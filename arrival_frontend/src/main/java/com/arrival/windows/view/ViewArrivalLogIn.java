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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;


/**
 * Login Class to get more authorisation form arrival App and more
 * functions.
 **/
public class ViewArrivalLogIn extends Application {
    private static final Logger log =  LogManager.getLogger(ViewArrivalLogIn.class);
    public FXMLLoader loader;
    public Parent root;
    public URL url;

    @Override
    public void start(Stage primaryStage) throws IOException {
        log.info("Start App with LogIn");
        url = getClass().getResource("/fxml/FXMLArrivalLogIn.fxml");
        loader = new FXMLLoader(url);
        root = loader.load();

        Scene scene = new Scene(root, 400, 300);
        scene.getStylesheets().add("/css/arrivalLogIn.css");

        primaryStage.setScene(scene);
        primaryStage.setTitle("ViewLogIn - ArrivalOcto");
        primaryStage.setResizable(false);
        primaryStage.show();
        log.info("LogIn show up");
    }

    public void run() {
        launch();
    }
}
