package com.work.common.utils; // 定义该类所在的包

import java.security.MessageDigest; // 导入MessageDigest类，用于生成哈希值
import java.util.UUID; // 导入UUID类，用于生成唯一标识符

public class MD5Util {

	/**
	 * 将字节数组转换为十六进制字符串
	 *
	 * @param b 字节数组
	 * @return 转换后的十六进制字符串
	 */
	private static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer(); // 创建一个StringBuffer用于存储结果
		for (int i = 0; i < b.length; i++) // 遍历字节数组
			resultSb.append(byteToHexString(b[i])); // 将每个字节转换为十六进制字符串并添加到结果中

		return resultSb.toString(); // 返回最终的十六进制字符串
	}

	/**
	 * 将单个字节转换为十六进制字符串
	 *
	 * @param b 字节
	 * @return 转换后的十六进制字符串
	 */
	private static String byteToHexString(byte b) {
		int n = b; // 将字节转换为int
		if (n < 0) // 如果是负值，将其转换为正值
			n += 256;
		int d1 = n / 16; // 十六进制的高位
		int d2 = n % 16; // 十六进制的低位
		return hexDigits[d1] + hexDigits[d2]; // 返回对应的十六进制字符
	}

	/**
	 * 对输入字符串进行MD5编码
	 *
	 * @param origin 待编码的字符串
	 * @param charsetname 字符集名称
	 * @return MD5编码结果的十六进制字符串
	 */
	public static String MD5Encode(String origin, String charsetname) {
		String resultString = null; // 初始化结果字符串
		try {
			resultString = new String(origin); // 将原始字符串赋值
			MessageDigest md = MessageDigest.getInstance("MD5"); // 获取MD5哈希算法实例
			// 根据字符集进行字节数组的生成
			if (charsetname == null || "".equals(charsetname))
				resultString = byteArrayToHexString(md.digest(resultString.getBytes())); // 获取MD5哈希并转换为十六进制字符串
			else
				resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname))); // 使用指定字符集
		} catch (Exception exception) {
			// 捕获异常但不处理，直接返回null
		}
		return resultString; // 返回MD5编码后的字符串
	}

	/**
	 * 生成16个长度的十六进制UUID
	 *
	 * @return 16位长的UUID字符串
	 */
	public static String get16UUID() {
		UUID id = UUID.randomUUID(); // 生成一个随机UUID
		String[] idd = id.toString().split("-"); // 将UUID转换为字符串并按"-"分割
		return idd[0] + idd[1] + idd[2]; // 返回前48位字符（去掉"-"后的部分）
	}

	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" }; // 十六进制字符表
}
