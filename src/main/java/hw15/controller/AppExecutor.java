package hw15.controller;

import hw15.model.FileManager;

import java.io.IOException;

public class AppExecutor {
    private FileManager fileManager = new FileManager();

    public  void method() {
        try {
            fileManager.countWords();
            fileManager.writeShortWords();
            fileManager.showMostCommonWords();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}