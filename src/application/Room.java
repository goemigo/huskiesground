package application;

import java.sql.Date;

public abstract class Room {
	private int roomId;
	private int roomNum;
	private int buildingNum;
	private String buildingName;
	private String bookableFlag;
	
	public Room(int roomId, int roomNum, int buildingNum, String buildingName, String bookableFlag) {
		super();
		this.roomId = roomId;
		this.roomNum = roomNum;
		this.buildingNum = buildingNum;
		this.buildingName = buildingName;
		this.bookableFlag = bookableFlag;
	}
	
	public abstract void book( int userid, int roomid, Date date, int start, int end, String status);
	// method fired when book button is clicked
	// for bookable rooms, this method should changed the booked value from 0 to 1 in roomStatus table
	// for view-only rooms, this method should tell users this is view-only

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public int getBuildingNum() {
		return buildingNum;
	}

	public void setBuildingNum(int buildingNum) {
		this.buildingNum = buildingNum;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getBookableFlag() {
		return bookableFlag;
	}

	public void setBookableFlag(String bookableFlag) {
		this.bookableFlag = bookableFlag;
	}
	
	
}
