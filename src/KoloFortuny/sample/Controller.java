package KoloFortuny.sample;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.awt.*;
import java.io.IOException;

import static KoloFortuny.sample.Main.newGame;
public class Controller {
    public AnchorPane pan;

   /* public String haslo = new String();
    public StringBuilder hasloZaszyfrowane = new StringBuilder();*/

    @FXML public void click(Event event)
    {
        int len = newGame.getWordLength();
        int countSigns=0;
        Button x = (Button) event.getSource();
        x.setDisable(true);

        for(int i = 0; i < len; i++)
        {
             char ClickedSign;
             String ClickedSignStr = (x.getId()).toUpperCase();
              ClickedSign=ClickedSignStr.charAt(0);
             char CurrSign = (newGame.getWord()).charAt(i);
            if (CurrSign==ClickedSign) {
                countSigns++;
                newGame.setHiddenWord(i, ClickedSign);
                newGame.labels.get(i).setText(ClickedSignStr);
            }

        }

        System.out.println(newGame.getHiddenWord());
        System.out.println("Ile liter trafiono "+countSigns);

    }

    @FXML private void addL(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = null;
        rootNode = FXMLLoader.load(getClass().getResource("sample.fxml"));
        GridPane pane =  new GridPane();
       // pane.minWidth(20);
        pane.setHgap(1);
        //pane.setPadding(new Insets(1,1,1,1));
       // pane.setPrefSize(100,100);
        pane.maxWidth(500);
        pane.prefWidth(500);
        pane.setLayoutX(450);
        pane.setLayoutY(50);

        for(int i=0;i<newGame.getWordLength();i++) {


            pane.add(newGame.labels.get(i),i, 0);
        }
        pan.getChildren().add(pane);
    }

}
