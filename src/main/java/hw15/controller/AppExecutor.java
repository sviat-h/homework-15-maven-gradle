package hw15.controller;

import hw15.model.FileManager;

import java.io.IOException;

public class AppExecutor {
    private FileManager fileManager = new FileManager();

    public  void runProgram() {
        try {
            fileManager.countWords();
            fileManager.writeShortWordsToNewFile();
            fileManager.readShortWordsFromNewFile();
            fileManager.writeAllWordsToList();
            fileManager.showMostCommonWords();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
