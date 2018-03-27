package io.dashbase.auth.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.dashbase.auth.AuthenticatedUser;
import io.dashbase.auth.UserInfo;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import org.pac4j.core.profile.CommonProfile;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@JsonTypeName("config")
public class ConfigAuthenticatorFactory implements AuthenticatorFactory
{
    @JsonProperty
    public Map<String, UserInfo> users = new HashMap<>();

    @Override
    public Authenticator<BasicCredentials, AuthenticatedUser> authenticator() {
        return basicCredentials -> {
            String userName = basicCredentials.getUsername();
            UserInfo userInfo = users.get(userName);
            if (userInfo != null && userInfo.password.equals(basicCredentials.getPassword())) {
                CommonProfile profile = new CommonProfile();
                profile.addAttributes(userInfo.attributes);
                profile.setId(userName);
                profile.addRoles(userInfo.roles);
                AuthenticatedUser user = new AuthenticatedUser(profile);
                return Optional.of(user);
            } else {
                return Optional.empty();
            }
        };
    }
}
