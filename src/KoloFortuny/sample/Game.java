package KoloFortuny.sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private String word;
    private StringBuilder hiddenWord;
    private int wordLength;
    List<Field> labels = new ArrayList<>();

    Game(String entry) {
        word = entry.toUpperCase();
        wordLength = word.length();
        hideWord();
        addLabels();
    }

    public void hideWord() {
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

    void addLabels() {
        for (int i = 0; i < wordLength; i++) {
            labels.add(new Field(hiddenWord.charAt(i)));
        }


    }

   /* @FXML private void addL() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = null;
        rootNode = loader.load(getClass().getResource("sample.fxml"));
        Label dua = new Label("2");
        dua.setLayoutX(50);
        dua.setLayoutY(50);
        Pane pane = new Pane();
        pane.getChildren().add(dua);
        ((BorderPane) rootNode).setCenter(pane);
        pane.getChildren().add(dua);
        pan.getChildren().add(dua);
    }*/
}
