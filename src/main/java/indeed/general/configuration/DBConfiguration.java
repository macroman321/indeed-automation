package indeed.general.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DBConfiguration {
    @JsonProperty("STAGING")
    public DBConfigurationSection stagingEnv;
    @JsonProperty("INT")
    public DBConfigurationSection integrationEnv;
    @JsonProperty("PROD")
    public DBConfigurationSection prodEnv;

    public DBConfigurationSection getConfig(String envName) {
        switch (envName.toUpperCase()) {
            case "STAGING":
                return stagingEnv;
            case "INT":
                return integrationEnv;
            case "PROD":
                return prodEnv;
            default:
                return null;
        }
    }
}