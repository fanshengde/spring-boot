package com.fsd.password;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.security.Key;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PwEncode extends AuthorizingRealm{
	Logger logger = LoggerFactory.getLogger(PwEncode.class);

	// 散列算法
	@Test
	public void slsf() {
		String str = "admin";
		String salf = "123";

		String md5 = new Md5Hash(str, salf).toString();

		logger.info(md5);

		// 通过盐 “123”MD5 散列 “admin”。另外散列时还可以指定散列次数。如下
		String md5_2 = new Md5Hash(str, salf, 2).toString();

		logger.info(md5_2);

		/**
		 * Shiro 还提供了通用的散列支持 SHA256 算法生成相应的散列数据，另外还有如 SHA1、SHA512 算法
		 * 
		 */
		String sha256 = new SimpleHash("SHA-1", str, salf).toString();

		logger.info(sha256);

		/**
		 * 1、首先创建一个 DefaultHashService，默认使用 SHA-512 算法； 2、以通过 hashAlgorithmName 属性修改算法；
		 * 3、可以通过privateSalt 设置一个私盐，其在散列时自动与用户传入的公盐混合产生一个新盐； 4、可以通过
		 * generatePublicSalt属性在用户没有传入公盐的情况下是否生成公盐； 5、 可以设置 randomNumberGenerator
		 * 用于生成公盐； 6、可以设置 hashIterations属性来修改默认加密迭代次数； 7、 需要构建一个
		 * HashRequest，传入算法、数据、公盐、迭代次数。
		 */

		DefaultHashService hashService = new DefaultHashService(); // 默认算法SHA-512
		hashService.setHashAlgorithmName("SHA-512");
		hashService.setPrivateSalt(new SimpleByteSource(salf)); // 私盐，默认无
		hashService.setGeneratePublicSalt(true); // 是否生成公盐，默认false
		hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator()); // 用于生成公盐。默认就这个

		hashService.setHashIterations(1); // 生成Hash值的迭代次数

		HashRequest request = new HashRequest.Builder().setAlgorithmName("MD5").setSource(ByteSource.Util.bytes(str))
				.setSalt(ByteSource.Util.bytes(salf)).setIterations(2).build();
		String hex = hashService.computeHash(request).toHex();

		logger.info(hex);

		/**
		 * Shiro 还提供对称式加密 / 解密算法的支持，如 AES、Blowfish 等；当前还没有提供对非对称加密 / 解密算法支持
		 */
		AesCipherService aesCipherService = new AesCipherService();
		aesCipherService.setKeySize(128);

		// 生成key
		Key key = aesCipherService.generateNewKey();

		// 加密
		String encrptText = aesCipherService.encrypt(str.getBytes(), key.getEncoded()).toHex();

		// 解密
		String code = new String(aesCipherService.decrypt(Hex.decode(encrptText), key.getEncoded()).getBytes());

		logger.info(code);

		/**
		 * HashedCredentialsMatcher 实现密码验证服务 Shiro 提供了 CredentialsMatcher 的散列实现
		 * HashedCredentialsMatcher， 和之前的 PasswordMatcher
		 * 不同的是，它只用于密码验证，且可以提供自己的盐，而不是随机生成盐，且生成密码散列值的算法需要自己写，因为能提供自己的盐。
		 */
		// 1、生成密码散列值
		// 如果要写用户模块，需要在新增用户 / 重置密码时使用如上算法保存密码，将生成的密码及 salt2 存入数据库（因为我们的散列算法是：md5(md5(密码
		// +username+salt2))）。
		String algorithmName = "md5";
		String username = "zhang";
		String password = "123";
		String salf1 = username;
		String salf2 = new SecureRandomNumberGenerator().nextBytes().toHex();
		int hashIterations = 2;

		SimpleHash hash = new SimpleHash(algorithmName, password, salf1 + salf2, hashIterations);
		String encodedPassword = hash.toHex();

		logger.info(encodedPassword);

		// 2、生成 Realm（com.github.zhangkaitao.shiro.chapter5.hash.realm.MyRealm2）
		String encodePw = "202cb962ac59075b964b07152d234b70";
		String encodeSalf = "202cb962ac59075b964b07152d234b70";
		SimpleAuthenticationInfo ai = new SimpleAuthenticationInfo(username, encodedPassword, getName());
		ai.setCredentials(ByteSource.Util.bytes(username + salf));
		logger.info(ai.getPrincipals() + "");
		
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		return null;
	}
}
