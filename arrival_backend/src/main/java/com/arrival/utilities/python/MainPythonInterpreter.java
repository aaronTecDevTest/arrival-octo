package com.arrival.utilities.python;

import com.google.common.base.Predicate;
import org.python.util.PythonInterpreter;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 15.02.2016.
 * @since: 1.0
 * Package: com.arrival.utilities.python
 */

public class MainPythonInterpreter implements Predicate<PythonInterpreter> {

    @Override
    public boolean apply(PythonInterpreter anInterpreter) {
        /**
        * Examples of creating and initializing variables in the monkeyrunner environment's
        * namespace. During execution, the monkeyrunner program can refer to the variables "newtest"
        * and "use_emulator"
        *
        */
        anInterpreter.set("newtest", "enabled");
        anInterpreter.set("use_emulator", 1);

        return true;
    }


    public static void main(String[] args) {
        MainPythonInterpreter mpi = new MainPythonInterpreter();
        PythonInterpreter pythonInterpreter = new PythonInterpreter();
        
        mpi.apply(pythonInterpreter);

        //pythonInterpreter.exec("import util");
        //#Imports the monkeyrunner modules used by this program

        //pythonInterpreter.exec("import sys");
        //pythonInterpreter.exec("sys.path.append(sys.path[0].split(':',1)[1])");
        //pythonInterpreter.exec("sys.path.append('C:\\Users\\a.kutekidila\\android-sdks\\sdk\\tools\\lib\\monkeyrunner.jar')");
        pythonInterpreter.exec("from com.android.monkeyrunner import MonkeyRunner, MonkeyDevice");
        //pythonInterpreter.exec("import MonkeyRunner, MonkeyDevice");

        //# Connects to the current device, returning a MonkeyDevice object
        pythonInterpreter.exec("device = MonkeyRunner.waitForConnection()");

        //# Installs the Android package. Notice that this method returns a boolean, so you can test
        //# to see if the installation worked.
        pythonInterpreter.exec("device.installPackage('C:/Users/a.kutekidila/Desktop/WetterInfo/APK/wetterinfo_v1.7.0.r511_prod_20151016.apk')");

        //# sets a variable with the package's internal name
        pythonInterpreter.exec("package = 'com.telekom.wetterinfo'");

        //# sets a variable with the name of an Activity in the package
        pythonInterpreter.exec("activity = 'com.telekom.wetterinfo.ui.activities.MainActivity'");

        //# sets the name of the component to start
        pythonInterpreter.exec("runComponent = package + '/' + activity");

        //# Runs the component
        pythonInterpreter.exec("device.startActivity(component=runComponent)");

        //# Presses the Menu button
        pythonInterpreter.exec("device.press('KEYCODE_MENU', MonkeyDevice.DOWN_AND_UP)");

        //# Takes a screenshot
        pythonInterpreter.exec("result = device.takeSnapshot()");

        //# Writes the screenshot to a file
        pythonInterpreter.exec("result.writeToFile(''C:/Users/a.kutekidila/Desktop/WetterInfo/APK/shot1.png','png')");

        pythonInterpreter.close();
    }

}
