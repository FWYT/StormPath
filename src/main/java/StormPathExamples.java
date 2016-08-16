/*import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.account.AccountList;
import com.stormpath.sdk.application.Application;
import com.stormpath.sdk.authc.AuthenticationRequest;
import com.stormpath.sdk.authc.AuthenticationResult;
import com.stormpath.sdk.authc.UsernamePasswordRequests;
import com.stormpath.sdk.client.Client;
import com.stormpath.sdk.client.Clients;
import com.stormpath.sdk.resource.ResourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vagrant on 8/8/16.
 */
/*
public class StormPathExamples {

    private static final Logger log = LoggerFactory.getLogger(StormPathExamples.class);

    public static void main(String[] args) throws Exception {
        Client client = Clients.builder().build();


        Application application =
                client.getResource("https://api.stormpath.com/v1/applications/1dbLlJbQzHh0KnXai01QWm",
                        Application.class);

        //add new account
        /*System.out.println("ADD ACCOUNT");
        Account account = client.instantiate(Account.class);

        account
                .setGivenName("Cat")
                .setSurname("Meow")
                .setUsername("meowmeow")
                .setEmail("meows@cats.com")
                .setPassword("All999FISH")
                .getCustomData().put("favoriteFood", "fish");

        account = application.createAccount(account);*/
/*
        //retrieve account
        System.out.println("RETRIEVE ACCOUNT");
        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("username", "meowmeow");
        AccountList accounts = application.getAccounts(queryParams);

        for (Account acct : accounts) {
            log.info("Found Account: " + acct.getHref() + ", " + acct.getEmail());
        }

        //authenticate
        System.out.println("AUTHENTICATE");
        AuthenticationRequest request = UsernamePasswordRequests.builder()
                .setUsernameOrEmail("meowmeow")
                .setPassword("All999FISH")
                .build();

        Account account = client.instantiate(Account.class);

        try {
            AuthenticationResult result = application.authenticateAccount(request);
            account = result.getAccount();
            log.info("Authenticated Account: " + account.getUsername() + ", Email: " + account.getEmail());
        } catch (ResourceException ex) {
            log.error(ex.getMessage());
        }

        Key key = MacProvider.generateKey();

        String compactJws = Jwts.builder()
                .setSubject("Kitties")
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();

        System.out.println("JWT: " + compactJws);

    }
}
*/