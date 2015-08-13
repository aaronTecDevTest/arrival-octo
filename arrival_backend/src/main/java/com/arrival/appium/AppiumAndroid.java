package com.arrival.appium;

import com.arrival.utilities.interfaces.IFAppiumServer;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * @author Aaron Kutekidila
 * @version 1.0
 *          Created on 26/05/15.
 * @since 1.0
 */

public class AppiumAndroid implements IFAppiumServer {
    private static final Logger log =  LogManager.getLogger(AppiumAndroid.class);
    /**
     * Standard Constructor
     */
    public AppiumAndroid() {

    }

    public static void main(String[] args) throws IOException {
        AppiumAndroid androidServer = new AppiumAndroid();
        androidServer.lgServer();
        androidServer.note3Sever();
    }

    public void note3Sever() throws IOException {
        CommandLine command = new CommandLine("/Applications/Appium.app/Contents/Resources/node/bin/node");

        command.addArgument("/Applications/Appium.app/Contents/Resources/node_modules/iosTestcase/bin/iosTestcase.js");

        command.addArgument("--address");
        command.addArgument("127.0.0.1");
        command.addArgument("--port");
        command.addArgument("5555");
        command.addArgument("--nodeconfig");
        command.addArgument("/Users/tecdesdev/IdeaProjects/arrival-septem/iosTestcase/src/main/resources/AppiumNodeNote3.json");

//	command.addArgument("--no-reset");

        DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
        DefaultExecutor executor = new DefaultExecutor();
        executor.setExitValue(1);
        executor.execute(command, resultHandler);
    }

    public void lgServer() throws IOException {
        CommandLine command = new CommandLine("/Applications/Appium.app/Contents/Resources/node/bin/node");
        command.addArgument("/Applications/Appium.app/Contents/Resources/node_modules/iosTestcase/bin/iosTestcase.js");

        command.addArgument("--address");
        command.addArgument("127.0.0.1");
        command.addArgument("--port");
        command.addArgument("6666");
        command.addArgument("--nodeconfig");
        command.addArgument("/Users/tecdesdev/IdeaProjects/arrival-septem/iosTestcase/src/main/resources/AppiumNodeGFlex.json");

//	command.addArgument("--no-reset");

        DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
        DefaultExecutor executor = new DefaultExecutor();
        executor.setExitValue(1);
        executor.execute(command, resultHandler);
    }

    /**
     * This functions start a current Server over commando line.
     **/
    @Override
    public void startServer() {

    }

    /**
     * This functions start a current Server over commando line.
     **/
    @Override
    public void stopServer() {

    }

    /**
     * This functions Restart a current Server over commando line.
     **/
    @Override
    public void restartSever() {

    }

    /**
     * This functions start a current Sever over commando line with JSON-NodeConfiguration file.
     * * @param JSONFilePath -> The Path where the file existe.
     */
    @Override
    public void runServerWithJSON(String JSONFilePath) {

    }
}
