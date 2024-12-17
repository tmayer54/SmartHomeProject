/**
 * @file Menu.java
 * @date 18/12/2022
 * Core of the game model
 */
package model;

import java.util.ArrayList;
import java.util.Objects;

public class Game {

	private final House house; // house where the persons live
	private final Weather weather; // outside weather
	private int dayNumber; // current day number in the game
	private final ArrayList<Task> availableTasks; // available tasks the user can pick
	private final ArrayList<Perk> availablePerks; // available perks
	private final ArrayList<Perk> boughtPerks; // perks bought
	private String gameOverReason; // filled when the game is over by the reason

	/**
	 * Class constructor
	 */
	public Game() {
		this.house = initHouse();
		this.weather = new Weather();
		this.dayNumber = 0;
		this.availableTasks = new ArrayList<>();
		this.availablePerks = new ArrayList<>();
		this.boughtPerks = new ArrayList<>();
		this.gameOverReason = "";
		initTasks();
		initPerks();
	}
	
	/**
	 * class constructor when load from a save
	 * @param dayNumber: current day number
	 * @param weather: weather of the environment
	 */
	public Game(int dayNumber, Weather weather) {
		this.house = initHouse();
		this.weather = weather;
		this.dayNumber = dayNumber;
		this.availableTasks = new ArrayList<>();
		this.availablePerks = new ArrayList<>();
		this.boughtPerks = new ArrayList<>();
		
		initTasks();
		initPerks();
	}

	/**
	 * Updates the house and the weather on a new day
	 * @return True if the house is still viable, false otherwise
	 */
	public boolean onNewDay() {
		String viability = this.house.isViable();
		if (!Objects.equals(viability, "Viable")) {
			this.gameOverReason = viability;
			return false;
		}
		this.dayNumber++;
		this.house.onNewDay(this.boughtPerks);
		this.weather.update(this.dayNumber);
		return true;
	}

	/**
	 * Instantiates the rooms, power generators, available perks and family
	 */
	private House initHouse() {
		ArrayList<Room> rooms = new ArrayList<>();
		rooms.add(new Room("Kitchen", 18, .4f));
		rooms.add(new Room("Bedroom", 18, .4f));
		rooms.add(new Room("Living room", 18, .4f));
		rooms.add(new Room("Bathroom", 18, .4f));
		rooms.add(new Room("Office", 18, .4f));

		ArrayList<PowerGenerator> powerGenerators = new ArrayList<>();
		powerGenerators.add(new PowerGenerator("Linky", 0, 100, 100));

		Family family = new Family();
		family.addPerson(new Person("Jean", 0, 10));
		family.addPerson(new Person("Marie", 1, 10));

		return new House(18f, .4f, 100, rooms, powerGenerators, family, 21f, .45f);
	}

	/**
	 * Instantiates all the available tasks
	 */
	private void initTasks() {
		availableTasks.add(new Task(0, "Heater On", "Turning heater On.", -1, 0, 0));
		availableTasks.add(new Task(1, "Heater Off", "Turning heater Off.", -1, 0, 0));
		availableTasks.add(new Task(2, "AC On", "Turning AC On.", -1, 0, 0));
		availableTasks.add(new Task(3, "AC Off", "Turning AC Off.", -1, 0, 0));
		availableTasks.add(new Task(4, "Open windows", "Opening windows.", -1, 0, 0));
		availableTasks.add(new Task(5, "Close windows", "Closing closing windows.", -1, 0, 0));
		availableTasks.add(new Task(6, "Work", "Currently working.", -5, 200, 0));
		availableTasks.add(new Task(7, "Sleep", "Sleeping.", 2, 0, 0));
		availableTasks.add(new Task(8, "Biking", "Biking.", -4, 0, 50));
		availableTasks.add(new Task(9, "Cook", "Cooking and eating.", 1, -50, -30));
		// availableTasks.add(new Task(10, "Repair Outage", "Repairing power Outage.", -2, -200, 0)); // power outage not yet implemented
	}

	/**
	 * Instantiates the perks
	 */
	private void initPerks() {
		this.availablePerks.add(new Perk(0, "Automatic windows", 1000, 100, 100));
		this.availablePerks.add(new Perk(1, "Automatic AC and heaters", 1500, 150, 100));
		this.availablePerks.add(new Perk(2, "Better mattress", 1000, 0, 0));
		this.availablePerks.add(new Perk(3, "Cooking robot", 1000, 0, 50));
		this.availablePerks.add(new Perk(4, "Solar panels (100 daily energy)", 2000, 0, 0)); // power generators are special perks
		this.availablePerks.add(new Perk(5, "Wind turbine (100 daily energy)", 2000, 0, 0));
		this.availablePerks.add(new Perk(6, "Isolate rooms at 65%", 2000, 0, 0));
		this.availablePerks.add(new Perk(7, "Isolate rooms at 90%", 5000, 0, 0));
	}

