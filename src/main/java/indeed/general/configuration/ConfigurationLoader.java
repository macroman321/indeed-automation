package indeed.general.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;

@Log4j2
public class ConfigurationLoader {

    private ConfigurationLoader() {
    }

    private static final String ENV_CONF_FILE_PROPERTY = "environment.configuration.file";

    public static EnvConfigurationSection loadEnvironment(String environmentName) throws IOException {
        ObjectMapper configurationMapper = new ObjectMapper();

        if (System.getProperty(ENV_CONF_FILE_PROPERTY) == null) {
            System.setProperty(ENV_CONF_FILE_PROPERTY, "src/test/resources/configuration/EnvConfigurationFile.json");
        }
        log.info("Trying to open file: " + System.getProperty(ENV_CONF_FILE_PROPERTY));
        File configurationFile = new File(System.getProperty(ENV_CONF_FILE_PROPERTY));
        EnvConfiguration envConfig = configurationMapper.readValue(configurationFile, EnvConfiguration.class);
        return envConfig.getConfig(environmentName);
    }

    public static DBConfigurationSection loadDBParams(String environmentName) throws IOException {
        File dbConfigurationFile = new File(System.getProperty("dbConfigurationFile"));
        log.info("Trying to open file: " + dbConfigurationFile.getName());

        ObjectMapper dbConfigurationMapper = new ObjectMapper();
        return dbConfigurationMapper
                .readValue(dbConfigurationFile, DBConfiguration.class)
                .getConfig(environmentName);

    }
}