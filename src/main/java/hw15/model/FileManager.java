package hw15.model;

import lombok.*;

import java.io.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor

public class FileManager {
    String fileReadName = "src/main/resources/data.txt";
    String fileWriteName = "src/main/resources/writeText.txt";
    File file = new File(fileReadName);
    String[] songArray;
    String[] shortWordsArray;

    public void countWords() throws IOException {
        FileReader fileReader = new FileReader(fileReadName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        int numberOfWords = 0;
        while ((line = bufferedReader.readLine()) != null) {
            songArray = line.split(" ");
            numberOfWords = numberOfWords + songArray.length;
        }
        fileReader.close();
        System.out.println("The quantity of all words in the song :" + numberOfWords);
    }

    public void writeShortWords() throws IOException {
        FileReader fileReader = new FileReader(fileReadName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        FileWriter fileWriter = new FileWriter(fileWriteName);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String line;
        int numberOfWords = 0;
        while ((line = bufferedReader.readLine()) != null) {
            shortWordsArray = line.split(" ");

            for (String s : shortWordsArray) {
                if (s.length() <= 3 && !s.equals("" + "")) {
                    bufferedWriter.write(s + "\n");
                    bufferedWriter.flush();

                    numberOfWords++;

                }
            }
        }
        System.out.println("The quantity of short words in the song :" + numberOfWords);
    }

    public void showMostCommonWords() {
        System.err.println("Ну привіт, Маркіян. Як справи?");
    }
}
