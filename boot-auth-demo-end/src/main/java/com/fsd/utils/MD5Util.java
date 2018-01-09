package com.fsd.utils;

import java.security.MessageDigest;

/**
 * MD5加密
 * 
 */
public class MD5Util {
	private static final String SALT = "yy";
	private static final String WECHAT_SALT = "yy_aa";

	public static String encode(String password) {
		password = password + SALT;
		return processEncode(password);
	}

	public static String wechatEncode(String password) {
		password = password + WECHAT_SALT;
		return processEncode(password);
	}

	public static boolean wechatValidation(String str, String token) {
		boolean flag = false;
		if (wechatEncode(str).equals(token)) {
			flag = true;
		}
		return flag;
	}

	public static String processEncode(String password) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			throw new RuntimeException();
		}

		char[] charArray = password.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++) {
			byteArray[i] = (byte) charArray[i];
		}
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = md5Bytes[i] & 0xff;
			if(val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	public static void main(String[] args) {
		System.out.println(MD5Util.encode("123"));
		System.out.println(MD5Util.encode("123"));
	}
}
