package io.dashbase.auth;

import io.dropwizard.auth.Auth;
import org.pac4j.core.profile.UserProfile;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticateResource {

    @GET
    @Path("basic")
    public UserProfile basicGet(@Auth AuthenticatedUser user) {
        return user.getProfile();
    }

    @POST
    @Path("basic")
    public UserProfile basicPost(@Auth AuthenticatedUser user) {
        return user.getProfile();
    }
}
