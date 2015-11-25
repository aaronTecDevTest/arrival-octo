package com.arrival.windows.controller;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 09.05.2015
 * @since: 1.0
 * Package: com.arrival.windows.controller
 */

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller Class for ViewMainApp. This Class have linked with ViewMainApp.fxml and
 * can evoke function from ViewMainApp.fxml file.
 */
public class FXMLArrivalResultController implements Initializable {
    private static final Logger log = LogManager.getLogger(FXMLArrivalResultController.class);

    private static final Image passedPNG = new Image("../icons/passed.png");
    private static final Image failedPNG = new Image("../icons/failed.png");
    private static final Image skippedPNG = new Image("../icons/skipped.png");
    private static final Image defaultPNG = new Image("");

    @FXML
    ImageView resultViewer;


    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void setImagePassed(){
        resultViewer.setImage(passedPNG);
    }

    @FXML
    public void setImageFail(){
        resultViewer.setImage(failedPNG);
    }

    @FXML
    public void setImageSkipped(){
        resultViewer.setImage(skippedPNG);
    }

    @FXML
    public void setImageDefault(){
        resultViewer.setImage(defaultPNG);
    }
}