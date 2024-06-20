import java.util.ArrayList;
import java.util.Random;

public class Word {
    ArrayList<Word> canBeFollowedBy = new ArrayList<>();
    String wordText;
    boolean isFirst = false;
    boolean isLast = false;
    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst() {
        isFirst = true;
    }

    public boolean isLast() {
        return isLast;
    }

    public void setLast() {
        isLast = true;
    }

    public Word(String wordText){
        this.wordText = wordText;
    }

    public ArrayList<Word> getCanBeFollowedBy() {
        return canBeFollowedBy;
    }

    public String getWordText() {
        return wordText;
    }

    // to add a new word into canBeFollowedBy ArrayList.
    public void addFollowedBy (Word w){
        canBeFollowedBy.add(w);
    } 

    public Word getRandomNextWord(){
        if (canBeFollowedBy.isEmpty()) {
            return null;
        } else {
            int num = new Random().nextInt(canBeFollowedBy.size());
            Word next = canBeFollowedBy.get(num);
            return next;
        }
    }


    
    //implement any required constructors and set, get, or additional methods to make your code functional.

    //You can include methods to check if a word is an ending word and to get a random word that can follow the current word in your Word class.

}
