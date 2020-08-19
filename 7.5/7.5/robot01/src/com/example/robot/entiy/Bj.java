package com.example.robot.entiy;

public class Bj {
  private int bjId;
  public String getBjType() {
	return bjType;
}
public void setBjType(String bjType) {
	this.bjType = bjType;
}
public String getBjPlace() {
	return bjPlace;
}
public void setBjPlace(String bjPlace) {
	this.bjPlace = bjPlace;
}
public String getBjTime() {
	return bjTime;
}
public void setBjTime(String bjTime) {
	this.bjTime = bjTime;
}
private String bjType;
  private String bjPlace;
  private String bjTime;
 
public Bj(int bjId, String bjType, String bjPlace, String bjTime) {
	super();
	this.bjId = bjId;
	this.bjType = bjType;
	this.bjPlace = bjPlace;
	this.bjTime = bjTime;
}
public Bj(String bjType, String bjPlace, String bjTime) {
	super();
	this.bjType = bjType;
	this.bjPlace = bjPlace;
	this.bjTime = bjTime;
}
  
}
