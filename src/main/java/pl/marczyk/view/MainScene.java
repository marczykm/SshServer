package pl.marczyk.view;

import javafx.beans.NamedArg;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Parent;
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
public class MainScene extends Scene {

    private final TableView TABLE = new TableView();
    private final Stage MAIN_STAGE;

    private final ObservableList<Redirect> data = FXCollections.observableArrayList(
            new Redirect(8080, "localhost", 8080),
            new Redirect(3389, "localhost", 3389)
    );

    public MainScene(@NamedArg("root") Parent root, Stage stage) {
        super(root);
        MAIN_STAGE = stage;
        prepare();
    }

    private void prepare() {
        createMenu();
        createRedirectsTable();
    }

    private void createMenu() {
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");

        MenuItem propertiesMenuItem = new MenuItem("Properties");
        propertiesMenuItem.setOnAction((ActionEvent t) -> {
            System.out.println("Properties clicked");
            MAIN_STAGE.setScene(new PropertiesScene(new VBox(), MAIN_STAGE));
        });

        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction( (ActionEvent t) -> System.exit(0) );

        menuFile.getItems().addAll(propertiesMenuItem, exitMenuItem);
        menuBar.getMenus().addAll(menuFile);

        ((VBox)this.getRoot()).getChildren().addAll(menuBar);
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

        ((VBox)this.getRoot()).getChildren().addAll(vbox);
    }
}
