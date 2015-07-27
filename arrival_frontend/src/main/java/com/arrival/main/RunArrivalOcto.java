package com.arrival.main;

import com.arrival.windows.controller.ControllerLogIn;
import com.arrival.windows.view.ViewArrivalMainApp;
import com.arrival.windows.view.ViewLogIn;
import com.arrival.windows.view.ViewArrivalMainApp;

/**
 * Created by Aaron on 28.03.2015.
 *
 * @author Aaron K.
 * @version 0.0.1
 */

public class RunArrivalOcto {

    public ViewArrivalMainApp viewMainApp;
    public ViewLogIn viewLogIn;

    public RunArrivalOcto() {
        viewMainApp = new ViewArrivalMainApp();
    }

    public void runLogIn() {
        viewLogIn = new ViewLogIn();
        ViewLogIn.setMainApp(this);
        viewLogIn.run();
    }

    public void runMainApp(){
        viewMainApp.run();
    }

    public static void main(String[] args) {
        RunArrivalOcto app = new RunArrivalOcto();
        app.runLogIn();
    }
}

