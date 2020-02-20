package hw15.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileManager {
    private static final String PATH_TO_FILE = "src/main/resources/data.txt";
    private static final String PATH_TO_DESTINATION_FILE = "src/main/resources/writeText.txt";
    private File file = new File(PATH_TO_FILE);
    private String[] wordsArray;
    private List<String> listOfAllWords = new ArrayList<>();

    private static final String WORD_DIVIDER = " ";
    private static final int NUMBER_OF_POPULAR_WORDS = 10;
    private static final int LETTERS_LIMIT = 3;

    public void countWords() throws IOException {
        FileReader fileReader = new FileReader(PATH_TO_FILE);

        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        int numberOfWords = 0;

        while ((line = bufferedReader.readLine()) != null) {
            wordsArray = line.split(WORD_DIVIDER);
            numberOfWords += wordsArray.length;
        }

        System.out.println("The quantity of all words in the song :" + numberOfWords);

        bufferedReader.close();
    }

    public void writeShortWordsToNewFile() throws IOException {
        FileReader fileReader = new FileReader(PATH_TO_FILE);

        BufferedReader bufferedReader = new BufferedReader(fileReader);

        FileWriter fileWriter = new FileWriter(PATH_TO_DESTINATION_FILE);

        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String line;
        int numberOfWords = 0;

        while ((line = bufferedReader.readLine()) != null) {
            String[] shortWordsArray = line.split(WORD_DIVIDER);

            for (String shortWord : shortWordsArray) {
                if (shortWord.length() <= LETTERS_LIMIT && !shortWord.isEmpty()) {
                    bufferedWriter.write(shortWord + "\n");
                    bufferedWriter.flush();

                    numberOfWords++;

                }
            }
        }

        System.out.println("The quantity of short words in the song :" + numberOfWords + "\n");

        bufferedReader.close();
        bufferedWriter.close();
    }

    public void readShortWordsFromNewFile() throws IOException {
        FileReader fileReader = new FileReader(PATH_TO_DESTINATION_FILE);

        BufferedReader bufferedReader = new BufferedReader(fileReader);

        System.out.println("Read from a new file: ");

        String line;

        while ((line = bufferedReader.readLine()) != null) {
            System.out.print(line + ", ");
        }

        System.out.println("\n");
        bufferedReader.close();
    }

    public void writeAllWordsToList() throws IOException {
        FileReader fileReader = new FileReader(PATH_TO_FILE);

        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;

        while ((line = bufferedReader.readLine()) != null) {
            wordsArray = line.split(WORD_DIVIDER);

            int counter = 0;

            for (String word : wordsArray) {
                if (!word.isEmpty()) {
                    listOfAllWords.add(counter, word.toLowerCase());
                }
                counter++;
            }
        }
        bufferedReader.close();
    }

    public void showMostCommonWords() {
        Map<String, Long> wordToAmount = listOfAllWords.stream()
                .collect(Collectors.groupingBy(listOfAllWords -> listOfAllWords, Collectors.counting()));

        List<Map.Entry<String, Long>> collect = wordToAmount.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(NUMBER_OF_POPULAR_WORDS)
                .collect(Collectors.toList());

        System.out.println("10 most common words: \n" + collect);
    }
}
