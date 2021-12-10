package com.tomveselka.adventofcode2021.days;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.tomveselka.adventofcode2021.utils.FileReaderCustom;

public class DayNine {
	private int basinSize=0;
	
	private Set<String> visited = new HashSet<String>();
	int height =0;
	int width =0;

	public static void main(String[] args) {
		DayNine dayNine = new DayNine();
		FileReaderCustom rdr = new FileReaderCustom();
		String path = "resources/Day9InputFull.txt";
		//String path = "resources/Day9InputTest.txt";
		List<String> input = rdr.readFileString(path);
		//dayNine.taskOne(input);
		dayNine.taskTwo(input);

	}

	public void taskOne(List<String> input) {
		int height = input.size();
		int width = input.get(0).length();
		int[][] area = new int[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				area[i][j] = Integer.parseInt(input.get(i).substring(j, j + 1));
				// System.out.print(area[i][j]);
			}
			// System.out.println("");
		}
		int riskScore=0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int location=area[i][j];
				int numberOfLower=0;
				for (int a=i-1;a<=i+1;a++) {
					for (int b=j-1;b<=j+1;b++) {
						if(a>=0&&a<height&&b>=0&&b<width) {
							if (location>area[a][b]) {
								numberOfLower++;
							}
						}
					}
				}
				if (numberOfLower==0) {
					riskScore=riskScore+area[i][j]+1;
				}
			}
		}
		System.out.println("Sum of risk levels is "+riskScore);
		
		

	}
	
	public void taskTwo(List<String> input) {
		height = input.size();
		width = input.get(0).length();
		int[][] area = new int[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				area[i][j] = Integer.parseInt(input.get(i).substring(j, j + 1));
				// System.out.print(area[i][j]);
			}
			// System.out.println("");
		}
		
		List<Integer> basinSizes = new ArrayList<Integer>();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int location=area[i][j];
				int numberOfLower=0;
				basinSize=0;
				
				for (int a=i-1;a<=i+1;a++) {
					for (int b=j-1;b<=j+1;b++) {
						if(a>=0&&a<height&&b>=0&&b<width) {
							if (location>area[a][b]) {
								numberOfLower++;
							}
						}
					}
				}
				if (numberOfLower==0) {
					visited.add(i+"X"+j);
					basinSize++;
					inspectSurrounding(area,i,j);
				}
				//System.out.println("Basin size is "+basinSize);				
				visited.clear();
				basinSizes.add(basinSize);
				basinSize=0;
			}
		}
		//System.out.println(basinSizes);
		basinSizes.removeIf(n -> Objects.equals(n, 0));
		//System.out.println(basinSizes);
		Collections.sort(basinSizes,Collections.reverseOrder());
		//System.out.println(basinSizes);
		int result=1;
		for (int i=0;i<3;i++) {
			result=result*basinSizes.get(i);
		}
		System.out.println("Result is "+result);
	}
	
	public void inspectSurrounding(int[][] area, int a, int b) {
		if (a>0&&a<height&&b>=0&&b<width&&area[a-1][b]!=9) {
			if(!visited.contains((a-1)+"X"+(b))){
				basinSize++;
				visited.add((a-1)+"X"+(b));
				inspectSurrounding(area,a-1,b);
			}
		}
		if (a>=0&&a<height-1&&b>=0&&b<width&&area[a+1][b]!=9) {
			if(!visited.contains((a+1)+"X"+(b))){
				basinSize++;
				visited.add((a+1)+"X"+(b));
				inspectSurrounding(area,a+1,b);
			}
		}
		if (a>=0&&a<height&&b>0&&b<width&&area[a][b-1]!=9) {
			if(!visited.contains((a)+"X"+(b-1))){
				basinSize++;
				visited.add((a)+"X"+(b-1));
				inspectSurrounding(area,a,b-1);
			}
		}
		if (a>=0&&a<height&&b>=0&&b<width-1&&area[a][b+1]!=9) {
			if(!visited.contains((a)+"X"+(b+1))){
				basinSize++;
				visited.add((a)+"X"+(b+1));
				inspectSurrounding(area,a,b+1);
			}
		}
	}


}
