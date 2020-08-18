package com.zzq.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.zzq.entity.userinfo;
import com.zzq.service.LoginService;
import com.zzq.service.investService;

public class ShiroRealm extends AuthorizingRealm {

	@Autowired
	LoginService loginservice;
	@Autowired
	investService investService;
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		System.out.println("进入到认证--------");
		//1. 把 AuthenticationToken 转换为 UsernamePasswordToken 
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();
		userinfo userinfofromdb= loginservice.selectUserinfoByUsername(username);
		System.out.println("从数据库中获取 userinfoDB: " + userinfofromdb + " ++所对应的用户信息.");
		Object credentials = userinfofromdb.getPassword(); 
		String realmName = getName();
		Subject currentUser = SecurityUtils.getSubject();
		System.out.println("+++++++++realm中的subject++++++++++");
		System.out.println(currentUser.hashCode());
		Session session=currentUser.getSession();
		session.setAttribute("user", userinfofromdb);
		Double countInteger= investService.selectusercount(userinfofromdb.getUserid());
		 session.setAttribute("count", countInteger); 
		SimpleAuthenticationInfo info = null; 
		info = new SimpleAuthenticationInfo(userinfofromdb, credentials, realmName);
		return info;
	}
	public static void main(String[] args) {
		String hashAlgorithmName = "MD5";
		Object credentials = "123456";
		//Object salt = ByteSource.Util.bytes("user");
		Object result = new SimpleHash(hashAlgorithmName, credentials);
		System.out.println(result);
	}
	//授权会被 shiro 回调的方法
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		System.out.println("+++++++++++++"+"授权");
		//1. 从 PrincipalCollection 中来获取登录用户的信息
		userinfo principal = (userinfo)principals.getPrimaryPrincipal();
		System.out.println(principal.getUsertype()+"+++++++");
		//2. 利用登录的用户的信息来用户当前用户的角色或权限(可能需要查询数据库)
		Set<String> roles = new HashSet<>();
		if(principal.getUsertype()==2){
			roles.add("super");
			roles.add("user");
			System.out.println("具有super角色");
		}if(principal.getUsertype()==1){
			roles.add("user");
			System.out.println("具有user角色");
		}
		//3. 创建 SimpleAuthorizationInfo, 并设置其 reles 属性.
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
		//4. 返回 SimpleAuthorizationInfo 对象. 
		return info;
	}
}
