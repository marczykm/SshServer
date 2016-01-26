package pl.marczyk.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by MMARCZYK on 2016-01-26.
 */
public class MainView extends Application {


    private Scene scene;



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        scene = new MainScene(new VBox(), primaryStage);
        primaryStage.setTitle("Table View");
        primaryStage.setWidth(500);
        primaryStage.setHeight(500);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
