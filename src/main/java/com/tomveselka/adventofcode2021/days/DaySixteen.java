package com.tomveselka.adventofcode2021.days;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.tomveselka.adventofcode2021.utils.FileReaderCustom;

public class DaySixteen {
	public static void main(String[] args) {
	DaySixteen daySixteen = new DaySixteen();
	FileReaderCustom rdr = new FileReaderCustom();
	String path = "resources/Day16InputFull.txt";
	List<String> input = rdr.readFileString(path);
//	List<String> input = new ArrayList<String>();
//	input.add("880086C3E88112");
	//daySixteen.taskOne(input);
	daySixteen.taskTwo(input);
	}
	
	int sumOfVersions=0;
	int currentPos=0;
	public void taskOne(List<String> input) {
		String inputHexa=input.get(0);
		String binary=toBinary(inputHexa);
		currentPos=0;
		typeOfPacket(binary);	
		System.out.println("Sum of versions is "+sumOfVersions);
	}
	
	public void typeOfPacket(String binary) {
		int version=Integer.parseInt(binary.substring(currentPos, currentPos+3),2);
		//System.out.println("Current version is "+version);
		//System.out.println("Current possition is "+currentPos);
		sumOfVersions=sumOfVersions+version;
		int typeID=Integer.parseInt(binary.substring(currentPos+3, currentPos+6),2);
		currentPos=currentPos+6;
		if (typeID==4) {
			processLiteral(binary);			
		}else {
			processOperator(binary);
		}
	}
	public void processLiteral(String binary) {
		boolean moreNumbers=true;
		int pos=currentPos;
		StringBuilder finNumber= new StringBuilder();
		while (moreNumbers) {
			if("0".equals(binary.substring(pos, pos+1))) {
				moreNumbers=false;
			}
			finNumber.append(binary.substring(pos+1, pos+5));
			pos=pos+5;
			if(pos+4>binary.length()) {
				moreNumbers=false;
			}
		}
		currentPos=pos;
		//System.out.println("Number in binary="+finNumber.toString()+" number in decimal="+Integer.parseInt(finNumber.toString(), 2));
	}
	public void processOperator(String binary){
		String header=binary.substring(currentPos, currentPos+1);
		currentPos++;
		if ("1".equals(header)) {			
			int numberOfPackages=Integer.parseInt(binary.substring(currentPos, currentPos+11), 2);
			currentPos=currentPos+11;
			for (int i=0;i<numberOfPackages;i++) {
				typeOfPacket(binary);
			}
		}else {
			int lengthOfPackages = Integer.parseInt(binary.substring(currentPos, currentPos + 15), 2);
			currentPos=currentPos+15;
			int startingPos=currentPos;
			while (currentPos<startingPos+lengthOfPackages) {
				typeOfPacket(binary);
			}
		}
	}
	
	//=====TASK TWO=====
	public void taskTwo(List<String> input) {
		String inputHexa=input.get(0);
		String binary=toBinary(inputHexa);
		currentPos=0;
		long result=typeOfPacketV2(binary);	
		System.out.println("Value of outer packet is: " +result);
	}
	
	public long typeOfPacketV2(String binary) {
		int version=Integer.parseInt(binary.substring(currentPos, currentPos+3),2);
		//System.out.println("Current version is "+version);
		//System.out.println("Current possition is "+currentPos);
		sumOfVersions=sumOfVersions+version;
		int typeID=Integer.parseInt(binary.substring(currentPos+3, currentPos+6),2);
		currentPos=currentPos+6;
		if (typeID==4) {
			return processLiteralV2(binary);			
		}else {
			return processOperatorV2(binary, typeID);
		}
	}
	public long processLiteralV2(String binary) {
		boolean moreNumbers=true;
		int pos=currentPos;
		StringBuilder finNumber= new StringBuilder();
		while (moreNumbers) {
			if("0".equals(binary.substring(pos, pos+1))) {
				moreNumbers=false;
			}
			finNumber.append(binary.substring(pos+1, pos+5));
			pos=pos+5;
			if(pos+4>binary.length()) {
				moreNumbers=false;
			}
		}
		currentPos=pos;
		return Long.parseLong(finNumber.toString(), 2);
		//System.out.println("Number in binary="+finNumber.toString()+" number in decimal="+Integer.parseInt(finNumber.toString(), 2));
	}
	public long processOperatorV2(String binary, int typeID){
		String header=binary.substring(currentPos, currentPos+1);
		currentPos++;
		List<Long> packetValues=new ArrayList<Long>();
		if ("1".equals(header)) {			
			int numberOfPackages=Integer.parseInt(binary.substring(currentPos, currentPos+11), 2);
			currentPos=currentPos+11;
			for (int i=0;i<numberOfPackages;i++) {
				packetValues.add(typeOfPacketV2(binary));
			}
		}else {
			int lengthOfPackages = Integer.parseInt(binary.substring(currentPos, currentPos + 15), 2);
			currentPos=currentPos+15;
			int startingPos=currentPos;
			while (currentPos<startingPos+lengthOfPackages) {
				packetValues.add(typeOfPacketV2(binary));
			}
		}
		long packetValue=packetOperation(packetValues,typeID);
		return packetValue;
		
	}
	
	public String toBinary(String inputHexa){
		HashMap<Character, String> hashMap = new HashMap<Character, String>();
		StringBuilder str = new StringBuilder();
	    // storing the key value pairs
	    hashMap.put('0', "0000");
	    hashMap.put('1', "0001");
	    hashMap.put('2', "0010");
	    hashMap.put('3', "0011");
	    hashMap.put('4', "0100");
	    hashMap.put('5', "0101");
	    hashMap.put('6', "0110");
	    hashMap.put('7', "0111");
	    hashMap.put('8', "1000");
	    hashMap.put('9', "1001");
	    hashMap.put('A', "1010");
	    hashMap.put('B', "1011");
	    hashMap.put('C', "1100");
	    hashMap.put('D', "1101");
	    hashMap.put('E', "1110");
	    hashMap.put('F', "1111");
	    
	    for (int i=0;i<inputHexa.length();i++) {
	    	char letter=inputHexa.substring(i, i+1).charAt(0);
	    	if(!hashMap.containsKey(letter)) {
	    		System.out.println("Not found");
	    	}else {
	    		str.append(hashMap.get(letter));
	    	}
	    }
	    return str.toString();
	    
	}
	
	public long packetOperation(List<Long> packetValues, int typeID) {
		if(typeID==0) {//SUM
			long sum=0;
			for (long packet:packetValues) {
				sum=sum+packet;
			}
			return sum;
		}
		if (typeID==1) {//Multiply
			long sum=1;
			for (long packet:packetValues) {
				sum=sum*packet;
			}
			return sum;
		}
		if (typeID==2) {//MIN
			return Collections.min(packetValues);	
		}
		if (typeID==3) {//MAX
			return Collections.max(packetValues);
		}
		if (typeID==5) {//Greater than
			if(packetValues.get(0).compareTo(packetValues.get(1))==1) {
				return 1;
			}else {
				return 0;
			}			
		}
		if (typeID==6) {//Less than
			if(packetValues.get(0).compareTo(packetValues.get(1))==-1) {
				return 1;
			}else {
				return 0;
			}
		}
		else {//equal
			if(packetValues.get(0).compareTo(packetValues.get(1))==0) {
				return 1;
			}else {
				return 0;
			}
		}
	}
}


