package KoloFortuny.sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

   static Game newGame;
   static Client newClient;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Koło Fortuny");
        primaryStage.setScene(new Scene(root,1200,800));
        primaryStage.show();
    }

    public static void main(String[] args) {
       // newGame = new Game("Król Karol kupił królowej Karolinie korale koloru koralowego");
       // newClient = new Client();
        //newClient.run();
            launch(args);
    }
}
