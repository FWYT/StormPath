package shiro;

import com.stormpath.sdk.application.Application;
import com.stormpath.sdk.client.Client;
import com.stormpath.sdk.client.Clients;
import com.stormpath.sdk.oauth.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by vagrant on 7/12/16.
 */
public class JwtBuilder {

    public MultivaluedMap<String, Object> buildJWTHeader(WebTarget target, String contentType, String entity) throws Exception {


        MultivaluedMap<String, Object> headers = new MultivaluedHashMap<String, Object>();

        String contentMd5 = entity != null ? DigestUtils.md5Hex(entity) : "";

        headers.add("Content-Type", contentType);
        headers.add("Content-MD5", contentMd5);

        Client client = Clients.builder().build();
        Application application =
                client.getResource("https://api.stormpath.com/v1/applications/1dbLlJbQzHh0KnXai01QWm",
                        Application.class);

        /*OAuthPasswordGrantRequestAuthentication req = OAuthRequests.OAUTH_PASSWORD_GRANT_REQUEST.builder()
                .setLogin("meows@cats.com")
                .setPassword("All999FISH").build();*/
        OAuthPasswordGrantRequestAuthentication req = OAuthRequests.OAUTH_PASSWORD_GRANT_REQUEST.builder()
                .setLogin("woofs@dogs.com")
                .setPassword("Bark3bark").build();

        OAuthGrantRequestAuthenticationResult res = Authenticators.OAUTH_PASSWORD_GRANT_REQUEST_AUTHENTICATOR
                .forApplication(application)
                .authenticate(req);

        String token = res.getAccessTokenString();

        String dateString = DateTime.now(DateTimeZone.UTC).toString("MM/dd/yyyy HH:mm:ss Z");
        headers.add("Date", dateString);
        headers.add("Authorization", "Bearer " + token);

        System.out.println();
        System.out.print("curl -X POST ");

        System.out.print("-H \"Authorization: "+ "Bearer " + token);

        System.out.print(" " + target.getUri() + " ");
        System.out.println();
        return headers;
    }
}
