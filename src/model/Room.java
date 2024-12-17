/**
 * @file Room.java
 * @date 18/12/2022
 * Describes a room of the house
 */
package model;

import java.lang.Math;

public class Room {

	private final String name; // name of the room
	private float heaterTemperature; // temperature of the heaters
	private boolean isHeaterTurnedOn; // tells if the heater is on
	private float ACTemperature; // temperature of the AC
	private boolean isACTurnedOn; // tells if the AC is on
	private float temperature; // temperature of the room
	private float humidityRate; // humidity of the room ([0,1])
	private float isolationRate; // isolation rate of the walls ([0,1])
	private boolean isWindowOpen; // tells if the window is open

	/**
	 * Class constructors
	 */
	public Room(String name, float temperature, float humidityRate) {
		this.name = name;
		this.heaterTemperature = 0f;
		this.isHeaterTurnedOn = false;
		this.ACTemperature = 0f;
		this.isACTurnedOn = false;
		this.temperature = temperature;
		this.humidityRate = humidityRate;
		this.isolationRate = .5f;
		this.isWindowOpen = false;
	}

	/**
	 * Used to update the stats of the room at each task
	 * @param outsideHumidityRate: humidity rate outside the house
	 * @param outsideTemperature: temperature outside the house
	 */
	public void update(float outsideHumidityRate, float outsideTemperature) {
		if (this.isWindowOpen) {
			this.humidityRate = (this.humidityRate + outsideHumidityRate) / 2;
			this.temperature = (this.temperature + outsideTemperature) / 2;
		} else {
			this.temperature = isHeaterTurnedOn && heaterTemperature > this.temperature ? (this.temperature + this.heaterTemperature) / 2 : this.temperature;
			this.temperature = isACTurnedOn && ACTemperature < this.temperature ? (this.temperature + this.ACTemperature) / 2 : this.temperature;
			// Loss of humidity and temperature due to non-optimal isolation
			this.humidityRate = Math.max(Math.min(this.humidityRate + (1 - this.isolationRate) * (outsideHumidityRate - this.humidityRate) * .1f, 1), 0);
			this.temperature = this.temperature + (1 - this.isolationRate) * (outsideTemperature - this.temperature) * .2f;
		}
	}

	/**
	 * Stringifies the room
	 * @return the infos of the room as a String
	 */
	public String toString() {
		return "Room:\n" +
				"name='" + name + '\'' +
				", heaterTemperature=" + heaterTemperature +
				", isHeaterTurnedOn=" + isHeaterTurnedOn +
				", temperature=" + temperature +
				", humidityRate=" + humidityRate +
				", isolationRate=" + isolationRate +
				", isWindowOpen=" + isWindowOpen;
	}

	/**
	 * Getters and setters
	 */
	public float getHeaterTemperature() {
		return heaterTemperature;
	}

	public void setHeaterTemperature(float heaterTemperature) {
		this.heaterTemperature = heaterTemperature;
	}

	public boolean isHeaterTurnedOn() {
		return isHeaterTurnedOn;
	}

	public void setHeaterTurnedOn(boolean heaterTurnedOn) {
		isHeaterTurnedOn = heaterTurnedOn;
	}

	public float getTemperature() {
		return temperature;
	}

	public float getHumidityRate() {
		return humidityRate;
	}

	public boolean isWindowOpen() {
		return isWindowOpen;
	}

	public void setWindowOpen(boolean windowOpen) {
		isWindowOpen = windowOpen;
	}

	public float getACTemperature() {
		return ACTemperature;
	}

	public void setACTemperature(float ACTemperature) {
		this.ACTemperature = ACTemperature;
	}

	public boolean isACTurnedOn() {
		return isACTurnedOn;
	}

	public void setACTurnedOn(boolean ACTurnedOn) {
		isACTurnedOn = ACTurnedOn;
	}

	public void setIsolationRate(float isolationRate) { this.isolationRate = isolationRate; }
}
