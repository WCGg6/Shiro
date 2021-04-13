package com.zhiyou.realm;

        import com.zhiyou.model.Permission;
        import com.zhiyou.model.Role;
        import com.zhiyou.model.User;
        import com.zhiyou.service.PermissionService;
        import com.zhiyou.service.RoleService;
        import com.zhiyou.service.UserService;
        import org.apache.shiro.authc.*;
        import org.apache.shiro.authc.credential.CredentialsMatcher;
        import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
        import org.apache.shiro.authz.AuthorizationInfo;
        import org.apache.shiro.authz.SimpleAuthorizationInfo;
        import org.apache.shiro.realm.AuthorizingRealm;
        import org.apache.shiro.subject.PrincipalCollection;
        import org.apache.shiro.util.ByteSource;
        import org.springframework.beans.factory.annotation.Autowired;

        import java.util.ArrayList;
        import java.util.List;

/**
 * @author QiuShiju
 * @date 2021/3/26
 * @desc
 */
public class UserAuthorRealm extends AuthorizingRealm{

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    /**
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 获得用户名
        User user = (User) principals.getPrimaryPrincipal( );

        // 通过用户名查角色
        List<Role> roles = roleService.findRolesByUsername(user.getUsername());
        ArrayList<String> rolename = new ArrayList<>( );
        for (Role role : roles) {
            rolename.add(role.getName());
        }

        // 通过角色查权限
        List<Permission> permissions = permissionService.findPermissionByRoles(roles);
        ArrayList<String> permissionname = new ArrayList<>( );
        for (Permission permission : permissions) {
            permissionname.add(permission.getUrl());
        }

        // 封装
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo( );
        info.addRoles(rolename);
        info.addStringPermissions(permissionname);
        return info;
    }

    /**
     * 认证
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 从登录信息中获得用户名密码
        String username = (String) token.getPrincipal( );
        char[] credentials = (char[]) token.getCredentials( );
        String password = String.valueOf(credentials);

        // 查数据库
         User user = userService.findUserByUsernamePassword(username,password);
        System.out.println("user="+user);
       // User user = userService.findUserByUsername(username);

        // 比对
        if (user == null) {
            throw new UnknownAccountException();
        }
//        ByteSource byteSource = ByteSource.Util.bytes(user.getSalt( ));
        // 封装信息
        return new SimpleAuthenticationInfo(user,credentials,getName());
    }
    /**
     * 重写方法,指定加密方法及次数
     */
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
        shaCredentialsMatcher.setHashAlgorithmName("MD5");
        shaCredentialsMatcher.setHashIterations(1024);
        super.setCredentialsMatcher(shaCredentialsMatcher);
    }
}
