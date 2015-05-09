package com.arrival.windows.view;

/**
 * Created by Aaron on 09.05.2015.
 **/

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        URL url = getClass().getResource("/fxml/MainApp.fxml");
        Parent root = FXMLLoader.load(url);

        Scene scene = new Scene(root,1100,500);
        scene.getStylesheets().add("/css/MainApp.css");

        primaryStage.setScene(scene);
        primaryStage.setTitle("Main - ArrivalOcto");
        primaryStage.show();
    }
}
