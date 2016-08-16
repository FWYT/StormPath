import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import shiro.JwtBuilder;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by vagrant on 8/9/16.
 */
public class Test {

    @ClassRule
    public static final DropwizardAppRule<MyConfiguration> RULE = new DropwizardAppRule<MyConfiguration>(MyApplication.class, "config.yaml");

    JwtBuilder jwt = new JwtBuilder();

    @org.junit.Test
    public void testGet() throws Exception
    {
        Client client = ClientBuilder.newBuilder().build();

        WebTarget target = client.target("http://localhost:8080/gimme/go");

        Response response = target.request().headers(jwt.buildJWTHeader(target,MediaType.TEXT_PLAIN, null))
                .get();

        System.out.println(response.readEntity(String.class));
        System.out.println(response.getStatus());
        assertThat((Integer) response.getStatus()).isEqualTo(200);
    }

    @org.junit.Test
    public void testFood() throws Exception
    {
        Client client = ClientBuilder.newBuilder().build();

        WebTarget target = client.target("http://localhost:8080/fish");

        Response response = target.request().headers(jwt.buildJWTHeader(target,MediaType.TEXT_PLAIN, null))
                .get();

        System.out.println(response.readEntity(String.class));
        System.out.println(response.getStatus());
        assertThat((Integer) response.getStatus()).isEqualTo(200);
    }

}
