package application;

import java.util.Date;

public class roomData {
	private Date date;
	private int roomNum;
	private String buildingName;
	private int startTime;
	private int endTime;
	private String option;
	
	public roomData(Date date, int roomNum, String buildingName, int start, int end, String option) {
		this.setDate(date);
		this.setRoomNum(roomNum);
		this.setBuildingName(buildingName);
		this.setStartTime(start);
		this.setEndTime(end);
		this.setOption(option);
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}
	
	
}

