package cn.ego.utils;

import java.util.Random;

public class StringUtils {

	private static final char[] CHARS = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9'/*, 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
			'K', 'L', 'M', 'N','O','P','Q','R','S','T','U','V','W','X','Y','Z'*/,'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			'k', 'l', 'm', 'n','o','p','q','r','s','t','u','v','w','x','y','z' };
	
	private static final Random ran = new Random();
	
	public static String getRandomStr(int length) {
		if(length<=0) length = 3;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append(CHARS[ran.nextInt(CHARS.length)]);
		}
		return sb.toString();
	}
}
