package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.validator.routines.EmailValidator;

public class FormChecker {
	public String findEmailError(String checkingText) {
		if (checkingText.length() == 0) return "メールアドレスを入力してください";
		if (checkingText.length() > 100) return "メールアドレスは100文字以内で入力してください";
		if (!EmailValidator.getInstance().isValid(checkingText)) return "メールアドレスを正しく入力してください";
		
		return ""; // 全て満たしていれば空で返す
	}
	
	public String findPassError(String checkingText) {
		if (checkingText.length() < 6) return "パスワードは6文字以上で入力してください";
		if (checkingText.length() > 100) return "パスワードは100文字以内で入力してください";
		
		// 正規表現で英字・数字が使われているかチェック
		Pattern pNumAndAlphabet = Pattern.compile("^[A-Za-z0-9]+$");
		Matcher m = pNumAndAlphabet.matcher(checkingText);
		if (!m.matches()) return "パスワードは半角英数字で入力してください";
		
		return ""; // 全て満たしていれば空で返す
	}
	
	public String findNameError(String checkingText) {
		if (checkingText.length() < 3) return "ニックネームは3文字以上で入力してください";
		if (checkingText.length() > 20) return "パスワードは20文字以内で入力してください";
		
		return ""; // 全て満たしていれば空で返す
	}
}
