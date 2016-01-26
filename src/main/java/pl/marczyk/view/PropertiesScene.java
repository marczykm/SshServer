package pl.marczyk.view;

import javafx.beans.NamedArg;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Created by MMARCZYK on 2016-01-26.
 */
public class PropertiesScene extends Scene {

    private final Stage MAIN_STAGE;

    public PropertiesScene(@NamedArg("root") Parent root, Stage stage) {
        super(root);
        MAIN_STAGE = stage;
        prepare();

    }

    private void prepare() {
        final Label label = new Label("Application properties");
        label.setFont(new Font("Arial", 20));

        Label vpsAddressLabel = new Label("VPS address");
        TextField vpsAddressTextField = new TextField();
        HBox hbVpsAddress = new HBox(10, vpsAddressLabel, vpsAddressTextField);

        Label vpsUsernameLabel = new Label("VPS username");
        TextField vpsUsernameTextField = new TextField();
        HBox hbVpsUsername = new HBox(10, vpsUsernameLabel, vpsUsernameTextField);

        Label vpsPasswordLabel = new Label("VPS password");
        TextField vpsPasswordTextField = new TextField();
        HBox hbVpsPassword = new HBox(10, vpsPasswordLabel, vpsPasswordTextField);

        Button saveButton = new Button("Save");
        saveButton.setOnAction( (ActionEvent t) -> MAIN_STAGE.setScene(new MainScene(new VBox(), MAIN_STAGE)) );

        VBox vbox = new VBox(10, label, hbVpsAddress, hbVpsUsername, hbVpsPassword, saveButton);
        ((VBox)this.getRoot()).getChildren().addAll(vbox);
    }
}
