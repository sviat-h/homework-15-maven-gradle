package hw15.model;

import lombok.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor

public class FileManager {
    String fileName = "src/main/resources/data.txt";
    String[] allWords;
    ArrayList<String> shortWords;
    String split;
    FileReader songReader;


    public void countAllWords() throws IOException {
        FileReader songReader = new FileReader(fileName);
        BufferedReader br = new BufferedReader(songReader);
        int wc = 0;


        while ((split = br.readLine()) != null) {
            allWords = split.split(" ");
            wc = wc + allWords.length;
        }

        songReader.close();
        System.out.println("Number of words in the file:" + wc);
    }

    public void countShortWords() {
    }
}
