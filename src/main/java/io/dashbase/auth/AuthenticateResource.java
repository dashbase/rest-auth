package io.dashbase.auth;

import io.dropwizard.auth.Auth;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/v1/auth")
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticateResource {

    @GET
    @Path("validate")
    public AuthenticatedUser valudate(@Auth AuthenticatedUser user) {
        return user;
    }
}
