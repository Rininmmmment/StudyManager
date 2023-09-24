package model;
import java.io.Serializable;

public class Account implements Serializable {
	private int ID;
	private String email;
	private String pass;
	private String name;
	
	public Account() { }
	public Account(int ID, String email, String pass, String name) {
		this.ID = ID;
		this.email = email;
		this.pass = pass;
		this.name = name;
	}
	
	public int getID() { return ID; }
	public void setID(int ID) { this.ID = ID; }
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	public String getPass() { return pass; }
	public void setPass(String pass) { this.pass = pass; }
	public String getName() { return name; }
	public void setName(String pass) { this.pass = name; }
}