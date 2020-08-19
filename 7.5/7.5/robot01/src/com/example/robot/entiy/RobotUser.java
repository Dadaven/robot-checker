package com.example.robot.entiy;

public class RobotUser {
	private String username;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getVideo_port() {
		return video_port;
	}
	public void setVideo_port(int video_port) {
		this.video_port = video_port;
	}
	public int getNum_port() {
		return num_port;
	}
	public void setNum_port(int num_port) {
		this.num_port = num_port;
	}
	private String password;
	private String ip;
	public RobotUser(String username, String password, String ip,
			int video_port, int num_port) {
		super();
		this.username = username;
		this.password = password;
		this.ip = ip;
		this.video_port = video_port;
		this.num_port = num_port;
	}
	public RobotUser() {
		
	} 
	private int video_port;
	private int num_port;
	private static RobotUser instance = null;

	public static RobotUser getInstance() {
		if (instance == null) {

			synchronized (RobotUser.class) {
				if (instance == null) {
					instance = new RobotUser();
				}
			}
		}
		return instance;
	}

}
