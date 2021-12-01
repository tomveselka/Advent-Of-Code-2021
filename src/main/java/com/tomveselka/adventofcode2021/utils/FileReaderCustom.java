package com.tomveselka.adventofcode2021.utils;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReaderCustom {
	public List<String> readFileString(String path) {
		List<String> lines = new ArrayList<String>();
		try {
			lines = Files.readAllLines(Paths.get(path), Charset.defaultCharset());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lines;
	}
	
	public List<Long> readFileLong(String path) {
		List<String> lines = new ArrayList<String>();
		List<Long> output=new ArrayList<Long>();
		try {
			lines = Files.readAllLines(Paths.get(path), Charset.defaultCharset());
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i=0;i<lines.size();i++) {
			output.add(Long.parseLong(lines.get(i)));
		}
		return output;
	}
	
	public List<Integer> readFileInt(String path) {
		List<String> lines = new ArrayList<String>();
		List<Integer> output=new ArrayList<Integer>();
		try {
			lines = Files.readAllLines(Paths.get(path), Charset.defaultCharset());
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i=0;i<lines.size();i++) {
			//System.out.print(lines.get(i)+ ", ");
			output.add(Integer.parseInt(lines.get(i)));
		}
		//System.out.println(" ");
		return output;
	}
}
