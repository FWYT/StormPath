package healthcheck;

import com.codahale.metrics.health.HealthCheck;

/**
 * Created by vagrant on 8/9/16.
 */

public class MyHealthCheck extends HealthCheck {
    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
