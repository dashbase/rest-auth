package io.dashbase.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import org.pac4j.core.credentials.UsernamePasswordCredentials;
import org.pac4j.core.exception.CredentialsException;
import org.pac4j.core.exception.HttpAction;
import org.pac4j.http.credentials.authenticator.RestAuthenticator;
import org.pac4j.core.profile.UserProfile;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@JsonTypeName("rest")
public class RestAuthenticatorFactory implements AuthenticatorFactory {

    private final ObjectMapper mapper = new ObjectMapper();

    @JsonProperty
    public String clientId = "rest-auth-factory";

    private RestAuthenticator restAuthenticator;

    public RestAuthenticatorFactory() {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        restAuthenticator = new RestAuthenticator(url);
        restAuthenticator.setMapper(mapper);
    }

    @Override
    public Authenticator<BasicCredentials, AuthenticatedUser> authenticator() {
        return new Authenticator<BasicCredentials, AuthenticatedUser>() {
            @Override
            public Optional<AuthenticatedUser> authenticate(BasicCredentials basicCredentials) throws AuthenticationException {

                UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(
                        basicCredentials.getUsername(), basicCredentials.getPassword(), clientId);

                try {
                    restAuthenticator.validate(credentials, null);
                    UserProfile profile = credentials.getUserProfile();
                    return Optional.of(new AuthenticatedUser(profile));
                } catch (HttpAction httpAction) {
                    throw new AuthenticationException(httpAction);
                } catch (CredentialsException e) {
                    return Optional.empty();
                }
            }
        };
    }
}
