package test;

import java.util.List;

import dao.ShiftDAO;
import model.Shift;

public class ShiftDAOTest {
	public static void main(String[] args) {
		ShiftDAO shiftDAO = new ShiftDAO();
		
		// シフト取得テスト
//		List<Shift> shiftList1 = shiftDAO.findOneMonthShift(1, Date.valueOf("2023-09-01"), Date.valueOf("2023-09-30"));
//		shiftList1.forEach(shift -> System.out.println(shift.getStartTime()));
		
		// 今日のシフト取得テスト
		List<Shift> shiftList2 = shiftDAO.findTodayShift(1);
		shiftList2.forEach(shift -> System.out.println(shift.getStartTime()));
		
		
	    // シフト作成テスト
//	    List<Shift> shiftList3 = new ArrayList<Shift>();
//	    shiftList3.add(new Shift(-1, 5, Date.valueOf("2023-09-01"), Time.valueOf("18:00:00"), Time.valueOf("24:00:00")));
//	    shiftList3.add(new Shift(-1, 5, Date.valueOf("2023-09-02"), Time.valueOf("17:00:00"), Time.valueOf("24:00:00")));
//	    System.out.println(shiftDAO.createShift(shiftList3));
	    
	    // シフト削除テスト
//	    int[] testArr = {};
//	    shiftDAO.deleteShift(testArr);
	}
}
