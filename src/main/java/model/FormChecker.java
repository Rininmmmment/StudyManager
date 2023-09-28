package model;

import java.sql.Date;
import java.sql.Time;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.validator.routines.EmailValidator;

public class FormChecker {
	public String findEmailError(String checkingText) {
		if (checkingText.length() == 0) return "メールアドレスを入力してください。\n";
		if (checkingText.length() > 100) return "メールアドレスは100文字以内で入力してください。\n";
		if (!EmailValidator.getInstance().isValid(checkingText)) return "メールアドレスを正しく入力してください。\n";
		
		return ""; // 全て満たしていれば空で返す
	}
	
	public String findPassError(String checkingText) {
		if (checkingText.length() < 6) return "パスワードは6文字以上で入力してください。\n";
		if (checkingText.length() > 100) return "パスワードは100文字以内で入力してください。\n";
		
		// 正規表現で英字・数字が使われているかチェック
		Pattern pNumAndAlphabet = Pattern.compile("^[A-Za-z0-9]+$");
		Matcher m = pNumAndAlphabet.matcher(checkingText);
		if (!m.matches()) return "パスワードは半角英数字で入力してください。\n";
		
		return ""; // 全て満たしていれば空で返す
	}
	
	public String findNameError(String checkingText) {
		if (checkingText.length() < 3) return "ニックネームは3文字以上で入力してください。\n";
		if (checkingText.length() > 20) return "パスワードは20文字以内で入力してください。\n";
		
		return ""; // 全て満たしていれば空で返す
	}
	
	public String findDateError(Date checkingDate) {
		long miliseconds = System.currentTimeMillis();
		Date today = new Date(miliseconds);
		
		// 日付が現在時刻以降か
		if (!checkingDate.after(today)) return "明日以降のシフトを入力してください。\n";
		
		return ""; // 全て満たしていれば空で返す
	}
	
	public String findTimeError(Time checkingStartTime, Time checkingFinishTime) {
		if (checkingStartTime.after(checkingFinishTime)) return "終了時刻は開始時刻より後に設定してください。\n";
		return ""; // 全て満たしていれば空で返す
	}
}
