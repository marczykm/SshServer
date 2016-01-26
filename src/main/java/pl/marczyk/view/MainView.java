package pl.marczyk.view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pl.marczyk.model.Redirect;

/**
 * Created by MMARCZYK on 2016-01-26.
 */
public class MainView extends Application {

    private final TableView TABLE = new TableView();
    private Scene scene;

    private final ObservableList<Redirect> data = FXCollections.observableArrayList(
            new Redirect(8080, "localhost", 8080),
            new Redirect(3389, "localhost", 3389)
    );

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        scene = new Scene(new VBox(), 400, 350);
        primaryStage.setTitle("Table View");
        primaryStage.setWidth(500);
        primaryStage.setHeight(500);

        createMenu();

        createRedirectsTable();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createMenu() {
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");

        MenuItem propertiesMenuItem = new MenuItem("Properties");
        propertiesMenuItem.setOnAction((ActionEvent t) -> System.out.println("Properties clicked") );

        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction( (ActionEvent t) -> System.exit(0) );

        menuFile.getItems().addAll(propertiesMenuItem, exitMenuItem);
        menuBar.getMenus().addAll(menuFile);

        ((VBox)scene.getRoot()).getChildren().addAll(menuBar);
    }

    private void createRedirectsTable() {
        final Label label = new Label("Redirected ports");
        label.setFont(new Font("Arial", 20));

        TABLE.setEditable(false);
        TABLE.setPrefWidth(460);

        TableColumn remotePort = new TableColumn("Remote port");
        remotePort.setPrefWidth(TABLE.getPrefWidth()/3);
        remotePort.setCellValueFactory(new PropertyValueFactory("remotePort"));
        TableColumn host = new TableColumn("Host");
        host.setPrefWidth(TABLE.getPrefWidth()/3);
        host.setCellValueFactory(new PropertyValueFactory("host"));
        TableColumn localPort = new TableColumn("Local port");
        localPort.setPrefWidth(TABLE.getPrefWidth()/3);
        localPort.setCellValueFactory(new PropertyValueFactory("localPort"));

        TABLE.setItems(data);
        TABLE.getColumns().addAll(remotePort, host, localPort);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, TABLE);

        ((VBox)scene.getRoot()).getChildren().addAll(vbox);
    }
}
