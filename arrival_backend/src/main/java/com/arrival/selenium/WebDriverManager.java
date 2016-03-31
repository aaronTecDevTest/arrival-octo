package com.arrival.selenium;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 18.08.2015.
 * @since: 1.0
 * Package: com.arrival.selenium
 */

import com.arrival.utilities.SystemPreferences;
import com.arrival.utilities.interfaces.IFConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebDriverManager {
    private static final Logger log = LogManager.getLogger(WebDriverManager.class);

    /**
     * Diese Methode prüft anhand der RunningConfiguration des Tests, welcher
     * WebDriver benötigt wird, erzeugt diesen und gibt ihn zurück.
     *
     * @param runningConfiguration - RunningConfiguration des Tests
     * @return - driver - Der erzeugte Webdriver für den jeweiligen Browser
     */
    public WebDriver setUpDriver(IFConfig runningConfiguration) {
        log.debug("Setting up Webdriver");
        WebDriver driver = null;
        String browser = runningConfiguration.getBrowserName().split(" ")[0];


        switch (browser) {
            case "IE":
                try {
                    log.debug(new File(".").getCanonicalPath());
                } catch (IOException e) {
                    log.error("Error while initializing IEDriver");
                }
                System.setProperty("webdriver.ie.driver", "arrival_backend/src/main/resources/webdriver/IEDriverServer.exe");
                driver = new InternetExplorerDriver(this.setUpIEDriver());
                break;

            case "CH":
                try {
                    log.debug(new File(".").getCanonicalPath());
                } catch (IOException e) {
                    log.error("Error while initializing ChromeDriver");
                }
                if (SystemPreferences.getInstance().isWindows())
                    System.setProperty("webdriver.chrome.driver", "src/main/resources/webdriver/chromedriver.exe");

                if (SystemPreferences.getInstance().isMacOS())
                    System.setProperty("webdriver.chrome.driver", "src/main/resources/webdriver/chromedriver");
                driver = new ChromeDriver(this.setUpChromeDriver());
                System.out.println(driver.getTitle());
                break;

            case "SA":
                try {
                    log.debug(new File(".").getCanonicalPath());
                } catch (IOException e) {
                    log.error("Error while initializing SafariDriver");
                }
                System.setProperty("webdriver.chrome.driver", "arrival_backend/src/main/resources/webdriver/safariDriver.exe");
                driver = new ChromeDriver();
                break;

            case "OP":
                try {
                    log.debug(new File(".").getCanonicalPath());
                } catch (IOException e) {
                    log.error("Error while initializing ChromeDriver");
                }
                System.setProperty("webdriver.chrome.driver", "arrival_backend/src/main/resources/webdriver/chromedriver.exe");
                driver = new ChromeDriver();
                break;

            default:
                driver = new FirefoxDriver(this.setUpFirefox());
                break;
        }
        //driver.manage().window().setSize(screenResolution);
        //driver.get(runningConfiguration_New.getMachine());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        log.debug("WebDriver initialized");
        return driver;
    }

    /**
     * Diese Private Methode erzeugt ein neues Firefox Profil, nimmt verschiedene Einstellungen vor
     * und gibt das Profil dann zur weiteren Verwendung zurück. Das Profil kann verwendet werden,
     * um einen FirefoxDriver weiter zu konfigurieren.
     *
     * @return profile - Das angepasste Firefox Profile für einen FirefoxDriver.
     */
    private FirefoxProfile setUpFirefox() {
        log.debug("Configuring FirefoxDriver");
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("app.update.enabled", false);
        profile.setPreference("accessibility.blockautorefresh", true);
        profile.setPreference("browser.download.manager.retention", 0);
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("browser.formfill.enable", false);
        profile.setPreference("browser.history_expire_days", 0);
        profile.setPreference("browser.history_expire_days.mirror", 180);
        profile.setPreference("browser.migration.version", 1);
        profile.setPreference("browser.places.importBookmarksHTML", true);
        profile.setPreference("browser.places.importDefaults", false);
        profile.setPreference("browser.places.leftPaneFolderId", - 1);
        profile.setPreference("browser.places.migratePostDataAnnotations", false);
        profile.setPreference("browser.places.smartBookmarksVersion", 1);
        profile.setPreference("browser.places.updateRecentTagsUri", false);
        profile.setPreference("browser.preferences.advanced.selectedTabIndex", 2);
        profile.setPreference("Browser.windows.loadOnNewWindow", 0);
        profile.setPreference("browser.safebrowsing.enabled", false);
        profile.setPreference("browser.safebrowsing.malware.enabled", false);
        profile.setPreference("browser.search.update", false);
        profile.setPreference("browser.shell.checkDefaultBrowser", false);
        profile.setPreference("browser.startup.homepage", "about:blank");
        profile.setPreference("browser.tabs.forceHide", false);
        profile.setPreference("browser.tabs.warnOnClose", false);
        profile.setPreference("browser.tabs.warnOnOpen", false);
        profile.setPreference("extensions.checkCompatibility", false);
        profile.setPreference("extensions.checkUpdateSecurity", false);
        profile.setPreference("extensions.newAddons", false);
        profile.setPreference("extensions.update.enabled", false);
        profile.setPreference("extensions.update.notifyUser", false);
        profile.setPreference("security.warn_entering_weak", false);
        profile.setPreference("security.warn_viewing_mixed", false);
        profile.setPreference("signon.rememberSignons", false);
        profile.setPreference("general.appversion.override", "");
        profile.setPreference("general.description.override", "");
        profile.setPreference("general.platform.override", "");
        //Pop Up
        profile.setPreference("plugin.state.flash", 0);
        profile.setPreference("print.always_print_silent", true);
        profile.setPreference("print.show_print_progress", false);
        log.debug("FirefoxDriver configured successfully");
        return profile;
    }

    /**
     * Diese Private Methode erzeugt ein DesiredCapabilities Objekt, über das Einstellungen an einem
     * InternetExplorerDriver vorgenommen werden können. Das angepasste Objekt wird zur weiteren Verwendung
     * zurückgegeben.
     *
     * @return ieCapabilities - Das angepasste DesiredCapabilities Objekt
     */
    public DesiredCapabilities setUpIEDriver() {
        log.debug("Configuring IEDriver");
        DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
        ieCapabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
        ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        ieCapabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
        ieCapabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
        ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        ieCapabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, true);
//		ieCapabilities.setCapability(InternetExplorerDriver.ELEMENT_SCROLL_BEHAVIOR, "InternetExplorerElementScrollBehavior.Top");
//		ieCapabilities.setCapability(InternetExplorerDriver.BROWSER_ATTACH_TIMEOUT, "TimeSpan.FromSeconds(timeOut");
//		ieCapabilities.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, "InternetExplorerUnexpectedAlertBehavior.Ignore");
        ieCapabilities.setVersion("8");
        ieCapabilities.setCapability("disable-popup-blocking", true);
        log.debug("IEDriver configured successfully");
        return ieCapabilities;
    }

    /**
     * Diese Private Methode erzeugt ein DesiredCapabilities Objekt, über das Einstellungen an einem
     * ChromeDriver vorgenommen werden können. Das angepasste Objekt wird zur weiteren Verwendung
     * zurückgegeben.
     *
     * @return chOptions - Das angepasste ChromeOptions Objekt
     */
    public ChromeOptions setUpChromeDriver() {
        log.debug("Configuring ChromeDriver");
        ChromeOptions chOptions = new ChromeOptions();
        List<String> arguments = new ArrayList<String>();

        //TODO bitte hier noch sinnvolle optionnen eintrgagen
        //arguments.add("--silenc");
        //arguments.add("--use-mobile-user-agent = Mozilla/5.0 (Linux; U; Android 4.3; en-us; SM-N900T Build/JSS15J) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30");

        chOptions.addArguments(arguments);
        log.debug("ChromeDriver configured successfully");
        return chOptions;
    }
}
