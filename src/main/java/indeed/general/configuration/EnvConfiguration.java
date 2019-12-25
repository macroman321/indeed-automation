package indeed.general.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EnvConfiguration {
    @JsonProperty("LOCAL")
    private EnvConfigurationSection localEnv;
    @JsonProperty("INT")
    private EnvConfigurationSection integrationEnv;
    @JsonProperty("STAGING")
    private EnvConfigurationSection stagingEnv;
    @JsonProperty("PROD")
    private EnvConfigurationSection prodEnv;

    public EnvConfigurationSection getConfig(String envName) {
        switch (envName.toUpperCase()) {
            case "LOCAL ":
                return localEnv;
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