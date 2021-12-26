package com.tomveselka.adventofcode2021.objects;

public class Instruction {
	private String action="";
	private String param="";
	private String variabl="";
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getVariabl() {
		return variabl;
	}
	public void setVariabl(String variabl) {
		this.variabl = variabl;
	}
	@Override
	public String toString() {
		return "Instruction [action=" + action + ", param=" + param + ", variabl=" + variabl + "]";
	}
	public Instruction(String action, String param, String variabl) {
		super();
		this.action = action;
		this.param = param;
		this.variabl = variabl;
	}
}
