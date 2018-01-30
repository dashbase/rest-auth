package io.dashbase.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

public class RestBasicAuthConfiguration extends Configuration {
    @JsonProperty
    public AuthenticatorFactory auth = new ConfigAuthenticatorFactory();
}
