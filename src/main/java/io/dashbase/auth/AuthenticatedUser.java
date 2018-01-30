package io.dashbase.auth;

import org.pac4j.core.profile.CommonProfile;

import java.security.Principal;

public class AuthenticatedUser extends CommonProfile implements Principal {
    @Override
    public String getName() {
        return this.getId();
    }
}
