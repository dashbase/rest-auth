package io.dashbase.auth;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", defaultImpl = ConfigAuthenticatorFactory.class)
public interface AuthenticatorFactory {
    public Authenticator<BasicCredentials, AuthenticatedUser> authenticator();
}
