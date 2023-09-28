package test;

import java.sql.Date;
import java.sql.Time;

import model.FormChecker;

public class FormCheckerTest {
	public static void main(String[] args) {
		FormChecker formChecker = new FormChecker();
		System.out.println(formChecker.findEmailError("rin@@@@gmail.com"));
		System.out.println(formChecker.findPassError("krukb8dog"));
		System.out.println(formChecker.findNameError("rin@@@@gmail.com"));
		System.out.println(formChecker.findDateError(Date.valueOf("2023-09-29")));
		System.out.println(formChecker.findTimeError(Time.valueOf("16:00:00"), Time.valueOf("18:00:00")));
	}
}
