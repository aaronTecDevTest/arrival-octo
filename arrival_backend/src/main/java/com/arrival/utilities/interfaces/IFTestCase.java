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
 * Interface for TestCases
 **/
public interface IFTestCase {

    SimpleIntegerProperty tcID = null;
    SimpleStringProperty tcName = null;
    SimpleStringProperty tcDescription = null;
    SimpleStringProperty tcDuration = null;
    SimpleStringProperty tcLastRun = null;
    SimpleStringProperty tcLink = null;
    SimpleStringProperty tcResult = null;

    int getTcID();

    void setTcID(int tcID);

    SimpleIntegerProperty tcIDProperty();

    String getTcName();

    void setTcName(String tcName);

    SimpleStringProperty tcNameProperty();

    String getTcDescription();

    void setTcDescription(String tcDescription);

    SimpleStringProperty tcDescriptionProperty();

    String getTcResult();

    void setTcResult(String tcResult);

    SimpleStringProperty tcResultProperty();

    String getTcLastRun();

    void setTcLastRun(String tcLastRun);

    SimpleStringProperty tcLastRunProperty();

    String getTcLink();

    void setTcLink(String tcLink);

    SimpleStringProperty tcLinkProperty();

    String getTcDuration();

    void setTcDuration(String tcDuration);

    SimpleStringProperty tcDurationProperty();
}
