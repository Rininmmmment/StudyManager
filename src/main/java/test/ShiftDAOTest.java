package test;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import dao.ShiftDAO;
import model.Shift;

public class ShiftDAOTest {
	public static void main(String[] args) {
		ShiftDAO shiftDAO = new ShiftDAO();
		
		// シフト取得テスト
		List<Shift> shiftList = shiftDAO.findOneMonthShift(1, Date.valueOf("2023-09-01"), Date.valueOf("2023-09-30"));
		shiftList.forEach(shift -> System.out.println(shift.getStartTime()));
	    
	    // シフト作成テスト
	    List<Shift> shiftList2 = new ArrayList<Shift>();
	    shiftList2.add(new Shift(-1, 5, Date.valueOf("2023-09-01"), Time.valueOf("18:00:00"), Time.valueOf("24:00:00")));
	    shiftList2.add(new Shift(-1, 5, Date.valueOf("2023-09-02"), Time.valueOf("17:00:00"), Time.valueOf("24:00:00")));
	    System.out.println(shiftDAO.createShift(shiftList2));
	}
}
