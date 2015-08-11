package com.arrival.windows.view;

import com.arrival.unit.suites.ArrivalTestSuite;
import com.arrival.windows.model.TestCase;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import org.testng.xml.XmlClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 03.08.2015.
 * @since: 1.0
 * Package: com.arrival.windows.view
 */
public class ViewArrivalTab extends Tab{
    private String suiteResultPaht;
    private Objects suiteConfiguration;
    private ArrivalTestSuite runableSuite;
    private TableView tableView;
    private ObservableList date;

    public ViewArrivalTab(){
    }

    public  ViewArrivalTab(String title, Node content) {
        super(title,content);
        runableSuite = new ArrivalTestSuite();
        runableSuite.setSuiteName(title);
    }

    public ViewArrivalTab(Node content){
        super("", content);
    }

    public void setTableView(TableView tableView) {
        this.tableView = tableView;
        this.date = tableView.getItems();
    }

    public void runTestSuite(){
        List<XmlClass> tempClasses = new ArrayList<>();

        for(int i=0; i<date.size();i++){
            tempClasses.add(new XmlClass(((TestCase)date.get(i)).getTcClassPackage()));
        }
        runableSuite.setClasses(tempClasses);
        runableSuite.runVirtualSuit();
    }
}
