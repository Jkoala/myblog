package com.simple.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * ���ܹ���
 *
 */
public class CryptographyUtil {

	
	/**
	 * Md5����
	 * @param str
	 * @param salt
	 * @return
	 */
	public static String md5(String str,String salt){
		return new Md5Hash(str,salt).toString();
	}
	
	public static void main(String[] args) {
		String password="o";
		System.out.println("Md5���ܣ�"+md5(password,"java1234"));
	}
}
