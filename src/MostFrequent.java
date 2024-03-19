import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MostFrequent {

    private static List<String> ReadFile(String fileName){
        List<String> words = new ArrayList<>();
        BufferedReader reader = null;

        try {
            // Create a new FileReader
            FileReader fileReader = new FileReader(fileName);
            reader = new BufferedReader(fileReader);
            String line;

            while ((line = reader.readLine()) != null) {
                words.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return words;
    }

    private static List<String> GenerateDailyWords(List<String> learnedWords, List<String> words){
        List<String> dailyWords = new ArrayList<>();
        Random random = new Random();
        while(dailyWords.size() != 50) {
            int randomNumber = random.nextInt(words.size());
            if(!learnedWords.contains(words.get(randomNumber))){
                dailyWords.add(words.get(randomNumber));
            }
        }
        return dailyWords;
    }

    

    public static void main(String[] args) throws Exception {
        String fileName = "mostfrequent.txt";
        List<String> learnedWords = new ArrayList<>();
        GenerateDailyWords(learnedWords, ReadFile(fileName));
        


    }
}
