package KoloFortuny.sample;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static KoloFortuny.sample.Client.newGame;
public class Controller implements Initializable {
    public AnchorPane pan;
    public FlowPane Gpane;
    public Button Buttonstart;
    private Client klient;
    boolean clickedStart=true;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        klient = new Client(2004);
        Thread x = new Thread(klient);
        x.start();
    }

    @FXML
    public void click(Event event) {        //KLIKANIE LITER
        int len = newGame.getWordLength();
        int countSigns = 0;
        String ClickedSignStr = "";
        Button x = (Button) event.getSource();
        x.setDisable(true);
        for (int i = 0; i < len; i++) {
            char ClickedSign;
            ClickedSignStr = (x.getId()).toUpperCase();
            ClickedSign = ClickedSignStr.charAt(0);
            char CurrSign = (newGame.getWord()).charAt(i);
            if (CurrSign == ClickedSign) {
                countSigns++;
                newGame.setHiddenWord(i, ClickedSign);
                newGame.labels.get(i).setText(ClickedSignStr);
            }
        }
        //newClient.sendMessage(ClickedSignStr);
        System.out.println(newGame.getHiddenWord());
        System.out.println("Ile liter trafiono " + countSigns);
    }

    @FXML
    private void addL() {    //KLIKNIECIE START


        // Messages msg = new Messages();
        // msg.setType(MessageType.START);
        // newClient.sendMessage(msg);


        Gpane.setAlignment(Pos.CENTER);
        Gpane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));

        if(clickedStart) {
            Messages msg = new Messages();
            msg.setType(MessageType.START);
            msg.setMsg("START");
            try {
                klient.sendMessage(msg);
            } catch (IOException e) {
                e.printStackTrace();

            }
            clickedStart=false;
            Buttonstart.setText("LOSUJ");

           // Messages msg1 = klient.getMessage();
            //if(msg1.getType()==MessageType.PASSWORD) {
            /*
                Game setter = new Game(klient.getPassword());
                setBoard(setter);
                System.out.println("xd" + setter.getWord());
            */

        }
        else
        {
            //TODO LOSOWANIE KWOT
        }
    }

    private void setBoard(Game newGame)
    {
        for (int i = 0; i < newGame.getWordsCounter()+1; i++) {
         Gpane.getChildren().add(newGame.words.get(i));
         }
    }

}
