/**
 * @file Weather.java
 * @date 18/12/2022
 * Represents the weather of the environment outside the house
 */
package model;

import java.lang.Math;
import static java.lang.Math.round;

public class Weather {

	private float temperature; // temperature of the environment
	private float humidityRate; // humidity rate of the environment
	private boolean isRainy; // tells if it is currently raining outside
	private boolean isSnowy; // tells if it is currently snowing outside
	private boolean isSunny; // tells if it is currently sunny outside
	private boolean isLightning; // tells if there are lightnings outside
	private float difficultyRate; // difficulty of the current weather, makes weather variations smaller of bigger

	/**
	 * Class constructor
	 */
	public Weather() {
		this.temperature = 20f;
		this.humidityRate = 0.5f;
		this.isRainy = false;
		this.isSnowy = false;
		this.isSunny = false;
		this.isLightning = false;
	}

	/**
	 * class constructor when load from a save
	 * @param temperature
	 * @param humidityRate
	 * @param isRainy
	 * @param isSnowy
	 * @param isSunny
	 * @param isLightning
	 */
	public Weather(float temperature, float humidityRate) {
		this.temperature = temperature;
		this.humidityRate = humidityRate;
		this.isRainy = false;
		this.isSnowy = false;
		this.isSunny = false;
		this.isLightning = false;
	}
	
	/**
	 * Update the weather according to the day number
	 * @param dayNumber: current day of the game
	 */
	public void update(int dayNumber) {
		this.updateDifficultyRate(dayNumber);

		boolean wasRainy = this.isRainy;
		this.setRainy(Math.random() < this.difficultyRate / 2);
		this.setSnowy(this.isRainy && this.temperature <= 0);
		this.setSunny(!this.isRainy);
		this.setLightning(false);

		int temperatureChangeSign = Math.random() < .5 ? -1 : 1;

		this.temperature += temperatureChangeSign * Math.random() * this.difficultyRate * 20;
		this.temperature += this.isSunny && wasRainy ? 5 : this.isRainy && !wasRainy ? -5 : 0;

		if (this.isRainy) {
			this.humidityRate = 1f;
			this.setLightning(Math.random() < this.difficultyRate / 4);
		} else if (wasRainy) {
			this.humidityRate = .5f;
		} else {
			this.humidityRate += temperatureChangeSign * Math.random() * this.difficultyRate * .1;
		}
	}

	/**
	 * Update The difficulty of the game according to the day. The difficulty increases over the days
	 * Uses logistic function
	 * @param dayNumber: current day of the game
	 */
	private void updateDifficultyRate(int dayNumber) {
		this.difficultyRate = (float) (1 / (1 + Math.exp(-.5 * (dayNumber - 5))));
	}

	/**
	 * Stringifies the weather to display it
	 * @return the infos of the weather as a String
	 */
	public String toString() {
		String sky = isSnowy ? "Snowy" : isLightning ? "Lightnings" : isRainy ? "Rainy" : "Sunny";
		return "Weather - T : " + (float)round(temperature * 10) / 10 + "°C, humidity : " + round(humidityRate * 100) + "%, Sky: " + sky;
	}

	/**
	 * Getters and setters
	 */
	public float getTemperature() {
		return this.temperature;
	}

	public float getHumidityRate() {
		return this.humidityRate;
	}

	public void setRainy(boolean rainy) {
		this.isRainy = rainy;
	}

	public void setSnowy(boolean snowy) {
		this.isSnowy = snowy;
	}

	public void setSunny(boolean sunny) {
		this.isSunny = sunny;
	}

	public boolean isLightning() {
		return this.isLightning;
	}
	
	public boolean isSunny() {
		return this.isSunny;
	}
	
	public boolean isSnowy() {
		return this.isSnowy;
	}
	
	public boolean isRainy() {
		return this.isRainy;
	}

	public void setLightning(boolean lightning) {
		this.isLightning = lightning;
	}
}
