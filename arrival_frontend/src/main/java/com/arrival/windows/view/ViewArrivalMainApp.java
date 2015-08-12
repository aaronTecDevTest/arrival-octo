package com.arrival.windows.view;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 09.05.2015
 * @since: 1.0
 * Package: com.arrival.windows.view
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;


/**
 * Main Class to run the hole Application functions
 **/
public class ViewArrivalMainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL url = getClass().getResource("/fxml/FXMLArrivalMain.fxml");

        InputStream inputStream = classLoader.getResource("bundles/arrivalMain_de.properties").openStream();
        ResourceBundle bundle = new PropertyResourceBundle(inputStream);

        FXMLLoader loader = new FXMLLoader(url, bundle);
        Parent root = loader.load();

        Scene scene = new Scene(root, 1300, 530);
        scene.getStylesheets().add("/css/arrivalMain.css");

        primaryStage.setScene(scene);
        primaryStage.setTitle("Main - ArrivalOcto");
        primaryStage.show();
    }

    public void run() {
        launch();
    }
}
