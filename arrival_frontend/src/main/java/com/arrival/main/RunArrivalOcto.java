package com.arrival.main;

/**
 * Created by Aaron on 28.03.2015.
 *
 * @author Aaron K.
 * @version 0.0.1
 */

import com.arrival.utilities.SystemPreferences;
import com.arrival.windows.view.ViewArrivalLogIn;

public class RunArrivalOcto {
    public ViewArrivalLogIn viewLogIn;

    public static void main(String[] args) {
        SystemPreferences.getInstance();
        RunArrivalOcto app = new RunArrivalOcto();
        app.runLogIn();
    }

    public void runLogIn() {
        viewLogIn = new ViewArrivalLogIn();
        viewLogIn.run();
    }
}

