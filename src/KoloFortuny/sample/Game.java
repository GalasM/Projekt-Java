package KoloFortuny.sample;

import javafx.geometry.Orientation;
import javafx.scene.layout.*;
import java.util.ArrayList;
import java.util.List;


class Game {
    private String word;
    private StringBuilder hiddenWord;
    private int wordLength;
    List<Field> labels = new ArrayList<>();
    List<FlowPane> words = new ArrayList<>();
    private int wordsCounter=0;
    Game(String entry) {
        word = entry.toUpperCase();
        wordLength = word.length();
        hideWord();
        addLabels();
    }

    private void hideWord() {
        hiddenWord = new StringBuilder(word);
        for (int i = 0; i < wordLength; i++)
            if (word.charAt(i) == ' ')
                hiddenWord.setCharAt(i, '_');
            else
                hiddenWord.setCharAt(i, '#');

    }

    String getWord() {
        return word;
    }

    String getHiddenWord() {
        return hiddenWord.toString();
    }

    void setHiddenWord(int i, char ClickedSign) {
        hiddenWord.setCharAt(i, ClickedSign);
    }

    int getWordLength() {
        return wordLength;
    }

    private void addLabels() {
        words.add(new FlowPane(Orientation.VERTICAL,2,2));
        words.get(wordsCounter).setPrefWrapLength(100);
        words.get(wordsCounter).setMaxSize(800,50);
        for (int i = 0; i < wordLength; i++) {
            labels.add(new Field(hiddenWord.charAt(i)));
            if(hiddenWord.charAt(i)=='_')
            {
                words.add(new FlowPane(Orientation.VERTICAL,2,2));
                wordsCounter++;
                words.get(wordsCounter).setPrefWrapLength(100);
                words.get(wordsCounter).setMaxSize(800,50);

            }
            else
            words.get(wordsCounter).getChildren().add(labels.get(i));
        }

    }

    int getWordsCounter()
    {
        return wordsCounter;
    }

}
