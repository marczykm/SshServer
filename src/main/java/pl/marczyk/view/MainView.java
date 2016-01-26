package pl.marczyk.view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pl.marczyk.model.Redirect;

/**
 * Created by MMARCZYK on 2016-01-26.
 */
public class MainView extends Application {

    private final TableView table = new TableView();
    ObservableList<Redirect> data = FXCollections.observableArrayList(
            new Redirect(8080, "localhost", 8080),
            new Redirect(3389, "localhost", 3389)
    );

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(new Group());
        primaryStage.setTitle("Table View");
        primaryStage.setWidth(500);
        primaryStage.setHeight(500);

        final Label label = new Label("Redirected ports");
        label.setFont(new Font("Arial", 20));

        table.setEditable(false);
        table.setPrefWidth(460);

        TableColumn remotePort = new TableColumn("Remote port");
        remotePort.setPrefWidth(table.getPrefWidth()/3);
        remotePort.setCellValueFactory(new PropertyValueFactory("remotePort"));
        TableColumn host = new TableColumn("Host");
        host.setPrefWidth(table.getPrefWidth()/3);
        host.setCellValueFactory(new PropertyValueFactory("host"));
        TableColumn localPort = new TableColumn("Local port");
        localPort.setPrefWidth(table.getPrefWidth()/3);
        localPort.setCellValueFactory(new PropertyValueFactory("localPort"));

        table.setItems(data);
        table.getColumns().addAll(remotePort, host, localPort);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        ((Group)scene.getRoot()).getChildren().addAll(vbox);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
