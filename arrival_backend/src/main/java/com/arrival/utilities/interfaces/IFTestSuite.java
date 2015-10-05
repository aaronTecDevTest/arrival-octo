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

    int getTsId();

    void setTsId(int tsId);

    SimpleIntegerProperty tsIdProperty();

    String getTsName();

    void setTsName(String tsName);

    SimpleStringProperty tsNameProperty();

    String getTsResult();

    void setTsResult(String tsResult);

    SimpleStringProperty tsResultProperty();
}
