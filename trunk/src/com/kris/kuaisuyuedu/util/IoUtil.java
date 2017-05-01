package com.kris.kuaisuyuedu.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;

public class IoUtil {

	/**
	 * UTF-8编码方式
	 */
	public static final String UTF8 = "UTF-8";

	/**
	 * 换行符
	 */
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");

	/**
	 * 读取流中的数据，返回二进制数组
	 * 
	 * @param in
	 *            输入流
	 * @return
	 * @throws IOException
	 */
	public static byte[] readStreamToByteArray(InputStream in) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		while ((len = in.read(buffer)) != -1) {
			out.write(buffer, 0, len);
		}
		out.close();
		in.close();

		return out.toByteArray();
	}

	/**
	 * 读取流中的数据，返回UTF-8编码方式字符串
	 * 
	 * @param in
	 *            输入流
	 * @return
	 * @throws Exception
	 */
	public static String readStreamToString(InputStream in) throws Exception {
		byte[] data = readStreamToByteArray(in);
		return new String(data, UTF8);
	}

	/**
	 * 读取源文件内容
	 * 
	 * @param filename
	 *            String 文件路径
	 * @throws IOException
	 * @return byte[] 文件内容
	 */
	public static byte[] readFile(String filename) {
		File file = new File(filename);
		long len = file.length();
		byte[] bytes = new byte[(int) len];

		BufferedInputStream bufferedInputStream;
		try {
			bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
			int r = bufferedInputStream.read(bytes);
			if (r != len) {
				bufferedInputStream.close();
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return bytes;

	}

	public static String getAssetToStr(Context context, String fileName) {
		StringBuilder sb = new StringBuilder(10000);
		String line;
		try {
			InputStream is = context.getAssets().open(fileName);// 读取文件
			if (is != null) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(is, "Unicode"));
				while ((line = reader.readLine()) != null) {
					sb.append(line).append("\n");// stream转string
				}
				is.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
