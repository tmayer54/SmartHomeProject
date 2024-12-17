/**
 * @file Perk.java
 * @date 14/12/2022
 * Describes a perk used to upgrade the house
 */
package model;

public record Perk (int ID, String name, int installationCost, int dailyMoneyCost, int dailyEnergyCost) {

	/**
	 * Stringifies the perk to display it
	 * @return the main infos of the perk as a String
	 */
	public String toString() {
		return name + " | " +" cost: " + installationCost + ", daily money cost: " + dailyMoneyCost + " daily energy cost: " + dailyEnergyCost ;
	}
}
