package com.arrival.utilities.interfaces;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 09.05.2015
 * @since: 1.0
 * Package: com.utilities.interfaces
 */


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Interface for TestSuites
 **/
public interface IFTestSuite {

     SimpleIntegerProperty tsId =null;
     SimpleStringProperty tsName =null;
     SimpleStringProperty tsResult =null;

    int getTsId();

    SimpleIntegerProperty tsIdProperty();

    void setTsId(int tsId) ;

    String getTsName() ;

    SimpleStringProperty tsNameProperty() ;

    void setTsName(String tsName) ;

    String getTsResult();

    SimpleStringProperty tsResultProperty();

    void setTsResult(String tsResult);

}
