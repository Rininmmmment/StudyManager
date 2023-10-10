package model;

import java.util.Random;

public class ShulackMsgGenerator {
	public static String generateMsg (String state) {
		String Msg = "";
		Random rand = new Random();
		
		switch(state) {
			case("start"):
				String[] startMsgList = {
						"おお〜がんばれ〜〜",
						"一緒に頑張ろう！",
						"いい目標だね〜",
				};
				Msg = startMsgList[rand.nextInt(startMsgList.length)];
				break;
				
			case("finish"):
				String[] finishMsgList = {
						"今日もお疲れ様〜",
						"お腹すいたね〜",
						"今日は集中できた？"
				};
				Msg = finishMsgList[rand.nextInt(finishMsgList.length)];
				break;
			default:
				 throw new IllegalStateException();	
		}
		
		return Msg;
	}
}
