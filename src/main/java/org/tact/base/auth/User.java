package org.tact.base.auth;

public class User {

	private String firstName;
	
	private String lastName;
	
	private String city;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public User(){
		
	}
	
	public User(String firstName, String lastName, String city){
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
	}
	
	public User(String firstName){
		this.firstName = firstName;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName
				+ ", city=" + city + "]";
	}	
}
