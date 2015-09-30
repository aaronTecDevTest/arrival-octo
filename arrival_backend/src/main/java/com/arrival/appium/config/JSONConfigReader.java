package com.arrival.appium.config;

/**
 * @author Aaron Kutekidila
 * @version 1.0
 *          Created on 01.06.2015.
 * @since 1.0
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class JSONConfigReader {

    //public static final String jsonConfigDirectory = "/Users/tecdesdev/IdeaProjects/arrival-septem/appium/src/main/resources";
    private static final Logger log = LogManager.getLogger(JSONConfigReader.class);
    public static final String PATH_DIRECTORY = "../arrival-septem/appium/src/main/resources";
    public  String jsonConfigDirectory = null;
    public ArrayList<Path> pathList = null;

    /**
     * Default Constratory
     */
    public JSONConfigReader() {
        pathList = new ArrayList<>();
        jsonConfigDirectory = PATH_DIRECTORY;
        pathList = readDirectory();
    }

    /**
     *
     * @param pathDirectory DirectoryPath where the json File are.
     */
    public JSONConfigReader(String pathDirectory) {
        jsonConfigDirectory = pathDirectory;
        pathList = new ArrayList<>();
    }

    public String getJsonConfigDirectory() {
        return jsonConfigDirectory;
    }

    private ArrayList<Path> readDirectory() {
        final ArrayList<Path> jsonConfigTemp = new ArrayList<>();

        try {
            Files.walk(Paths.get(jsonConfigDirectory)).forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    jsonConfigTemp.add(filePath);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getOnlyJsonPath(jsonConfigTemp);
    }

    private ArrayList<Path> readDirectory(String jsonConfigDirectory) {
        final ArrayList<Path> jsonConfigTemp = new ArrayList<>();
        try {
            Files.walk(Paths.get(jsonConfigDirectory)).forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    jsonConfigTemp.add(filePath);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getOnlyJsonPath(jsonConfigTemp);
    }

    private ArrayList<Path> getOnlyJsonPath(ArrayList<Path> allPath) {
        ArrayList<Path> temp = new ArrayList<>();

        for (Path path : allPath) {
            boolean isJson = path.getFileName().toString().contains("json");
            if (isJson) {
                temp.add(path);
            }
        }
        return temp;
    }

    private ArrayList<Path> getOnlyJsonPath(ArrayList<Path> allPath, String extenden) {
        ArrayList<Path> temp = new ArrayList<>();

        for (Path path : allPath) {
            boolean isJson = path.getFileName().toString().contains("json");
            if (isJson) {
                temp.add(path);
            }
        }
        return temp;
    }

    public ArrayList<Path> getPathList() {
        return pathList;
    }

   /* public void setJsonConfigFiles(ArrayList<Path> pathList) {
        this.pathList = pathList;
    }*/
}
