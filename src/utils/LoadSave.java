package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import model.Game;
import model.Weather;

public class LoadSave {

	/**
	 * @brief Create a new file named game.txt if this file does not exist yet
	 */
	public static void CreateFile() {
		File txtFile = new File("game.txt");

		try {
			txtFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void WriteToFile(File f, int[] idArr) {
		try {
			PrintWriter pw = new PrintWriter(f);
			for (Integer i : idArr)
				pw.println(i);

			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void SaveLevel(String name, int[] idArr) {
		File levelFile = new File(name + ".txt");

		if (levelFile.exists()) {
			WriteToFile(levelFile, idArr);
		} else {
			System.out.println("File:" + name + " does not exists!");
		}

	}

	private static ArrayList<Integer> ReadFromFile(File file) {
		ArrayList<Integer> list = new ArrayList<>();

		try {
			Scanner sc = new Scanner(file);

			while (sc.hasNextLine()) {
				list.add(Integer.parseInt(sc.nextLine()));
			}

			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return list;
	}

	public static Game LoadGame() {
		ArrayList<Integer> save = ReadFromFile(new File("game" + ".txt"));
		float weatherTemperature = (float) save.get(3) / 100;
		float weatherHumidity = (float) save.get(4) / 100;
		Weather weather = new Weather(weatherTemperature, weatherHumidity);
		switch (save.get(5)) {
		case 0 -> weather.setRainy(true);
		case 1 -> weather.setSnowy(true);
		case 2 -> weather.setSunny(true);
		case 3 -> weather.setLightning(true);
		}

		Game game = new Game(save.get(0), weather);
		game.getHouse().getFamily().setMoney(save.get(1));
		game.getHouse().setEnergy(save.get(2));
		game.getHouse().setTemperature((float) save.get(6) / 100);
		game.getHouse().setHumidityRate((float) save.get(7) / 100);
		for (int i = 0; i < save.get(8); i++) {
			game.addPerkFromID(save.get(i + 9));
		}
		return game;
	}
}
