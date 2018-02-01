package io.dashbase.auth;

import org.pac4j.core.profile.CommonProfile;
import org.pac4j.core.profile.UserProfile;
import org.pac4j.http.profile.RestProfile;

import java.security.Principal;

public class AuthenticatedUser implements Principal {

    private final UserProfile profile;

    public AuthenticatedUser(UserProfile profile) {
        this.profile = profile;
    }

    public UserProfile getProfile() {
        return profile;
    }

    @Override
    public String getName() {
        return profile.getId();
    }
}
