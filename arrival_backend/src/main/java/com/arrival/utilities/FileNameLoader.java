package com.arrival.utilities;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 09.05.2015
 * @since: 1.0
 * Package: com.arrival.utilities
 */

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileNameLoader {

    public ArrayList<Path> filePathList;
    public ArrayList<String> fileNameWithExtension;
    public ArrayList<String> fileName;
    public String fileDirectory;
    public URL url;
    public String fileExtension;

    public FileNameLoader() {
        fileNameWithExtension = new ArrayList<>();
        filePathList = new ArrayList<>();
        fileName = new ArrayList<>();
        fileDirectory = null;
        url = null;
    }

    /**
     * @param filePath DirectoryPath where the Files are.
     */
    public FileNameLoader(String filePath, String fileExtension) {
        this.fileExtension = fileExtension;
        fileNameWithExtension = new ArrayList<>();
        filePathList = new ArrayList<>();
        fileName = new ArrayList<>();
        url = getClass().getResource(filePath);
        fileDirectory = url.getPath().split(":")[1];

        setUpFilePathList();
        setUpFileName();
        setUpFileNameWithExtension();
    }

    private void setUpFilePathList() {

        final ArrayList<Path> temp = new ArrayList<>();
        try {
            Files.walk(Paths.get(fileDirectory)).forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    temp.add(filePath);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        filePathList = temp;
    }

    private void setUpFileName() {
        ArrayList<String> temp = new ArrayList<>();
        String tempFilePathString;

        for (Path tempFilePath : filePathList) {
            tempFilePathString = tempFilePath.getFileName().toString();
            String[] tempArray = tempFilePathString.split(fileExtension);

            temp.add(tempArray[0]);
        }
        fileName = temp;
    }

    private void setUpFileNameWithExtension() {
        ArrayList<String> temp = new ArrayList<>();
        for (Path tempFilePath : filePathList) {
            temp.add(tempFilePath.getFileName().toString());
        }
        fileNameWithExtension = temp;
    }

    public ArrayList<Path> getFilePathList() {
        return filePathList;
    }

    public ArrayList<String> getFileNameWithExtension() {
        return fileNameWithExtension;
    }


    public ArrayList<String> getFileName() {
        return fileName;
    }

    public String getFileDirectory() {

        return fileDirectory;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    /*public static void main(String[] args) {
        FileNameLoader fileNameLoader = new FileNameLoader("/com/arrival/testCase/iosTestcase", "class");
        System.out.println(fileNameLoader.getUrl());
        System.out.println(fileNameLoader.getFileDirectory());
        System.out.println(fileNameLoader.getFilePathList());
        System.out.println(fileNameLoader.getFileName());
        System.out.println(fileNameLoader.getFileNameWithExtension());
    }*/
}

