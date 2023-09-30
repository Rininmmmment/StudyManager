package model;

import java.util.Random;

public class BossMessage {
	public static String generateMessage(String state) {
		String message = "";
		Random rand = new Random();
		switch(state) {
			case("before"):
				String[] beforeMsgList = {
						"今日もがんばって働くのじゃ！",
						"むむむ、眠いのじゃ...",
						"はやく出勤するのじゃ！"
				};
				message = beforeMsgList[rand.nextInt(beforeMsgList.length)];
				break;
			case("working"):
				String[] workingMsgList = {
						"出勤したらしゅらっくで目標・開始連絡するのじゃ！",
						"退勤前は振り返り・終了連絡するのじゃ！",
						"疲れてきたのじゃ...",
						"早く帰りたいのじゃ...",
						"サボらず働くのじゃ！"
				};
				message = workingMsgList[rand.nextInt(workingMsgList.length)];
				break;
			default:
				 throw new IllegalStateException();	
		}
		return message;
	}
}
