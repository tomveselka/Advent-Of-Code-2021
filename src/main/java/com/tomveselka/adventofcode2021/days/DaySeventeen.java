package com.tomveselka.adventofcode2021.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.tomveselka.adventofcode2021.utils.FileReaderCustom;

public class DaySeventeen {
	public static void main(String[] args) {
		DaySeventeen daySeventeen = new DaySeventeen();
		FileReaderCustom rdr = new FileReaderCustom();
		List<String> input = new ArrayList<String>();
		//input.add("target area: x=20..30, y=-10..-5");
		input.add("target area: x=230..283, y=-107..-57");
		// List<String> input = rdr.readFileString(path);
		//daySeventeen.taskOne(input);
		daySeventeen.taskTwo(input);
	}

	public void taskOne(List<String> input) {
		String inputString = input.get(0).replaceAll("\\s+", "").replace("targetarea:x=", "");
		String[] coordArray = inputString.split(",y=");
		String[] xArrayString = coordArray[0].split("\\..");
		String[] yArrayString = coordArray[1].split("\\..");
		List<Integer> xList = Arrays.stream(xArrayString) // stream of String
				.map(Integer::valueOf) // stream of Integer
				.collect(Collectors.toList());
		List<Integer> yList = Arrays.stream(yArrayString) // stream of String
				.map(Integer::valueOf) // stream of Integer
				.collect(Collectors.toList());
		System.out.println("X list: " + xList.toString() + " Y list:" + yList.toString());
		int lowestPoint = yList.get(0);
		int elevation = 0;
		if (lowestPoint > 0) {
			elevation = Math.abs(lowestPoint - 1);
		} else {
			elevation = Math.abs(lowestPoint + 1);
		}
		int verticalVelocity = elevation;
		int reachedAltitude = 0;
		while (verticalVelocity > 0) {
			reachedAltitude = reachedAltitude + verticalVelocity;
			verticalVelocity--;
		}
		System.out.println("Initial velocity is " + elevation + " and maximal altitude is " + reachedAltitude);
	}

	public void taskTwo(List<String> input) {
		String inputString = input.get(0).replaceAll("\\s+", "").replace("targetarea:x=", "");
		String[] coordArray = inputString.split(",y=");
		String[] xArrayString = coordArray[0].split("\\..");
		String[] yArrayString = coordArray[1].split("\\..");
		List<Integer> xList = Arrays.stream(xArrayString) // stream of String
				.map(Integer::valueOf) // stream of Integer
				.collect(Collectors.toList());
		List<Integer> yList = Arrays.stream(yArrayString) // stream of String
				.map(Integer::valueOf) // stream of Integer
				.collect(Collectors.toList());
		System.out.println("X list: " + xList.toString() + " Y list:" + yList.toString());
		int lowestPoint = yList.get(0);
		int highestPoint = yList.get(1);
		int closestPoint = xList.get(0);
		int furthestPoint = xList.get(1);
		int maximumElevation = Math.abs(lowestPoint + 1);
		Set<Integer> possibleVelocities= new HashSet<Integer>();
		for (int velocity=furthestPoint;velocity>0;velocity--) {
			int sum=0;
			for (int i=velocity;i>0;i--) {
				sum=sum+i;
				if (sum>=closestPoint&&sum<=furthestPoint) {
					possibleVelocities.add(velocity);
				}
			}
		}
		Set<Integer> possibleElevations= new HashSet<Integer>();
		for (int elevation=maximumElevation;elevation>=lowestPoint;elevation--) {
			int currentClimb=elevation;
			int sum=0;
			while(sum>lowestPoint) {
				sum=sum+currentClimb;
				currentClimb=currentClimb-1;	
				if (sum>=lowestPoint&&sum<=highestPoint) {
					possibleElevations.add(elevation);
				}
			}
		}
		Set<String> possibleCombinations= new HashSet<String>();
		for (Integer velocity: possibleVelocities) {
			for (Integer elevation: possibleElevations) {
				int sumH=0;
				int sumV=0;
				int currentV=velocity;
				int currentH=elevation;
				while (sumV<=furthestPoint&&sumH>=lowestPoint) {
					sumH=sumH+currentH;
					sumV=sumV+currentV;
					if(currentV>0) {
						currentV--;
					}
					currentH--;
					if(sumV>=closestPoint&&sumV<=furthestPoint&&sumH<=highestPoint&&sumH>=lowestPoint) {
						possibleCombinations.add(velocity+","+elevation);
					}
				}
			}
		}
		System.out.println("Possible velocities: "+possibleVelocities.toString());
		System.out.println("Possible elevations: "+possibleElevations.toString());
		System.out.println("Number of possible combinations is: "+possibleCombinations.size());
	}
	
}