package com.arrival.windows.controller;

import com.arrival.utilities.SystemPreferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 09.05.2015
 * @since: 1.0
 * Package: com.arrival.windows.controller
 */

public class FXMLArrivalLogInController implements Initializable {
    private static final Logger log = LogManager.getLogger(FXMLArrivalLogInController.class);

    @FXML
    private Button btnLogIn;
    @FXML
    private Button btnSignIn;
    @FXML
    private Label lblFailLogIn;
    @FXML
    private TextField txfUsername;
    @FXML
    private PasswordField pwfPassword;
    @FXML
    private CheckBox chbNoLogIn;

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void clickLogIn(ActionEvent actionEvent) {
        lblFailLogIn.setVisible(!lblFailLogIn.isVisible());
        try {

            URL url = getClass().getResource("/fxml/FXMLArrivalMain.fxml");
            FXMLLoader loader = new FXMLLoader(url, SystemPreferences.getResourceBundle("arrivalMain"));

            Parent root = loader.load();
            Scene mainAppScene = new Scene(root, 1300, 530);
            Stage mainAppStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            mainAppScene.getStylesheets().add("/css/arrivalMain.css");

            mainAppStage.hide();
            mainAppStage.setScene(mainAppScene);
            mainAppStage.setTitle("Main - ArrivalOcto");
            mainAppStage.setResizable(true);
            mainAppStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void clickSignIn(ActionEvent actionEvent) {
        log.info("Sign In");
    }

    @FXML
    public void clickLogInWithoutSignIn(ActionEvent actionEvent) {
        log.info("Log in without Sign");
    }
}