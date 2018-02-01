package io.dashbase.auth.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dashbase.auth.AuthenticatedUser;
import io.dashbase.auth.RestAuthenticatorFactory;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import org.pac4j.core.credentials.UsernamePasswordCredentials;
import org.pac4j.core.profile.UserProfile;
import org.pac4j.http.credentials.authenticator.RestAuthenticator;
import org.pac4j.http.profile.RestProfile;

import java.util.Optional;

public class RestAuthClient {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        RestAuthenticatorFactory restAuthenticatorFactory = new RestAuthenticatorFactory();
        restAuthenticatorFactory.setUrl("https://dashbase.io/login/basic");

        Authenticator<BasicCredentials, AuthenticatedUser> authenticator = restAuthenticatorFactory.authenticator();

        BasicCredentials credentials = new BasicCredentials("admin", "admin");

        Optional<AuthenticatedUser> user = authenticator.authenticate(credentials);

        if (user.isPresent()) {
            UserProfile profile = user.get().getProfile();
            if (profile != null) {
                System.out.println(mapper.writeValueAsString(profile));
            } else {
                System.out.println("user not authenticated");
            }
        } else {
            System.out.println("user not found");
        }


    }
}
