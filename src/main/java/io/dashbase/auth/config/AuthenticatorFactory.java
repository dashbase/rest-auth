package io.dashbase.auth.config;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.dashbase.auth.AuthenticatedUser;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.jackson.Discoverable;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", defaultImpl = ConfigAuthenticatorFactory.class)
public interface AuthenticatorFactory extends Discoverable
{
    Authenticator<BasicCredentials, AuthenticatedUser> authenticator();
}
