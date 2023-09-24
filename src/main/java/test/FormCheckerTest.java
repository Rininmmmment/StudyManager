package test;

import model.FormChecker;

public class FormCheckerTest {
	public static void main(String[] args) {
		FormChecker formChecker = new FormChecker();
		System.out.println(formChecker.findEmailError("rin@@@@gmail.com"));
		System.out.println(formChecker.findPassError("krukb8dog"));
		System.out.println(formChecker.findNameError("rin@@@@gmail.com"));
	}
}
