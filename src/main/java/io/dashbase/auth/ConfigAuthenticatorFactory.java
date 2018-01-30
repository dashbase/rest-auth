package io.dashbase.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@JsonTypeName("config")
public class ConfigAuthenticatorFactory implements AuthenticatorFactory {

    @JsonProperty
    public Map<String, UserInfo> users = new HashMap<>();

    @Override
    public Authenticator<BasicCredentials, AuthenticatedUser> authenticator() {
        return new Authenticator<BasicCredentials, AuthenticatedUser>() {
            @Override
            public Optional<AuthenticatedUser> authenticate(BasicCredentials basicCredentials) throws AuthenticationException {
                String userName = basicCredentials.getUsername();
                UserInfo userInfo = users.get(userName);
                if (userInfo == null) {
                    return Optional.empty();
                }
                if (userInfo.password.equals(basicCredentials.getPassword())) {
                    AuthenticatedUser user = new AuthenticatedUser();
                    user.addAttributes(userInfo.attributes);
                    user.setId(userName);
                    user.addRoles(userInfo.roles);
                    return Optional.of(user);
                } else {
                    return Optional.empty();
                }
            }
        };
    }
}
