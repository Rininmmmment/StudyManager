package model;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class WorkingHistory implements Serializable {
	private int ID;
	private int userID;
	private Date date;
	private Time startTime;
	private Time finishTime;
	
	public WorkingHistory() { }
	public WorkingHistory(int ID, int userID, Date date, Time startTime, Time finishTime) {
		this.ID = ID;
		this.userID = userID;
		this.date = date;
		this.startTime = startTime;
		this.finishTime = finishTime;
	}
	
	public int getID() { return ID; }
	public void setID(int ID) { this.ID = ID; }
	public int getUserID() { return userID; }
	public void setUserID(int userID) { this.userID = userID; }
	public Date getDate() { return date; }
	public void setDate(Date date) { this.date = date; }
	public Time getStartTime() { return startTime; }
	public void setStartTime(Time startTime) { this.startTime = startTime; }
	public Time getFinishTime() { return finishTime; }
	public void setFinishTime(Time finishTime) { this.finishTime = finishTime; }
}