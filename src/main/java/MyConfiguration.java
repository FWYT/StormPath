import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.secnod.dropwizard.shiro.ShiroConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by vagrant on 8/9/16.
 */
public class MyConfiguration extends Configuration{

    @Valid
    @NotNull
    private String apiKeyId;
    public String getApiKeyId()
    {
        return apiKeyId;
    }

    @Valid
    @NotNull
    private String apiKeySecret;
    public String getApiKeySecret()
    {
        return apiKeySecret;
    }

    @Valid
    @NotNull
    private String apiUrl;
    public String getApiUrl()
    {
        return apiUrl;
    }

    @Valid
    @NotNull
    private String publicKeyFilename;
    public String getPublicKeyFilename()
    {
        return publicKeyFilename;
    }

    @Valid
    @NotNull
    @JsonProperty("shiro")
    private ShiroConfiguration shiro = new ShiroConfiguration();
    public ShiroConfiguration getShiroConfiguration() {
        return shiro;
    }

    @Valid
    @NotNull
    public KatharsisConfiguration katharsis = new KatharsisConfiguration();

    public class KatharsisConfiguration {
        @Valid
        @NotNull
        public String host;

        @Valid
        @NotNull
        public String searchPackage;
    }

}
