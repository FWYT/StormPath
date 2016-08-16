//import com.stormpath.shiro.client.ClientFactory;
//import com.stormpath.shiro.realm.ApplicationRealm;
import com.google.common.collect.Lists;
import com.stormpath.sdk.client.Client;
import healthcheck.MyHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.katharsis.locator.JsonServiceLocator;
import io.katharsis.locator.SampleJsonServiceLocator;
import io.katharsis.queryParams.DefaultQueryParamsParser;
import io.katharsis.queryParams.QueryParamsBuilder;
import io.katharsis.rs.KatharsisFeature;
import org.eclipse.jetty.server.session.SessionHandler;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.secnod.dropwizard.shiro.ShiroBundle;
import org.secnod.dropwizard.shiro.ShiroConfiguration;
import resources.MyResource;


import static io.katharsis.rs.KatharsisProperties.RESOURCE_DEFAULT_DOMAIN;
import static io.katharsis.rs.KatharsisProperties.RESOURCE_SEARCH_PACKAGE;

/**
 * Created by vagrant on 8/9/16.
 */
public class MyApplication extends Application<MyConfiguration> {



    public static void main(String[] args) throws Exception {
        new MyApplication().run(args);

    }

    @Override
    public void initialize(Bootstrap<MyConfiguration> bootstrap) {
        // nothing to do yet
        bootstrap.addBundle(shiro);

    }

    private void registerRest(MyConfiguration configuration,
                  Environment environment)
    {
        final MyResource resource = new MyResource();
        environment.jersey().register(resource);
    }

    private void registerHealthCheck(MyConfiguration configuration,
                                      Environment environment)
    {
        final MyHealthCheck healthCheck = new MyHealthCheck();
        environment.healthChecks().register("template", healthCheck);
    }

    private final ShiroBundle<MyConfiguration> shiro = new ShiroBundle<MyConfiguration>() {
        @Override
        protected ShiroConfiguration narrow(MyConfiguration configuration) {
            return configuration.getShiroConfiguration();
        }
    };

    public void registerKatharsis(MyConfiguration configuration,
                                  Environment environment)
    {
        environment.jersey()
                .property(RESOURCE_DEFAULT_DOMAIN, configuration.katharsis.host);
        environment.jersey()
                .property(RESOURCE_SEARCH_PACKAGE, configuration.katharsis.searchPackage);

        KatharsisFeature katharsisFeature = new KatharsisFeature(environment.getObjectMapper(), new QueryParamsBuilder(new DefaultQueryParamsParser()),
                new SampleJsonServiceLocator());
        environment.jersey().register(katharsisFeature);
    }


    @Override
    public void run(MyConfiguration configuration,
                    Environment environment) {
        registerRest(configuration, environment);
        registerHealthCheck(configuration, environment);
        //registerAuth(configuration, environment);
        registerKatharsis(configuration, environment);
        environment.getApplicationContext().setSessionHandler(new SessionHandler());
        environment.jersey().register(RolesAllowedDynamicFeature.class);
    }

}