	/**
	 * True if the user can afford the perk
	 * @param perk: perk to test
	 * @return true if he can afford it false otherwise
	 */
	private boolean canAffordPerk(Perk perk) {
		return perk.installationCost() <= this.house.getFamily().getMoney();
	}


	/**
	 * Buys a perk from its id. Has different effect depending on the perk
	 * @param id: id of the perk to buy
	 */
	public void buyPerkFromId(int id) {
		for (Perk perk: this.availablePerks) {
			if (perk.ID() == id) {
				if (this.canAffordPerk(perk)) {
                    addPerkFromID(id);
                    this.house.getFamily().setMoney(this.house.getFamily().getMoney() - perk.installationCost());
				}
				break;
			}
		}
	}

	/**
	 * Adds a perk from its id
	 * @param id: id of the perk
	 */
	public void addPerkFromID(int id) {
		for (Perk perk: this.availablePerks) {
			if (perk.ID() == id) {
				this.availablePerks.remove(perk);
				this.boughtPerks.add(perk);
				switch (perk.ID()) {
					case 2 -> this.availableTasks.set(this.availableTasks.indexOf(this.findTaskFromId(7)), new Task(7, "Sleep", "Sleeping.", 3, 0, 0));
					case 3 -> this.availableTasks.set(this.availableTasks.indexOf(this.findTaskFromId(9)), new Task(9, "Cook", "Cooking and eating.", 3, -50, -30));
					case 4 -> this.house.addPowerSupply(new PowerGenerator("Solar panel", 2000, 50, 100));
					case 5 -> this.house.addPowerSupply(new PowerGenerator("Wind turbine", 2000, 50, 100));
					case 6 -> {
						this.house.getRooms().forEach(room -> room.setIsolationRate(.65f));
						for (Perk availablePerk: this.availablePerks) {
							if (availablePerk.ID() == 7) {
								this.availablePerks.set(this.availablePerks.indexOf(availablePerk), new Perk(7, "Isolate rooms at 90%", 2500, 0, 0));
							}
						}
					}
					case 7 -> {
						this.house.getRooms().forEach(room -> room.setIsolationRate(.9f));
						this.availablePerks.removeIf(availablePerk -> availablePerk.ID() == 6); // remove lower isolation level is the player bought the higher one
					}
				}
			    break;
			}
		}
	}
	/**
	 * Finds a Task in all tasks using its ID
	 * @param id: ID of the task
	 * @return the Task corresponding to the ID
	 */
	public Task findTaskFromId(int id) {
		if (id >= 0 && id < availableTasks.size()) {
			for (Task task : availableTasks) {
				if (task.ID() == id) {
					return task;
				}
			}
		} else {
			System.out.println("Error system in Playing : ID not recognized");
		}
		return null;
	}

	/**
	 * Executes the task of every member of the house at the index n of their tasks list
	 * @param n: index of the tasks to execute
	 */
	public void doNthTaskOfAllPersons(int n) {
		this.house.getFamily().getPersons().forEach(person -> {
			if (n < person.getTasks().size()) {
				Task currentTask = person.getTasks().get(n);
				float heaterTemp = 23;
				float ACTemperature = 18; // Hard coded for the moment, has to depend on the UI entry later
				switch (currentTask.ID()) {
					case 0 -> this.house.setAllHeatersTemperature(heaterTemp); // turn on all heaters
					case 1 -> this.house.turnOffAllHeaters(); // turn off all heaters
					case 2 -> this.house.setAllACTemperature(ACTemperature); // turn on all AC
					case 3 -> this.house.turnOffAllAC(); // turn off all AC
					case 4 -> this.house.setAllWindowsOpen(true); // open all windows
					case 5 -> this.house.setAllWindowsOpen(false); // close all windows
					case 6 -> {} // work
					case 7 -> {} // sleep
					case 8 -> {} // bike
					case 9 -> {} // cook
				}
				person.setStamina(person.getStamina() + currentTask.stamina());
				this.house.getFamily().setMoney(this.house.getFamily().getMoney() + currentTask.money());
				this.house.setEnergy(this.house.getEnergy() + currentTask.energy());
			}
		});
		this.house.update(this.weather, this.boughtPerks);
	}

	/**
	 * Getters and setters
	 */
	public House getHouse() {
		return this.house;
	}

	public Weather getWeather() {
		return this.weather;
	}

	public ArrayList<Task> getAvailableTasks() {
		return this.availableTasks;
	}

	public int getDayNumber() {
		return this.dayNumber;
	}

	public String getGameOverReason() {
		return this.gameOverReason;
	}

	public Perk getPerkById(int id) {
		for (Perk perk: this.availablePerks) {
			if (perk.ID() == id) {
				return perk;
			}
		}
		return null;
	}

	public ArrayList<Perk> getAvailablePerks() {
		return this.availablePerks;
	}

	public ArrayList<Perk> getBoughtPerks() {
		return this.boughtPerks;
	}
}