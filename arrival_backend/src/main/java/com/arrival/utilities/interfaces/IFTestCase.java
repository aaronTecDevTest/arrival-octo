package com.arrival.utilities.interfaces;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 09.05.2015
 * @since: 1.0
 * Package: com.utilities.interfaces
 */

import com.arrival.utilities.ArrivalResult;
import io.appium.java_client.android.AndroidDriver;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.testng.annotations.Test;

/**
 * Interface for TestCases
 **/
public interface IFTestCase {
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

    void setTcResult(ArrivalResult tcResult);

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

    SimpleStringProperty tcClassPackageProperty();

    void setTcClassPackage(String tcClassPackage);
}
