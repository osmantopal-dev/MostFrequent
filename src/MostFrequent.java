import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;

public class MostFrequent {

    private  List<String> ReadFile(String fileName){
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

    private  List<String> GenerateDailyWords(List<String> learnedWords, List<String> words){
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

    private void CreateFile(String filePath){
        try {
            File myObj = new File("filePath");
            if (myObj.createNewFile()) {
              System.out.println("File created: " + myObj.getName());
            } else {
              System.out.println("File already exists.");
            }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
    }
    
    private  void WriteFile(List<String> dailyWords, String filePath){
        CreateFile(filePath);
        try {
            FileWriter myWriter = new FileWriter(filePath);
            for(int i = 0;i < dailyWords.size();i++){
                myWriter.write(dailyWords.get(i));
                if(i != dailyWords.size() - 1){
                    myWriter.write("\n");
                }
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
    }
    

    public static void main(String[] args) throws Exception {
        MostFrequent instance = new MostFrequent();
        String fileName = "mostfrequent.txt";
        List<String> learnedWords = new ArrayList<>();
        instance.GenerateDailyWords(learnedWords, instance.ReadFile(fileName));
        instance.WriteFile(instance.GenerateDailyWords(learnedWords, instance.ReadFile(fileName)), "DailyWords.txt");

    }
}
