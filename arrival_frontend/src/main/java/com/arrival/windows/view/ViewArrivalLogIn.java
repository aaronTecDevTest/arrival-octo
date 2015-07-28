package com.arrival.windows.view;

/**
 * Created by Aaron on 09.05.2015.
 **/

import com.arrival.main.RunArrivalOcto;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;


/**
 * Login Class to get more authorisation form arrival App and more
 * functions.
 **/
public class ViewArrivalLogIn extends Application {

    public FXMLLoader loader;
    public Parent root;
    public URL url;

    public ViewArrivalLogIn() {
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        url = getClass().getResource("/fxml/arrivalLogIn.fxml");
        loader = new FXMLLoader(url);
        root = loader.load();

        Scene scene = new Scene(root, 400, 300);
        scene.getStylesheets().add("/css/arrivalLogIn.css");

        primaryStage.setScene(scene);
        primaryStage.setTitle("ViewLogIn - ArrivalOcto");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void run(){
        launch();
    }
}
