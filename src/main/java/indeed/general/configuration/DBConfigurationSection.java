package indeed.general.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DBConfigurationSection {
    @JsonProperty("hostUrl")
    public String hostUrl;
    @JsonProperty("dbuser")
    public String dbuser;
    @JsonProperty("dbpass")
    public String dbpass;
}