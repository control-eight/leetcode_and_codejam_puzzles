package com.my.leetcode;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.*;

/**
 * Created by alex.bykovsky on 4/18/17.
 */
public class Test {

	public static void main(String[] args) throws IOException {
		Map props = new HashMap();

		LineNumberReader lineReader = new LineNumberReader(new FileReader(new File("/Users/abyk/Documents/company_list.csv")));
		String line;
		List lines = new ArrayList();
		while ((line = lineReader.readLine()) != null) {
			line = line.replaceAll("\"", "\\\\\"");
			lines.add(line);
		}
		lineReader.close();

		int count = 500;
		Random startRnd = new Random(123);
		Random sizeRnd = new Random(123);
		for (int i = 0; i < count; i++) {
			int fromIndex = startRnd.nextInt(3535);
			int size = sizeRnd.nextInt(500);
			List value = lines.subList(fromIndex, Math.min(lines.size(), fromIndex + size));

			StringBuilder str = new StringBuilder();
			Iterator iter = value.iterator();
			if (iter.hasNext()) {
				str.append("\"").append(iter.next()).append("\"");
			}
			while (iter.hasNext()) {
				str.append(",").append("\"").append(iter.next()).append("\"");
			}
			System.out.println(str);
			props.put("kwds_" + i, str.toString());
		}
	}
}