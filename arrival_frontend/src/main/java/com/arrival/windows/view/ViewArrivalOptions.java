package com.arrival.windows.view;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 09.05.2015
 * @since: 1.0
 * Package: com.arrival.windows.view
 */

import com.arrival.utilities.SystemPreferences;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;


/**
 * Login Class to get more authorisation form arrival App and more
 * functions.
 **/
public class ViewArrivalOptions extends Application {
    private static final Logger log = LogManager.getLogger(ViewArrivalOptions.class);
    private FXMLLoader loader;
    private Parent root;
    private URL url;
    private URL applicationIcon;


    @Override
    public void start(Stage primaryStage) throws IOException {
        log.info("Start App Options");
        SystemPreferences.getInstance();
        url = getClass().getResource("/fxml/FXMLArrivalOptions.fxml");
        applicationIcon = getClass().getResource("/icons/appIcons.png");
        loader = new FXMLLoader(url, SystemPreferences.getResourceBundle("arrivalOptions"));
        root = loader.load();

        Scene scene = new Scene(root, 500, 400);
        scene.getStylesheets().add("/css/arrivalLogIn.css");

        primaryStage.setScene(scene);
        primaryStage.setTitle("Options - ArrivalOcto");
        primaryStage.getIcons().add(new Image(applicationIcon.toString()));

        primaryStage.setResizable(false);
        primaryStage.show();
        log.info("Options show up");
    }
}