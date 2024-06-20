import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Application
 */
public class Application {

    public static void main(String[] args) {
        ArrayList<String> allLines = new ArrayList<String>();
        Scanner in = new Scanner(System.in);
        String inputText = "sentences.txt";
        int choice, softLimit, hardLimit, sentenceCount;
        String outputText;


        //READING FILE
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(inputText)));
            
            // read the first line of the text into currentLine
            String currentLine = reader.readLine();

            // unless we encounter a null line
            while (currentLine != null) {
                // print the current line
                allLines.add(currentLine);
                // and get the next line
                currentLine = reader.readLine();
            }

        } catch (FileNotFoundException e) {
            // in case the file we provide cannot be found, this is the exception we get
            e.printStackTrace();

        } catch (IOException e) {
            // in case there is problem with reading the file, this is the exception we get
            e.printStackTrace();
        }
        finally{
            for (int i = 0; i < allLines.size(); i++) {
                WordBag.processSentence(allLines.get(i));
            }
            System.out.printf("Input file '%s' is processed%n", inputText);
        }

        do {
            System.out.println("1. Generate Sentence\n2. Output Sentences to Text File\n3. Exit");
            System.out.print("Please choose an option: ");
            choice = in.nextInt();
        
            if (choice == 1) {
                System.out.print("Soft limit: ");
                softLimit = in.nextInt();
                System.out.print("Hard limit: ");
                hardLimit = in.nextInt();
                System.out.println(WordBag.generateSentence(softLimit, hardLimit));
            }


            else if (choice == 2) {
                System.out.print("File Name: ");
                outputText = in.next();
                outputText += ".txt";
                System.out.print("Sentence Count: ");
                sentenceCount = in.nextInt();
                System.out.print("Soft limit: ");
                softLimit = in.nextInt();
                System.out.print("Hard limit: ");
                hardLimit = in.nextInt();
                //WordBag.generateSentence(softLimit, hardLimit);
                WordBag.writeToTextFile(outputText, sentenceCount, softLimit, hardLimit);
                
            }
            else if (choice<1 && choice>3 ) {
                System.out.println("Enter a valid choice");
            }
        } while (choice != 3);

    }
}