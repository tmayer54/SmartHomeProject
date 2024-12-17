/**
 * @file Home.java
 * @date 18/12/2022
 * Used to represent the house, with the rooms and persons
 */
package model;

import java.util.ArrayList;
import static java.lang.Math.round;

public class House {

	private float temperature; // temperature inside the house
	private float humidityRate; // humidity rate inside the house
	private int energy; // energy available in the house
	// private boolean isOnPowerOutage; // power outage, not yet implemented
	private final ArrayList<Room> rooms; // rooms of the house
	private final ArrayList<PowerGenerator> powerSupply; // daily energy supplies of the house
	private final Family family; // members of the house
	private final float optimalTemperature; // optimal viable temperature inside
	private final float optimalHumidityRate; // optimal viable humidity rate inside
	
	/**
	 * Class constructor
	 */
	public House(float temperature, float humidityRate, int energy, ArrayList<Room> rooms, ArrayList<PowerGenerator> powerSupply, Family family, float optimalTemperature, float optimalHumidityRate) {
		this.temperature = temperature;
		this.humidityRate = humidityRate;
		this.energy = energy;
		// this.isOnPowerOutage = isOnPowerOutage;
		this.rooms = rooms;
		this.powerSupply = powerSupply;
		this.family = family;
		this.optimalTemperature = optimalTemperature;
		this.optimalHumidityRate = optimalHumidityRate;
	}
	
	/**
	 * Tells if the home is still viable or not, used to determine if the player can keep playing
	 * @return "Viable" if the house is still viable, the reason of the non-viability as a String otherwise.
	 */
	public String isViable() {
		if (temperature > optimalTemperature + 10) {
			return "Temperature too high: " + round(temperature * 10) + "°C";
		}
		if (temperature < optimalTemperature - 10) {
			return "Temperature too low: " + round(temperature * 10) + "°C";
		}
		if (humidityRate > optimalHumidityRate + .2) {
			return "Humidity too high: " + round(humidityRate * 100) + "%";
		}
		if (humidityRate < optimalHumidityRate - .2) {
			return "Humidity too low: " + round(humidityRate * 100)+ "%";
		}
		if (this.family.getMoney() < 0) {
			return "Not enough money to continue";
		}
		if (this.energy < 0) {
			return "Not enough energy to continue";
		}
		return "Viable";
	}

	/**
	 * Updates all the house elements according to the current weather and perks
	 * @param weather: weather of the environment
	 */
	public void update(Weather weather, ArrayList<Perk> perks) {
		this.temperature = 0;
		this.humidityRate = 0;

		for (Room room: rooms) {
			room.update(weather.getHumidityRate(), weather.getTemperature());
			this.temperature += room.getTemperature() / this.rooms.size();
			this.humidityRate += room.getHumidityRate() / this.rooms.size();
		}
		/*
		if (weather.isLightning() && Math.random() < .1) {
			this.isOnPowerOutage = true;
		}
		*/
		for (Perk perk: perks) {
			switch (perk.ID()) {
				case 0: // automatic windows
					this.setAllWindowsOpen(temperature < this.optimalTemperature && temperature < weather.getTemperature() ||
											temperature > this.optimalTemperature && temperature > weather.getTemperature()); // if the temperature is better outside
				case 1: // automatic heaters and AC
					if (this.rooms.get(0).isWindowOpen()) {
						break;
					}
					if (temperature < this.optimalTemperature) {
						this.setAllHeatersTemperature(this.optimalTemperature);
						this.turnOffAllAC();
					} else {
						this.setAllACTemperature(this.optimalTemperature);
						this.turnOffAllHeaters();
					}
					break;
			}
		}

		for (Room room: rooms) {
			if (room.isHeaterTurnedOn() || room.isACTurnedOn()) {
				this.energy -= 5;
			}
		}

	}

	/**
	 * Opens all the windows of each rooms
	 * @param status: true if the windows will be open, false otherwise
	 */
	public void setAllWindowsOpen(boolean status) {
		this.rooms.forEach(room -> room.setWindowOpen(status));
	}

	/**
	 * Turns on all the heaters of the house at a certain temperature
	 * @param temperature: temperature of the heaters
	 */
	public void setAllHeatersTemperature(float temperature) {
		this.rooms.forEach(room -> {
			room.setHeaterTemperature(temperature);
			room.setHeaterTurnedOn(true);
		});
	}

	/**
	 * Turns off all the heaters of the house
	 */
	public void turnOffAllHeaters() {
		this.rooms.forEach(room -> room.setHeaterTurnedOn(false));
	}

	/**
	 * Turns on all the ACs of the house at a certain temperature
	 * @param temperature: temperature of the ACs
	 */
	public void setAllACTemperature(float temperature) {
		this.rooms.forEach(room -> {
			room.setACTemperature(temperature);
			room.setACTurnedOn(true);
		});
	}

	/**
	 * Turns off all the ACs of the house
	 */
	public void turnOffAllAC() {
		this.rooms.forEach(room -> room.setACTurnedOn(false));
	}

	/**
	 * Called at each new day
	 */
	public void onNewDay(ArrayList<Perk> perks) {
		this.powerSupply.forEach(powerGenerator -> {
			this.energy += powerGenerator.getDailyProduction();
			this.family.setMoney(this.family.getMoney() - powerGenerator.getDailyCost());
		});
		this.getFamily().getPersons().forEach(Person::onNewDay);
		perks.forEach(perk -> {
			this.energy -= perk.dailyEnergyCost();
			this.family.setMoney(this.family.getMoney() - perk.dailyMoneyCost());
		});
	}

	public void addPowerSupply(PowerGenerator powerGenerator) {
		this.powerSupply.add(powerGenerator);
	}

	/**
	 * Stringifies the house to display
	 * @return the main infos of the house as a String
	 */
	public String toString() {
		String windowsStatus = this.rooms.get(0).isWindowOpen() ? "Open" : "Closed";
		String heatersStatus = this.rooms.get(0).isHeaterTurnedOn() ? this.rooms.get(0).getHeaterTemperature() + "°C" : "Off";
		String ACStatus = this.rooms.get(0).isACTurnedOn() ? this.rooms.get(0).getACTemperature() + "°C" : "Off";
		return "House - T: " + (float)round(temperature * 10) / 10 + "°C, humidity: " + round(humidityRate * 100) + "%, windows: " + windowsStatus + ", heaters: " + heatersStatus + ", AC: " + ACStatus;
	}

	/**
	 * Getters and setters
	 */
	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}
	
	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}
	
	public void setHumidityRate(float humidityRate) {
		this.humidityRate = humidityRate;
	}
	
	public float getTemperature() {
		return this.temperature;
	}

	public float getHumidityRate() {
		return this.humidityRate;
	}
	
	public Family getFamily() {
		return family;
	}

	public ArrayList<Room> getRooms() { return rooms; }
	
	public ArrayList<PowerGenerator> getPowerSupply() { return powerSupply; }
}
