package hw15.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileManager {
    private String fileReadName = "src/main/resources/data.txt";
    private String newFileName = "src/main/resources/writeText.txt";
    private File file = new File(fileReadName);
    private String[] songArray;
    private List<String> listOfAllWords = new ArrayList<>();
    private static final int NUMBER_OF_POPULAR_WORDS = 10;
    private static final int LETTERS_LIMIT = 3;

    public void countWords() throws IOException {
        FileReader fileReader = new FileReader(fileReadName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        int numberOfWords = 0;
        while ((line = bufferedReader.readLine()) != null) {
            songArray = line.split(" ");
            numberOfWords = numberOfWords + songArray.length;
        }
        System.out.println("The quantity of all words in the song :" + numberOfWords);
        bufferedReader.close();
    }

    public void writeShortWordsToNewFile() throws IOException {
        FileReader fileReader = new FileReader(fileReadName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        FileWriter fileWriter = new FileWriter(newFileName);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String line;
        int numberOfWords = 0;
        while ((line = bufferedReader.readLine()) != null) {
            String[] shortWordsArray = line.split(" ");

            for (String s : shortWordsArray) {
                if (s.length() <= LETTERS_LIMIT && !s.equals("" + "")) {
                    bufferedWriter.write(s + "\n");
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
        FileReader fileReader = new FileReader(newFileName);
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
        FileReader fileReader = new FileReader(fileReadName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            songArray = line.split(" ");
            int i = 0;
            for (String s : songArray) {
                if (!s.equals("" + "")) {
                    listOfAllWords.add(i, s.toLowerCase());
                }
                i++;
            }
        }
        bufferedReader.close();
    }

    public void showMostCommonWords() {
        Map<String, Long> mostCommonWords = listOfAllWords.stream()
                .collect(Collectors.groupingBy(listOfAllWords -> listOfAllWords, Collectors.counting()));

        List<Map.Entry<String, Long>> collect = mostCommonWords.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .collect(Collectors.toList());

        System.out.println("10 most common words: ");
        for (int i = 0; i < NUMBER_OF_POPULAR_WORDS; i++) {
            {
                System.out.println(collect.get(i));
            }
        }
    }
}
