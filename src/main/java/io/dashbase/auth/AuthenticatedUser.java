package io.dashbase.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import jersey.repackaged.com.google.common.base.Preconditions;
import org.pac4j.core.profile.UserProfile;

import java.security.Principal;
import java.util.Set;

public class AuthenticatedUser implements Principal
{
  @JsonProperty
  private final String id;

  @JsonProperty
  private final Set<String> roles;

  public AuthenticatedUser(UserProfile profile) {
    Preconditions.checkNotNull(profile);

    this.id = profile.getId();
    this.roles = profile.getRoles();
  }

  @Override
  public String getName() {
    return id;
  }

  public Set<String> getRoles() {
    return roles;
  }
}
