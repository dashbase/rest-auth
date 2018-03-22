package io.dashbase.auth;

import io.dropwizard.auth.Auth;
import org.pac4j.core.profile.UserProfile;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/v1/auth")
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticateResource {

    @GET
    @Path("validate")
    public UserProfile validateGet(@Auth AuthenticatedUser user) {
        return user.getProfile();
    }

    @POST
    @Path("validate")
    public UserProfile validatePost(@Auth AuthenticatedUser user) {
        return user.getProfile();
    }
}
