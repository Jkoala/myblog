package com.simple.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.simple.po.KeyWord;

/**
 * �ַ���������
 * 
 * @author
 *
 */
public class StringUtil {

	/**
	 * �ж��Ƿ��ǿ�
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * �ж��Ƿ��ǿ�
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		if ((str != null) && !"".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * ��ʽ��ģ����ѯ
	 * 
	 * @param str
	 * @return
	 */
	public static String formatLike(String str) {
		if (isNotEmpty(str)) {
			return "%" + str + "%";
		} else {
			return null;
		}
	}

	/**
	 * ���˵�������Ŀո�
	 * 
	 * @param list
	 * @return
	 */
	public static List<KeyWord> filterWhite(List<String> list) {
		List<KeyWord> resultList = new ArrayList<KeyWord>();
		KeyWord keyWord = null;
		 Random randdom = new Random( );
		for (String str : list) {
			if (isNotEmpty(str)) {
				keyWord = new KeyWord();
				keyWord.setName(str);
				keyWord.setClassName(getClassName(randdom.nextInt(5)));
				resultList.add(keyWord);
			}
		}
		return resultList;
	}

	public static String getClassName(int index) {
		switch (index) {
		case 1:
			return "primary";
		case 2:
			return "success";
		case 3:
			return "info";
		case 4:
			return "warning";
		default:
			return "success";
		}
	}
}
