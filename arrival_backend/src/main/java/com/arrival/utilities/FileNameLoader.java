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

    private ArrayList<Path> filePathList;
    private ArrayList<String> fileNameWithExtension;
    private ArrayList<String> className;
    private ArrayList<String> classPackage;
    private int size;
    private String fileDirectory;
    private String filePath;
    private URL url;
    private String fileExtension;

    public FileNameLoader() {
        fileNameWithExtension = new ArrayList<>();
        filePathList = new ArrayList<>();
        className = new ArrayList<>();
        fileDirectory = null;
        url = null;
    }

    /**
     * @param filePath DirectoryPath where the files are.
     * @param fileExtension Extension of the file.
     */
    public FileNameLoader(String filePath, String fileExtension) {
        this.fileExtension = fileExtension;
        this.fileNameWithExtension = new ArrayList<>();
        this.filePathList = new ArrayList<>();
        this.className = new ArrayList<>();
        this.filePath = filePath;
        this.url = getClass().getResource(filePath);
        this.fileDirectory = url.getPath().split(":")[1];

        setUpFilePathList();
        setUpFileName();
        setUpFileNameWithExtension();
        setUpClassPackage();

        this.size = className.size();
    }

    public static void main(String[] args) {
        FileNameLoader fileNameLoader = new FileNameLoader("/com/arrival/testCase/webTestcase", ".class");
        System.out.println("Url:" + fileNameLoader.getUrl());
        System.out.println("Dir:" + fileNameLoader.getFileDirectory());
        System.out.println("Path:" + fileNameLoader.getFilePathList());
        System.out.println("ClassName:" + fileNameLoader.getClassName());
        System.out.println("Extension:" + fileNameLoader.getFileNameWithExtension());
        System.out.println("ClassPackage:" + fileNameLoader.getClassPackage());
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
        className = temp;
    }

    private void setUpFileNameWithExtension() {
        ArrayList<String> temp = new ArrayList<>();
        for (Path tempFilePath : filePathList) {
            temp.add(tempFilePath.getFileName().toString());
        }
        fileNameWithExtension = temp;
    }

    private void setUpClassPackage() {
        ArrayList<String> temp = new ArrayList<>();
        String temFilePath = filePath.toString().replace("/","\\\\");
        for(Path path : filePathList){
            String dir = path.toString().split(temFilePath)[1].replace("/", ".");
            temp.add(dir);
        }
        classPackage = temp;
    }

    public ArrayList<Path> getFilePathList() {
        return filePathList;
    }

    public ArrayList<String> getFileNameWithExtension() {
        return fileNameWithExtension;
    }

    public ArrayList<String> getClassName() {
        return className;
    }

    public ArrayList<String> getClassPackage() {
        return classPackage;
    }

    public String getFileDirectory() {
        return fileDirectory;
    }

    public int getSize() {
        return size;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }
}
