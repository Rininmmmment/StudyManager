package model;

import java.util.List;

public class ShiftManager {
	public int calcWorkingTimeSum(List<Shift> shiftList) {
		int workingTimeSum = 0;
		
		int size = shiftList.size();
		for (int i = 0; i < size; i++) {
			Shift shift = shiftList.get(i);
			
			// getTime()によってTime型の "9:00:00からの経過時間をミリ秒で出力" している。
			long startTime = shift.getStartTime().getTime();
			long finishTime = shift.getFinishTime().getTime();
			
			// ミリ秒を1時間単位に変換し、和を出す
			workingTimeSum += (finishTime - startTime) / 3600000;
		}
		return workingTimeSum;
	}
}
