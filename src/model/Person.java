/**
 * @file Person.java
 * @date 14/12/2022
 * Describes a member of the house
 */
package model;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;


public class Person {

	private final String name; // name of the person
	private final int id; // ID of the person
	private final int maxStamina; // max stamina in a day
	private int stamina; // current stamina of the day
	private final ArrayList<Task> tasks; // daily tasks to execute
	
	/**
	 * Class constructor
	 * @param name: name of the person
	 * @param id: ID of the person
	 * @param maxStamina: maximum stamina in a day
	 */
	public Person(String name, int id, int maxStamina) {
		this.name = name;
		this.id = id;
		this.maxStamina = maxStamina;
		this.stamina = maxStamina;
		this.tasks = new ArrayList<>();
	}

	/**
	 * Adds a task to the ArrayList link to the Person
	 * @param task: Task to add
	 */
	public void addTask(Task task)	{
		if (task.stamina() >= 0) {
			AtomicBoolean taskAlreadyPicked = new AtomicBoolean(false);
			this.tasks.forEach(pickedTask -> {
				if(pickedTask.ID() == task.ID()) {
					taskAlreadyPicked.set(true);
				}
			});
			if (taskAlreadyPicked.get()) {
				return;
			}
		}
		this.tasks.add(task);
	}

	/**
	 * Called at each new day, resets tasks and stamina
	 */
	public void onNewDay() {
		this.stamina = this.maxStamina;
		this.tasks.clear();
	}

	/**
	 * Stringifies the person to display it
	 * @return the main infos of the person as a String
	 */
	public String toString() {
		return name + " - Stamina: " + stamina + ", max stamina: " + maxStamina;
	}

	/**
	 * Getters and setters
	 */
	public int getResultingStaminaForCurrentTasks() {
		int result = 0;
		for (Task task: this.tasks) {
			result += task.stamina(); // - : necessary stamina are negative values
		}
		return result;
	}
	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public int getMaxStamina() {
		return maxStamina;
	}

	public int getStamina() {
		return stamina;
	}

	public void setStamina(int stamina) {
		this.stamina = stamina;
	}

	public ArrayList<Task> getTasks() {
		return tasks;
	}
}
