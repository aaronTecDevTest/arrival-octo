package com.arrival.utilities.log;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.status.StatusLogger;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * @author Aaron Kutekidila
 * @version 1.0
 * Created on 31.05.2015.
 * @since 1.0
 */

/**
 * Configure log4j2 without a xml configuration file or with xml configuration file
 */
public class Log4jConfiguration {

    final static String simpleXmlConfig =
            "<?xml version='1.0' encoding='UTF-8'?>\n" +
                    "<Configuration status='INFO'>\n" +
                    "  <Appenders>\n" +
                    "    <Console name='Stdout' target='SYSTEM_OUT'>\n" +
                    "      <PatternLayout pattern='%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n'/>\n" +
                    "    </Console>\n" +
                    "  </Appenders>\n" +
                    "  <Loggers>\n" +
                    "    <Root level='debug'>\n" +
                    "      <AppenderRef ref='Stdout'/>\n" +
                    "    </Root>\n" +
                    "  </Loggers>\n" +
                    "</Configuration>\n";
    final static String rollingFileXmlConfig =
            "<?xml version='1.0' encoding='UTF-8'?>\n" +
                    "<Configuration>\n" +
                    "  <Properties>\n" +
                    "    <Property name='projectPrefix'>Mpj</Property>\n" +
                    "    <Property name='rawPattern'>%d %-5p [%t] %C{2} (%F:%L) - %m%n</Property>\n" +
                    "    <Property name='coloredFullPattern'>%d %highlight{%-5p}{FATAL=bright red, ERROR=red, WARN=yellow, INFO=cyan, DEBUG=green, TRACE=bright blue} %style{[%t] %C{2} (%F:%L) -}{bright,black} %m%n</Property>\n" +
                    "    <Property name='coloredMediumPattern'>%d %highlight{%-5p}{FATAL=bright red, ERROR=red, WARN=yellow, INFO=cyan, DEBUG=green, TRACE=bright blue} %style{[%t] (%F:%L) -}{bright,black} %m%n</Property>\n" +
                    "    <Property name='coloredShortPattern'>%d %highlight{%-5p}{FATAL=bright red, ERROR=red, WARN=yellow, INFO=cyan, DEBUG=green, TRACE=bright blue} %style{[%t] -}{bright,black} %m%n</Property>\n" +
                    "    <Property name='fileName'>Log/${projectPrefix}.log</Property>\n" +
                    "    <Property name='filePattern'>Log/${projectPrefix}-%i.log</Property>\n" +
                    "  </Properties>\n" +
                    "  <Appenders>\n" +
                    "    <Console name='Stdout' target='SYSTEM_OUT'>\n" +
                    //"      <PatternLayout pattern='${coloredFullPattern}'/>" +         // incl. Class & Filename
                    //"      <PatternLayout pattern='${coloredMediumPattern}'/>" +       // no Class but incl. Filename
                    "      <PatternLayout pattern='${coloredShortPattern}'/>" +        // no Class & Filename
                    //"      <PatternLayout pattern='${rawPattern}'/>" +                 // incl. Class & Filename but no colors
                    "    </Console>\n" +
                    "    <RollingFile name='Logfile' fileName='${fileName}' filePattern='${filePattern}'>\n" +
                    "      <PatternLayout pattern='${rawPattern}'/>\n" +
                    "      <Policies>\n" +
                    "        <SizeBasedTriggeringPolicy size='16 MB'/>\n" +
                    "      </Policies>\n" +
                    "      <DefaultRolloverStrategy fileIndex='min' max='16'/>\n" +
                    "    </RollingFile>\n" +
                    "  </Appenders>\n" +
                    "  <Loggers>\n" +
                    "    <Logger name='de.jme.mpjoe.swing.MpjPlayerVlc$1MyMediaPlayerEventListener' level='debug'/>\n" +
                    "    <Root level='trace'>\n" +
                    "      <AppenderRef ref='Stdout'/>\n" +
                    "      <AppenderRef ref='Logfile'/>\n" +
                    "    </Root>\n" +
                    "  </Loggers>\n" +
                    "</Configuration>\n";
    private static final Logger logger = StatusLogger.getLogger();

    /**
     * Configure log4j2 without a configuration file
     *
     * @param xmlConfig Configuration
     * @return LoggerContext (may not be needed by the caller)
     */
    public static LoggerContext configureFromXmlString(String xmlConfig) {
        LoggerContext ctx = null;
        try {
            InputStream is = new ByteArrayInputStream(xmlConfig.getBytes());
            ConfigurationSource source = new ConfigurationSource(is);
            ctx = Configurator.initialize(null, source);
        } catch (IOException e) {
            logger.error("Log4j configuration failed", e);
        }
        return ctx;
    }

    /**
     * Configure log4j2 for simple console output
     *
     * @return LoggerContext (may not be needed by the caller)
     */
    public static LoggerContext configureSimpleConsole() {
        return configureFromXmlString(simpleXmlConfig);
    }

    /**
     * Configure log4j2 for using colored console output and a rolling file appender
     *
     * @return LoggerContext (may not be needed by the caller)
     */
    public static LoggerContext configureRollingFile() {
        return configureFromXmlString(rollingFileXmlConfig);
    }

    /**
     * Configure log4j2 for using colored console output and a rolling file appender
     *
     * @return LoggerContext (may not be needed by the caller)
     */
    public static LoggerContext configreWithXMLFil(String xmlFilePath) {

        try {
            Path path = Paths.get(xmlFilePath);
            String customizedFileXmlConfig = null;
            customizedFileXmlConfig = new String(Files.readAllBytes(path));
            return configureFromXmlString(customizedFileXmlConfig);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
