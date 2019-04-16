package KoloFortuny.sample;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.io.IOException;

import static KoloFortuny.sample.Main.newGame;
public class Controller {
    public AnchorPane pan;
    public FlowPane Gpane;

    @FXML
    public void click(Event event) {        //KLIKANIE LITER
        int len = newGame.getWordLength();
        int countSigns = 0;
        Button x = (Button) event.getSource();
        x.setDisable(true);
        for (int i = 0; i < len; i++) {
            char ClickedSign;
            String ClickedSignStr = (x.getId()).toUpperCase();
            ClickedSign = ClickedSignStr.charAt(0);
            char CurrSign = (newGame.getWord()).charAt(i);
            if (CurrSign == ClickedSign) {
                countSigns++;
                newGame.setHiddenWord(i, ClickedSign);
                newGame.labels.get(i).setText(ClickedSignStr);
            }
        }
        System.out.println(newGame.getHiddenWord());
        System.out.println("Ile liter trafiono " + countSigns);
    }

    @FXML
    private void addL(Event event) throws IOException {     //KLIKNIECIE START
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = null;
        rootNode = FXMLLoader.load(getClass().getResource("sample.fxml"));

        Gpane.setAlignment(Pos.CENTER);
        Gpane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
        for (int i = 0; i < newGame.getWordsCounter()+1; i++) {
            Gpane.getChildren().add(newGame.words.get(i));
        }
    }
}
