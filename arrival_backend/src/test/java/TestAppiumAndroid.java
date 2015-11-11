import com.arrival.appium.model.Capabilities;
import com.arrival.appium.model.Configuration;
import com.arrival.appium.model.NodeConfig;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 11.11.2015.
 * @since: 1.0
 * Package: PACKAGE_NAME
 */
public class TestAppiumAndroid {

    private NodeConfig nodeConfig = new NodeConfig();
    private Capabilities [] capabilities;
    private Configuration configuration = new Configuration();

    public TestAppiumAndroid(){
        iniCapabilities();
        iniConfiguration();
        nodeConfig.setCapabilities(capabilities);
    }

    private void iniCapabilities() {
        capabilities[0].setBrowserName("");
        capabilities[0].setDeviceName("");
        capabilities[0].setMaxInstances(1000);
        capabilities[0].setPlatform("");
        capabilities[0].setUdid("");
        capabilities[0].setVersion("");
    }
    private void iniConfiguration() {
        configuration.setCleanUpCycle(1000);
        configuration.setHost("");
        configuration.setHubHost("1000");
        configuration.setHubPort(1000);
        configuration.setMaxSession(1000);
        configuration.setPort(1000);
        configuration.setProxy("1000");
        configuration.setRegister(true);
        configuration.setTimeout(1000);
        configuration.setCleanUpCycle(1000);
        configuration.setRegisterCycle(1000);
    }

    @Override
    public String toString() {
        return "Capabilities: "+capabilities.toString() + " Configuration: " + configuration.toString();
    }

    public static void main(String[] args) {
        TestAppiumAndroid testAppiumAndroid = new TestAppiumAndroid();
        testAppiumAndroid.toString();
    }
}
