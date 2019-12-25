package indeed.general.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnvConfigurationSection {
    @JsonProperty("protocol")
    private String protocol;
    @JsonProperty("mndMainMachineName")
    private String mndMainMachineName;
    @JsonProperty("lubMainMachineName")
    private String lubMainMachineName;
    @JsonProperty("baseHostPostfix")
    private String baseHostPostfix;
    @JsonProperty("mndPort")
    private Integer mndPort;
    @JsonProperty("lubPort")
    private Integer lubPort;
}