package com.example.robot.entiy;

public class Messages {
	String message;
	public Messages(){
		x=0.0;
		y=0.0;
		tem="0";
		hum="0";
		co="0";
		sped="0";
		press="0";
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	String tem;//温度
	public String getWendu() {
		return tem;
	}
	public void setWendu(String wendu) {
		this.tem = tem;
	}
	public String getShidu() {
		return hum;
	}
	public void setShidu(String shidu) {
		this.hum = hum;
	}
	public String getCo() {
		return co;
	}
	public void setCo(String co) {
		this.co = co;
	}
	public Double getX() {
		return x;
	}
	public void setX(Double x) {
		this.x = x;
	}
	public Double getY() {
		return y;
	}
	public void setY(Double y) {
		this.y = y;
	}
	String hum;//湿度
	String co;//浓度
	Double x;//经度
	Double y;//纬度
	String sped;//速度
	public String getSped() {
		return sped;
	}
	public void setSped(String sped) {
		this.sped = sped;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	String press;//电压
	private static Messages rmessages = null;

	public static Messages getInstance() {
		if (rmessages == null) {

			synchronized (Messages.class) {
				if (rmessages == null) {
					rmessages = new Messages();
				}
			}
		}
		return rmessages;
	}
	
   public int[] setdate(String str){
	   int[] flag={0,0,0};
	String[] sourceStrArray = str.split("#");
	if(sourceStrArray.length==7)
	{
	if(sourceStrArray[0]!=null)
		x=Double.parseDouble(sourceStrArray[0]);	
	if(sourceStrArray[1]!=null)
   	y=Double.parseDouble(sourceStrArray[1]);
	if(sourceStrArray[2]!=null){
		tem=sourceStrArray[2];
		if(Double.parseDouble(tem)>50.0){
			flag[0]=1;		}
	}
   	
	if(sourceStrArray[3]!=null)
    hum=sourceStrArray[3];
	if(sourceStrArray[4]!=null){
		co=sourceStrArray[4];
		if(Double.parseDouble(co)>35.0){
			flag[1]=1;		}
	}
   	
	if(sourceStrArray[5]!=null)
	sped=sourceStrArray[5];
    if(sourceStrArray[6]!=null){
	press=sourceStrArray[6];
	if(Double.parseDouble(press)<50.0){
		flag[2]=1;		}
    }   	
   }
	return flag;
   }

}
