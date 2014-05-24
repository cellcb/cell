package org.cell.base;

import java.security.MessageDigest;
import java.util.UUID;

public class AnneUtil {

	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	/**
	 * 生成有序的id (为mysql innodb 特性考虑 暂定)
	 * 
	 * @return
	 */
	public static String genId() {
		return System.currentTimeMillis()
				+ UUID.randomUUID().toString().substring(0, 27);
	}

	/**
	 * 字符串转成MD5格式
	 * 
	 * @param origin
	 *            原始字符串
	 * @return
	 */
	public static String MD5Encode(String origin) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString
					.getBytes()));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return resultString;
	}

	/**
	 * 转换字节数组为16进制字串
	 * 
	 * @param b字节数组
	 * @return 16进制字串
	 */

	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static void main(String[] args) {
		// 1f5f72f9d38b9cff1e5a663782ad26ac
		System.out.println(MD5Encode("chengbo"));
	}
}
