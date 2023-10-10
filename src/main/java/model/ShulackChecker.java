package model;

public class ShulackChecker {
	public static String checkShulackMsg(String msg) {
		String checkText = msg.substring(0,6);
		if (checkText.equals("【出勤連絡】")) return "startShulack";
		if (checkText.equals("【退勤連絡】")) return "finishShulack";
		return "";
	}
}
