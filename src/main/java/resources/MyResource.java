package resources;

import com.stormpath.sdk.provider.StormpathProvider;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import shiro.JwtBuilder;

import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;

/**
 * Created by vagrant on 8/9/16.
 */
@Path("/gimme")
@Produces(MediaType.APPLICATION_JSON)
public class MyResource {


    @GET
    @Path("/go")
    public Response getResponse() {
        //Subject s = SecurityUtils.getSubject();

        //System.out.println("PERMISSION: " + "admin:create: " + s.isPermitted("admin:create"));
        //System.out.println("PERMISSION: " + "user:edit: " + s.isPermitted("user:edit"));

        /*if (!s.hasRole("https://api.stormpath.com/v1/groups/6EKpAf7DqOCZ4em1B4vtH3")){
            return Response.status(401).build();
        }*/

        return Response.ok("I SURVIVED!", MediaType.APPLICATION_JSON).build();
        //return Response.status(200).build();
    }
}
