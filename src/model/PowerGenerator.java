/**
 * @file PowerGenerator.java
 * @date 14/12/2022
 * Describes a daily energy source
 */
package model;

public class PowerGenerator {

	private final String name; // name of the power generator
	private final int installationCost; // money cost of installation
	private final int dailyCost; // daily money cost
	private final int dailyProduction; // daily energy production

	/**
	 * Class constructor
	 * @param name: name of the power generator
	 * @param installationCost: money cost of installation
	 * @param dailyCost: daily money cost
	 * @param dailyProduction: daily energy production
	 */
	public PowerGenerator(String name, int installationCost, int dailyCost, int dailyProduction) {
		this.name = name;
		this.installationCost = installationCost;
		this.dailyCost = dailyCost;
		this.dailyProduction = dailyProduction;
	}

	/**
	 * Getters and setters
	 */
	public int getDailyCost() {
		return dailyCost;
	}

	public int getDailyProduction() {
		return dailyProduction;
	}
}
