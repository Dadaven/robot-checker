package com.example.modle;

import java.util.Date;

import com.example.robot.entiy.Messages;

public class MessageRecord {
	private String message;
	


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public Messages getMessages(){
		Messages messages=new Messages();
		
		return messages;	
	}
	
}
