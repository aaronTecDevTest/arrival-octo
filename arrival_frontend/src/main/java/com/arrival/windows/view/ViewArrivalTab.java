package com.arrival.windows.view;

import com.arrival.unit.suites.ArrivalTestSuite;
import com.arrival.windows.model.TestCase;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 03.08.2015.
 * @since: 1.0
 * Package: com.arrival.windows.view
 */
public class ViewArrivalTab extends Tab{
    private ArrayList<TestCase> testCases;
    private String suiteResultPaht;
    private Objects suiteConfiguration;
    private ObservableList date;
    private ArrivalTestSuite runableSuite;
    private TableView tableView;
    public ViewArrivalTab(){

    }

    public  ViewArrivalTab(String title, Node content) {
        super(title,content);
    }

    public ViewArrivalTab(Node content){
        super("",content);
    }

    public void setTableView(TableView tableView) {
        this.tableView = tableView;
    }

    public static final ViewArrivalTab arrivalTabFromTab(final Tab tab){
        ViewArrivalTab tempTab= new ViewArrivalTab();

        tempTab.setTab(tab);

        return tempTab;
    }

    private void setTab(Tab tab) {
        super.setContent( tab.getContent());
    }
}
