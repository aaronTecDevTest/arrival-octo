package com.arrival.main;

import com.arrival.windows.view.ViewArrivalMainApp;
import com.arrival.windows.view.ViewArrivalLogIn;

/**
 * Created by Aaron on 28.03.2015.
 *
 * @author Aaron K.
 * @version 0.0.1
 */

public class RunArrivalOcto {
    public ViewArrivalLogIn viewLogIn;

    public void runLogIn() {
        viewLogIn = new ViewArrivalLogIn();
        viewLogIn.run();
    }

    public static void main(String[] args) {
        RunArrivalOcto app = new RunArrivalOcto();
        app.runLogIn();
    }
}

