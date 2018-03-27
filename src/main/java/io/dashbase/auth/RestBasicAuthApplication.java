package io.dashbase.auth;

import io.dashbase.auth.config.RestBasicAuthConfiguration;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.setup.Environment;

public class RestBasicAuthApplication extends Application<RestBasicAuthConfiguration>
{
  @Override
  public void run(RestBasicAuthConfiguration conf, Environment env) throws Exception {
    Authenticator<BasicCredentials, AuthenticatedUser> authenticator = conf.auth.authenticator();

    env.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<AuthenticatedUser>()
            .setAuthenticator(authenticator)
            .buildAuthFilter()));
    env.jersey().register(new AuthValueFactoryProvider.Binder<>(AuthenticatedUser.class));
    env.jersey().register(new AuthenticateResource());
  }

  public static void main(String[] args) throws Exception {
    new RestBasicAuthApplication().run(args);
  }
}
