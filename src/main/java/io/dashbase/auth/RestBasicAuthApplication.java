package io.dashbase.auth;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class RestBasicAuthApplication extends Application<RestBasicAuthConfiguration> {
    @Override
    public void initialize(Bootstrap<RestBasicAuthConfiguration> bootstrap) {
        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(
            new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
                new EnvironmentVariableSubstitutor(false)
            )
        );
    }

    @Override
    public void run(RestBasicAuthConfiguration conf, Environment env) throws Exception {
        Authenticator<BasicCredentials, AuthenticatedUser> authenticator = conf.auth.authenticator();

        env.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<AuthenticatedUser>()
                .setAuthenticator(authenticator)
                //.setAuthorizer(authorizationService.authorizer())
                .buildAuthFilter()));
        env.jersey().register(new AuthValueFactoryProvider.Binder<>(AuthenticatedUser.class));
        //env.jersey().register(RolesAllowedDynamicFeature.class);
        env.jersey().register(new AuthenticateResource());
    }

    public static void main(String[] args) throws Exception {
        new RestBasicAuthApplication().run(args);
    }
}
