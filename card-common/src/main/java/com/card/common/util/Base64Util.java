package com.card.common.util;

import com.google.common.base.CaseFormat;
import com.google.common.io.BaseEncoding;

import java.io.IOException;
import java.io.UnsupportedEncodingException;


public class Base64Util {

	/**
	 * 转码 charsetType-->base64
	 * @param str
	 * @param charsetType
	 * @return
	 */
	public static String charsetForBase64(String str, String charsetType) {
		String strEncoder = null;
		try {
			strEncoder = BaseEncoding.base64().encode(str.getBytes(charsetType));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return strEncoder;
	}

	/**
	 * 转码 base64-->charsetType
	 *
	 * @param str
	 * @return
	 */
	public static String base64ForCharset(String str, String charsetType) {
		String strEncoder = null;
		try {
			strEncoder = new String(BaseEncoding.base64().decode(str), charsetType);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return strEncoder;
	}

	public static String base64Encode(byte[] bytes){
		return BaseEncoding.base64().encode(bytes);
	}

	public static byte[] base64Decode(String s) throws IOException {
		return BaseEncoding.base64().decode(s);
	}
}
