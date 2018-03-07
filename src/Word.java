import java.util.ArrayList;

public class Word {
    private String searchedWord;
    private ArrayList<Entry> entries;

    public Word(String searchedWord, ArrayList<Entry> entries) {
        this.searchedWord = searchedWord;
        this.entries = entries;
    }

    @Override
    public String toString() {
        return "Word{" +
                "searchedWord='" + searchedWord + '\'' +
                ", entries=" + entries +
                '}';
    }
}
