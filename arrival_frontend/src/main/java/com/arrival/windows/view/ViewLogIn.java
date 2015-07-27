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
public class ViewLogIn extends Application {

    public FXMLLoader loader;
    public Parent root;
    public URL url;

    private static RunArrivalOcto mainApp;

    public ViewLogIn() {
    }

    public static RunArrivalOcto getMainApp() {
        return mainApp;
    }

    public static void setMainApp(RunArrivalOcto mainApp) {
        ViewLogIn.mainApp = mainApp;
    }


    @Override
    public void start(Stage primaryStage) throws IOException {
        url = getClass().getResource("/fxml/LogIn.fxml");
        loader = new FXMLLoader(url);
        root = loader.load();

        Scene scene = new Scene(root, 400, 300);
        scene.getStylesheets().add("/css/LogIn.css");

        primaryStage.setScene(scene);
        primaryStage.setTitle("ViewLogIn - ArrivalOcto");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void run(){
        launch();
    }
}
