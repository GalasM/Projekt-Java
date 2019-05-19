package KoloFortuny.sample;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;

import static KoloFortuny.sample.Client.newGame;
import static KoloFortuny.sample.Client.sendMessage;

public class Controller implements Initializable {
    public AnchorPane pan;
    public FlowPane Gpane;
    public Button Buttonstart;
    public FlowPane Kategorie;
    private Client klient;
    public static Controller kontroler;
    boolean clickedStart=true;


    public void showScene() throws IOException {
        Platform.runLater(() -> {
            Stage stage = (Stage) Gpane.getScene().getWindow();


            stage.setOnCloseRequest((WindowEvent e) -> {
                Platform.exit();
                System.exit(0);
            });
        });
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        kontroler = new Controller();
        Thread t = Thread.currentThread();
        System.out.println("Controler"+t);
        klient = new Client(2004);
        Thread x = new Thread(klient);
        x.start();
    }

    @FXML
    public void click(Event event) {        //KLIKANIE LITER
       Messages msg = new Messages();
       msg.setType(MessageType.SIGN);
       Button x = (Button) event.getSource();
        String ClickedSignStr = "";
        ClickedSignStr = (x.getId()).toUpperCase();
       msg.setMsg(ClickedSignStr);
        try {
            Client.sendMessage(msg);
        } catch (IOException e) {
            e.printStackTrace();

        }

       //DZIALA!!!!
        /*int len = newGame.getWordLength();
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
        System.out.println("Ile liter trafiono " + countSigns);*/
    }

    @FXML
    private void addL() throws InterruptedException {    //KLIKNIECIE START

        Gpane.setAlignment(Pos.CENTER);
        Gpane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
        Kategorie.setAlignment(Pos.CENTER);


        if(clickedStart) {
            Messages msg = new Messages();
            msg.setType(MessageType.START);
            msg.setMsg("START");
            try {
                Client.sendMessage(msg);
            } catch (IOException e) {
                e.printStackTrace();

            }
            TimeUnit.MILLISECONDS.sleep(100);
            setBoard(newGame);
            clickedStart=false;
            Buttonstart.setText("LOSUJ");
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
        Text kategory = new Text();
        kategory.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR,20));
        kategory.setText("Kategoria: " + newGame.getCategory());
        Kategorie.getChildren().add(kategory);
    }
//////////////przeniesc do client

    public void changeBoard(String word) {
        for (int i = 0; i < newGame.getWordLength(); i++) {
            String ClickedSign = String.valueOf(word.charAt(i));
            if ((word.charAt(i) != '#') && (word.charAt(i) != '_')) {
                newGame.labels.get(i).setText(ClickedSign);
            }
        }
    }

}
