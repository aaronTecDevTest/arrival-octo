package com.arrival.windows.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Created by Aaron on 16.05.2015.
 **/
public class ControllerArrivalLogIn implements Initializable{

    @FXML private Button btnLogIn;
    @FXML private Button btnSignIn;
    @FXML private Label lblFailLogIn;
    @FXML private TextField txfUsername;
    @FXML private PasswordField pwfPassword;
    @FXML private CheckBox chbNoLogIn;

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
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = classLoader.getResource("bundles/arrivalMain_de.properties").openStream();
            ResourceBundle bundle = new PropertyResourceBundle(inputStream);

            URL url = getClass().getResource("/fxml/arrivalMain.fxml");
            FXMLLoader loader = new FXMLLoader(url, bundle);

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
    public void clickSignIn(ActionEvent actionEvent){
        System.out.println("Sign In");
    }

    @FXML
    public void clickLogInWithoutSignIn(ActionEvent actionEvent) {
        System.out.println("Log in without Sign");
    }
}
