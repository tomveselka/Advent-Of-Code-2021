package com.tomveselka.adventofcode2021.objects;

public class Step {
String state="";
private int Xleft=0;
private int Xright=0;
private int Yleft=0;
private int Yright=0;
private int Zleft=0;
private int Zright=0;
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public int getXleft() {
	return Xleft;
}
public void setXleft(int xleft) {
	Xleft = xleft;
}
public int getXright() {
	return Xright;
}
public void setXright(int xright) {
	Xright = xright;
}
public int getYleft() {
	return Yleft;
}
public void setYleft(int yleft) {
	Yleft = yleft;
}
public int getYright() {
	return Yright;
}
public void setYright(int yright) {
	Yright = yright;
}
public int getZleft() {
	return Zleft;
}
public void setZleft(int zleft) {
	Zleft = zleft;
}
public int getZright() {
	return Zright;
}
public void setZright(int zright) {
	Zright = zright;
}
public Step(String state, int xleft, int xright, int yleft, int yright, int zleft, int zright) {
	super();
	this.state = state;
	Xleft = xleft;
	Xright = xright;
	Yleft = yleft;
	Yright = yright;
	Zleft = zleft;
	Zright = zright;
}
@Override
public String toString() {
	return "Step [state=" + state + ", Xleft=" + Xleft + ", Xright=" + Xright + ", Yleft=" + Yleft + ", Yright="
			+ Yright + ", Zleft=" + Zleft + ", Zright=" + Zright + "]";
}
}
