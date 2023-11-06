package com.itbulls.learnit.onlinestore.persistence.enteties.store.impl;

import java.util.Objects;

import com.itbulls.learnit.onlinestore.persistence.enteties.store.User;

public class UserForHashTables implements User {

	private static int userCounter = 0;

	private int id;
	private String firstName;
	private String lastName;
	private String password;
	private String email;

	{
		id = ++userCounter;
	}

	public UserForHashTables() {
	}

	public UserForHashTables(String firstName, String lastName, String password, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
	}

	public UserForHashTables(int id, String firstName, String lastName, String password, String email) {
		this.id = id;
		userCounter--; // to keep sequential id
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
	}

	@Override
	public String getFirstName() {
		return this.firstName;
	}

	@Override
	public String getLastName() {
		return this.lastName;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getEmail() {
		return this.email;
	}

	@Override
	public String toString() {
		return "ID: " + this.getId() + "\t\t" +
				"First Name: " + this.getFirstName() + "\t\t" +
				"Last Name: " + this.getLastName() + "\t\t" +
				"Email: " + this.getEmail();
	}

	@Override
	public void setPassword(String password) {
		if (password == null) {
			return;
		}
		this.password = password;
	}

	@Override
	public void setEmail(String newEmail) {
		if (newEmail == null) {
			return;
		}
		this.email = newEmail;
	}

	@Override
	public int getId() {
		return this.id;
	}

	void clearState() {
		userCounter = 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, id, lastName, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserForHashTables other = (UserForHashTables) obj;
		return Objects.equals(email, other.email)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(id, other.id)
				&& Objects.equals(lastName, other.lastName)
				&& Objects.equals(password, other.password);
	}

	@Override
	public void setFirstName(String firstName) {
		
		
	}

	@Override
	public void setLastName(String lastName) {
		
		
	}

	@Override
	public void setId(int id) {
		
		
	}

	@Override
	public String getRoleName() {
		
		return null;
	}

	@Override
	public void setRoleName(String roleName) {
		
		
	}

	@Override
	public double getMoney() {
		
		return 0;
	}

	@Override
	public void setMoney(double money) {
		
		
	}

	@Override
	public String getCreditCard() {
		
		return null;
	}

	@Override
	public void setCreditCard(String creditCard) {
		
		
	}


}