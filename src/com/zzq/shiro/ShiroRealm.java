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
		System.out.println("���뵽��֤--------");
		//1. �� AuthenticationToken ת��Ϊ UsernamePasswordToken 
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();
		userinfo userinfofromdb= loginservice.selectUserinfoByUsername(username);
		System.out.println("�����ݿ��л�ȡ userinfoDB: " + userinfofromdb + " ++����Ӧ���û���Ϣ.");
		Object credentials = userinfofromdb.getPassword(); 
		String realmName = getName();
		Subject currentUser = SecurityUtils.getSubject();
		System.out.println("+++++++++realm�е�subject++++++++++");
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
	//��Ȩ�ᱻ shiro �ص��ķ���
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		System.out.println("+++++++++++++"+"��Ȩ");
		//1. �� PrincipalCollection ������ȡ��¼�û�����Ϣ
		userinfo principal = (userinfo)principals.getPrimaryPrincipal();
		System.out.println(principal.getUsertype()+"+++++++");
		//2. ���õ�¼���û�����Ϣ���û���ǰ�û��Ľ�ɫ��Ȩ��(������Ҫ��ѯ���ݿ�)
		Set<String> roles = new HashSet<>();
		if(principal.getUsertype()==2){
			roles.add("super");
			roles.add("user");
			System.out.println("����super��ɫ");
		}if(principal.getUsertype()==1){
			roles.add("user");
			System.out.println("����user��ɫ");
		}
		//3. ���� SimpleAuthorizationInfo, �������� reles ����.
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
		//4. ���� SimpleAuthorizationInfo ����. 
		return info;
	}
}
