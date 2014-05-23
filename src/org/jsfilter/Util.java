package org.jsfilter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
	// d:/workspace/
	private String base;

	private Pattern pattern = Pattern.compile(
			"^\\s*\\$import\\s*\\(\\s*['\"](.*?)['\"]\\s*\\)\\s*;?\\s*$",
			Pattern.CASE_INSENSITIVE);

	// private StringBuffer sb = new StringBuffer();

	public Util(String base) {
		this.base = base;
	}

	private Map<String, Integer> map = new HashMap<String, Integer>();

	// STK/gaea
	public String readFile(String path) {

		StringBuffer result = new StringBuffer();
		path = base + path;
		path = path.replaceAll("\\.", "/");
		path += ".js";

		File f = new File(path);

		if (!f.isFile() || !f.exists()) {
			return "alert('" + path + "  �����ڣ�����һ��')\n";
		}

		List<String> allLines = readOnly(path);
		for (String oneLine : allLines) {
			Matcher matcher = pattern.matcher(oneLine);
			if (matcher.find()) {
				String group = matcher.group(1);
				if (group != null) {
					group = group.trim();
					Integer has = map.get(group);
					if (has == null || has != 1) {
						map.put(group, 1);
						String readFile = readFile(group);
						result.append(readFile);
						result.append("\n");
					}
				}
			} else {
				result.append(oneLine);
				result.append("\n");
			}
		}
		return result.toString();
	}

	private List<String> readOnly(String realPath) {
		BufferedReader br = null;
		List<String> result = new ArrayList<String>();
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					realPath), "UTF-8"));
			String tmp = null;
			while ((tmp = br.readLine()) != null) {
				result.add(tmp);
			}
		} catch (Exception e) {
			ArrayList<String> arrayList = new ArrayList<String>();
			arrayList.add(";;");
			return arrayList;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
				}
			}
		}
		result.add(";;");
		return result;
	}
	// public static void main(String[] args) {
	// Util util = new Util("d:/workspace/");
	// String lines = util.readFile("STK/gaea");
	// System.out.println(lines);
	// }
}
