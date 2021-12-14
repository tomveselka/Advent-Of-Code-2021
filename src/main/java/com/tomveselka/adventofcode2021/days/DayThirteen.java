package com.tomveselka.adventofcode2021.days;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.tomveselka.adventofcode2021.utils.FileReaderCustom;

public class DayThirteen {

	public static void main(String[] args) {
		DayThirteen dayThirteen = new DayThirteen();
		FileReaderCustom rdr = new FileReaderCustom();
		String path = "resources/Day13InputFull.txt";
		//String path = "resources/Day13InputTest.txt";
		List<String> input = rdr.readFileString(path);
		// dayThirteen.taskOne(input);
		dayThirteen.taskTwo(input);
	}

	private List<String> foldList = new ArrayList<String>();

	public void taskOne(List<String> input) {
		List<Integer> XList = new ArrayList<Integer>();
		List<Integer> YList = new ArrayList<Integer>();
		int width = 0; // x, to the right
		int height = 0; // y, down
		boolean coordDone = false;
		for (int i = 0; i < input.size(); i++) {
			if ("".equals(input.get(i))) {
				coordDone = true;
			} else if (!coordDone) {
				String[] coord = input.get(i).split(",");
				XList.add(Integer.parseInt(coord[0]));
				YList.add(Integer.parseInt(coord[1]));
			} else {
				foldList.add(input.get(i).replace("fold along ", ""));
			}
		}
		System.out.println("x list size is " + XList.size() + " x list:" + XList.toString());
		System.out.println("y list size is " + YList.size() + " y list:" + YList.toString());
		System.out.println("fold list:" + foldList.toString());
		width = (int) Math.ceil(Collections.max(XList));
		width=((width + 99) / 100) * 100;
		height = (int) Math.ceil(Collections.max(YList));
		height=((height + 99) / 100) * 100;
		System.out.println("width="+width+" height="+height);
		String[][] paper = new String[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				paper[i][j] = ".";
			}
		}
		for (int i = 0; i < XList.size(); i++) {
			paper[YList.get(i)][XList.get(i)] = "#";
		}
		// printPaper(paper, height, width);
		countDots(paper, height, width);
		foldPaperV1(paper, height, width, 0);
	}

	public void taskTwo(List<String> input) {
		// same approach, just finish folding
		taskOne(input);
	}

	public void foldPaperV1(String[][] paper, int height, int width, int numberOfFold) {
		int foldLine = 0;
		System.out.println("Fold nr."+(numberOfFold+1));
		if ("y".equals(foldList.get(numberOfFold).substring(0, 1))) {
			foldLine = Integer.parseInt(foldList.get(numberOfFold).replace("y=", ""));
			int newHeight = foldLine;
			String[][] newPaper = new String[newHeight][width];
			for (int i = 0; i < newHeight; i++) {
				for (int j = 0; j < width; j++) {
					if ("#".equals(paper[i][j]) || "#".equals(paper[foldLine*2-i][j])) {
						newPaper[i][j] = "#";
					} else {
						newPaper[i][j] = ".";
					}
				}
			}
			//printPaper(newPaper, newHeight, width);
			countDots(newPaper, newHeight, width);
			numberOfFold++;
			if (numberOfFold < foldList.size()) {
				foldPaperV1(newPaper, newHeight, width, numberOfFold);
			} else {
				printPaper(newPaper, newHeight, width);
			}
		} else {
			foldLine = Integer.parseInt(foldList.get(numberOfFold).replace("x=", ""));
			int newWidth = foldLine;
			String[][] newPaper = new String[height][newWidth];
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < newWidth; j++) {
					if ("#".equals(paper[i][j]) || "#".equals(paper[i][foldLine*2-j])) {
						newPaper[i][j] = "#";
					} else {
						newPaper[i][j] = ".";
					}
				}
			}
			//printPaper(newPaper, height, newWidth);
			countDots(newPaper, height, newWidth);
			numberOfFold++;
			if (numberOfFold < foldList.size()) {
				foldPaperV1(newPaper, height, newWidth, numberOfFold);
			} else {
				printPaper(newPaper, height, newWidth);
			}
		}

	}

	public void countDots(String[][] paper, int height, int width) {
		int numberOfDots = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if ("#".equals(paper[i][j])) {
					numberOfDots++;
				}
			}
		}
		System.out.println("Number of dots is currently " + numberOfDots);
		System.out.println("");

	}

	public void printPaper(String[][] paper, int height, int width) {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if ("#".equals(paper[i][j])) {
					System.out.print(paper[i][j]);
				} else {
					System.out.print(" ");
				}
			}
			System.out.println("");
		}

	}

}
