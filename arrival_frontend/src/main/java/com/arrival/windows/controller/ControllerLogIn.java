package com.arrival.windows.controller;

import com.arrival.main.RunArrivalOcto;
import com.arrival.windows.view.ViewLogIn;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Aaron on 16.05.2015.
 **/
public class ControllerLogIn implements Initializable{

    protected RunArrivalOcto mainApp;

    @FXML private Button btnLogIn;
    @FXML private Label lblFailLogIn;
    @FXML private Label lblSignIn;
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
        mainApp = ViewLogIn.getMainApp();
    }

    public void clickLogIn(ActionEvent actionEvent) {
        mainApp.runMainApp();
        lblFailLogIn.setVisible(!lblFailLogIn.isVisible());
    }

    public void clickSingIn(ActionEvent actionEvent){

    }

    public void clickSignIn(Event event) {
        System.out.println("test");
    }
}
