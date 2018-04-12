package com.card.common.util;

import java.io.*;

public class FileUtil {
	public static String readFile(InputStream inputStream) {
		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();
		InputStreamReader inputStreamReader = null;
		try {
			inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			reader = new BufferedReader(inputStreamReader);

			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			reader.close();
			inputStreamReader.close();
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static void writeFile(String text, String fileName) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(new File(fileName));
			BufferedWriter bw = new BufferedWriter(writer);
			bw.write(text);
			bw.close();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
