package main;

import utils.LoadSave;
import view.GUIManager;

public class Main {
    public static void main(String[] args) {
    	LoadSave.CreateFile();
        GUIManager GUIManager = new GUIManager();
        GUIManager.start();
    }
}
