package de.kiessla.hetzner.dyndns;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Properties;

@Slf4j
public class ApplicationProperties {

    private static final String PROPERTIES_FILE_NAME = "application.properties";

    private static final String PROPERTY_FALLBACK = "KEY_NOT_FOUND";
    private static final String PROPERTY_ZONE_NAME = "zoneName";
    private static final String PROPERTY_RECORD_TTL = "recordTtl";
    private static final String PROPERTY_RECORD_NAME = "recordName";
    private static final String PROPERTY_MY_IP_URL = "myIpUrl";
    private static final String PROPERTY_HETZNER_URL = "hetznerUrl";

    private final Properties properties = loadProperties();

    public String getZoneName() {
        return getProperty(PROPERTY_ZONE_NAME);
    }

    public int getRecordTtl() {
        return Integer.parseInt(getProperty(PROPERTY_RECORD_TTL));
    }

    public String getRecordName() {
        return getProperty(PROPERTY_RECORD_NAME);
    }

    public String getMyIpUrl() {
        return getProperty(PROPERTY_MY_IP_URL);
    }

    public String getHetznerUrl() {
        return getProperty(PROPERTY_HETZNER_URL);
    }

    private Properties loadProperties() {
        Properties properties = new Properties();

        try {
            properties.load(Application.getResourceStream(PROPERTIES_FILE_NAME));

        } catch (IOException e) {
            log.error("Unable to load properties file: ", e);
        }

        return properties;
    }

    private String getProperty(String key) {
        return properties.getProperty(key, PROPERTY_FALLBACK);
    }
}
