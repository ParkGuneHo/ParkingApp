package parking;

public class Parking {
	private String type;
	private int CarNum;
	private int In_Hour;
	private int In_Min;
	private int Out_Hour;
	private int Out_Min;
	int fee=0;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getCarNum() {
		return CarNum;
	}
	public void setCarNum(int carNum) {
		this.CarNum = carNum;
	}
	public int getIn_Hour() {
		return In_Hour;
	}
	public void setIn_Hour(int In_Hour) {
		this.In_Hour = In_Hour;
	}
	public int getIn_Min() {
		return In_Min;
	}
	public void setIn_Min(int In_Min) {
		this.In_Min = In_Min;
	}
	public int getOut_Hour() {
		return Out_Hour;
	}
	public void setOut_Hour(int Out_Hour) {
		this.Out_Hour = Out_Hour;
	}
	public int getOut_Min() {
		return Out_Min;
	}
	public void setOut_Min(int Out_Min) {
		this.Out_Min = Out_Min;
	}
	public int getFee() {
		fee = ((Out_Hour - In_Hour)*60+(Out_Min - In_Min))*100;
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
}
