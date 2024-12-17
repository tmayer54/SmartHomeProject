/**
 * @file Couple.java
 * @date 14/12/2022
 * Used to group the persons in the house
 */
package model;

import java.util.ArrayList;

public class Family {

	private int money; // money of the family
	private final ArrayList<Person> persons; // persons members of the family

	/**
	 * Constructors
	 */
	public Family() {
		this.money = 500;
		this.persons = new ArrayList<>();
	}
	
	/**
	 * Adds a person to the couple
	 * @param person : The person to add
	 */
	public void addPerson(Person person) {
		this.persons.add(person);
	}

	/**
	 * Getters and setters
	 */
	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public ArrayList<Person> getPersons() {
		return persons;
	}
	public Person getPersonsFromId(int id) {
		Person result = null;
		for (Person person : this.persons) {
			if (id == person.getId()) {
				result = person;
			}
		}
		return result;
	}
}
