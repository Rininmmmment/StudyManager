package test;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import model.Shift;
import model.ShiftManager;

public class ShiftManagerTest {
	public static void main(String[] args) {
		ShiftManager shiftManager = new ShiftManager();
		
		List<Shift> shiftList = new ArrayList<Shift>();
	    shiftList.add(new Shift(-1, 5, Date.valueOf("2023-09-01"), Time.valueOf("9:00:00"), Time.valueOf("10:00:00")));
	    shiftList.add(new Shift(-1, 5, Date.valueOf("2023-09-02"), Time.valueOf("20:00:00"), Time.valueOf("24:00:00")));
	    
		shiftManager.calcWorkingTimeSum(shiftList);
		
	}
}
