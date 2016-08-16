package shiro;

/**
 * Created by vagrant on 6/28/16.
 */
import org.apache.shiro.subject.Subject;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class JwtAuthenticationFilter extends AuthenticatingFilter {

    protected static final String AUTHORIZATION_HEADER = "Authorization";

    private transient static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private String authzScheme = "Bearer";

    public String getAuthzScheme() {
        return authzScheme;
    }

    public void setAuthzScheme(String authzScheme) {
        this.authzScheme = authzScheme;
    }

    public JwtAuthenticationFilter() {

    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        AuthenticationToken token = createToken(request, response);
        Subject subject = getSubject(request, response);
        subject.login(token);

        return subject.isAuthenticated();
    }

    protected String getAuthzHeader(ServletRequest request) {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        return httpRequest.getHeader(AUTHORIZATION_HEADER);
    }

    public String getPrincipalsAndCredentials(String authorizationHeader){//}, ServletRequest request) {
        if (authorizationHeader == null) {
            return null;
        }
        String[] authTokens = authorizationHeader.split(" ");
        if (authTokens == null || authTokens.length != 2) {
            return null;
        }
        return authTokens[1];
    }


    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        String authorizationHeader = getAuthzHeader(request);
        if (authorizationHeader == null || authorizationHeader.length() == 0) {
            return null;
        }

        if (log.isDebugEnabled()) {
            log.debug("Attempting to execute login with headers [" + authorizationHeader + "]");
        }

        String token = getPrincipalsAndCredentials(authorizationHeader);//, request);
        if (token == null) {
            return null;
        }

        String message = token;
        String sub = token;

        return createToken(message, sub, request, response);
    }

    @Override
    protected AuthenticationToken createToken(String message, String sub, ServletRequest request, ServletResponse response) {
        return new JwtAuthenticationToken(message, sub);
    }

}
