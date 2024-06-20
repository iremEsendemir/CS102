import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class WordBag {
    public static ArrayList<Word> allWords = new ArrayList<>();

    //process the given String by appending the starting and ending words and splitting the sentence according to space characters.
    public static void processSentence (String sentence){
        String newSentence;
        String[] words;
        newSentence = "<START> " + sentence + " <END>";
        words = newSentence.split(" ");
        for (int i = 0; i < words.length - 2; i++) {
            Word mainWord = findOrCreate(words[i]);
            Word wordToAdd =findOrCreate(words[i+1]);
            mainWord.addFollowedBy(wordToAdd);
            if (i == 1) {
                mainWord.setFirst();
            }
        }
        String lastString = words[words.length - 2];
        Word lastWord = findOrCreate(lastString);
        lastWord.setLast();
    }

    //first checks if the given wordText is included in your allWords ArrayList. If it already exists, return that Word.
    //If not, then create a new Word object using the given wordText and add it to your allWords ArrayList
    public static Word findOrCreate(String wordText) {
        for (int i = 0; i < allWords.size(); i++) {
            if (allWords.get(i).getWordText().equals(wordText)) {
                return allWords.get(i);
            }
        }
        Word newWord = new Word(wordText);
        allWords.add(newWord);
        return newWord;
    }

    // process the given String by appending the starting and ending words and splitting the sentence according to space characters.
    public static String generateSentence(int softLimit, int hardLimit){
        ArrayList<Word> wordsOfSentence = new ArrayList<Word>();
        String sentence = "";
        Word previousWord, nextWord;
        Word starting = findOrCreate("<START>");
        Word firstWord = starting.getRandomNextWord();
        wordsOfSentence.add(firstWord);
        previousWord = firstWord;

        for (int i = 0; i < softLimit - 1; i++) {
            if ((previousWord.getCanBeFollowedBy().size() == 0)) {
                sentence = makeSentence(wordsOfSentence);
                return sentence; 
            } 
            else {
                nextWord = previousWord.getRandomNextWord();
                wordsOfSentence.add(nextWord);
                previousWord = nextWord;
            }
        }

        for (int i = 0; i < hardLimit - softLimit; i++) {
            if (previousWord.isLast()) {
                sentence = makeSentence(wordsOfSentence);
                return sentence; 
            } 
            else {
                System.out.print(wordsOfSentence.get(i+softLimit-1).isLast() + " ");
                System.out.println(wordsOfSentence.get(i+softLimit-1).getCanBeFollowedBy().size());
                nextWord = previousWord.getRandomNextWord();
                wordsOfSentence.add(nextWord);
                previousWord = nextWord;
            }
        }

        /*//to see whether it should end
        for (Word w : wordsOfSentence) {
            System.out.print(w.isLast());
            System.out.print(",");
        }
        System.out.println();

        //to see whether it should end
        for (Word w : wordsOfSentence) {
            System.out.print(w.getCanBeFollowedBy().size());
            System.out.print(",");
        }
        System.out.println();*/


        sentence = makeSentence(wordsOfSentence);
        return sentence;
    }

    public static String makeSentence(ArrayList<Word> wordList){
        String sentence = "";
        for (Word w : wordList) {
            sentence += w.getWordText() + " ";
        }
        return sentence;
    }

    public static void writeToTextFile(String outputName, int sentenceCount, int softLimit, int hardLimit) {
        try {
            // open the output writer
            FileWriter writer = new FileWriter(outputName);

            for (int i = 0; i < sentenceCount; i++) {
                writer.write(generateSentence(softLimit, hardLimit));
                writer.write("\n");
            }


            // in the end we need to close the file
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            System.out.printf("Saved to %s%n", outputName);
        }
    }
}
