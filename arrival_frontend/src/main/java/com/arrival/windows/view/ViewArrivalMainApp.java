package com.arrival.windows.view;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 09.05.2015
 * @since: 1.0
 * Package: com.arrival.windows.view
 */

import com.arrival.windows.controller.FXMLArrivalTableViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.text.TableView;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;


/**
 * Main Class to run the hole Application functions
 **/
public class ViewArrivalMainApp extends Application {
    private static final Logger log =  LogManager.getLogger(ViewArrivalMainApp.class);

    @Override
    public void start(Stage primaryStage) throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL url = getClass().getResource("/fxml/FXMLArrivalMain.fxml");
        URL applicationIcon = getClass().getResource("/icons/appIcon.png");
        InputStream inputStream = classLoader.getResource("bundles/arrivalMain_de.properties").openStream();
        ResourceBundle bundle = new PropertyResourceBundle(inputStream);

        FXMLLoader loader = new FXMLLoader();
        Parent root = FXMLLoader.load(url, bundle);

        Scene scene = new Scene(root, 1300, 530);
        scene.getStylesheets().add("/css/arrivalMain.css");

        primaryStage.setScene(scene);
        primaryStage.setTitle("Main - ArrivalOcto");
        primaryStage.getIcons().add(new Image(applicationIcon.toString()));
        primaryStage.show();
    }
}
