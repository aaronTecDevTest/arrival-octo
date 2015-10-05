package com.arrival.utilities;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 24.08.2015.
 * @since: 1.0
 * Package: com.arrival.utilities
 */

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;
import java.util.ResourceBundle;

public class WindowsDialogs {
    private ResourceBundle bundle = SystemPreferences.getResourceBundle("bundleDialogs");

    public WindowsDialogs(){

    }


    public static String setTestsuiteNameDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Testsuite name");
        dialog.setHeaderText("Enter the Testsuite name:");

        Optional<String> result = dialog.showAndWait();
        String entered;

        if (result.isPresent()) {
            entered = result.get();
        } else {
            entered = "";
        }
        return entered;
    }

    public static void noTestConfigSet(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("No Platform is set up!");

        alert.showAndWait();
    }

    public static void optionsIsNull(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Options is not set up!");

        alert.showAndWait();
    }

    public static void rongPlatform(String ios) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Testcase is not a " + ios +"Platform. " + "Roung Platform!");

        alert.showAndWait();
    }
}
