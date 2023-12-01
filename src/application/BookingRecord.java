package application;

import java.util.Date;

public class BookingRecord {
	private Date date;
	private int roomNum;
	private String buildingName;
	private int startTime;
	private int endTime;
	private String bookingStatus;
	
	// attributes that not shown in dashboard table
	private int roomid;
	private int bookingId;
	
	public BookingRecord(Date date, int roomNum, String buildingName, int startTime, int endTime, String bookingStatus) {
		super();
		this.date = date;
		this.roomNum = roomNum;
		this.buildingName = buildingName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.bookingStatus = bookingStatus;
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

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public int getRoomid() {
		return roomid;
	}

	public void setRoomid(int roomid) {
		this.roomid = roomid;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	
	
	
	
}
