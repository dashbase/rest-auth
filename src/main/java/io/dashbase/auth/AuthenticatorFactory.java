package io.dashbase.auth;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.jackson.Discoverable;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", defaultImpl = ConfigAuthenticatorFactory.class)
public interface AuthenticatorFactory extends Discoverable {
    public Authenticator<BasicCredentials, AuthenticatedUser> authenticator();
}
