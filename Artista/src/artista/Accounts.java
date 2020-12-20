package artista;

import java.util.*;

public class Accounts {

	private final int userIDNum;
	private String userName;
	private String password;
    private String firstName;
	private String lastName;
	private String email;
	List <Image> Images = new ArrayList<Image>();
	
	//for accounts that are in the database that already have userIDNum.
	public Accounts(int userID, String un, String pw, String fn, String ln, String email) {
		this.userIDNum = userID;
		this.userName =  un;
		this.password = pw;
		this.firstName = fn;
		this.lastName = ln;
		this.email = email;
	
	}
	
	public int getUserIDNum() {
		return this.userIDNum;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setUserName(String un) {
		this.userName = un;
	}
	
	public void setFirstName(String fn) {
		this.firstName = fn;
	}
	
	public void setLastName(String ln) {
		this.lastName = ln;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
