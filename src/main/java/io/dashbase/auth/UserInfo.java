package io.dashbase.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfo
{
    @JsonProperty
    @NotNull
    public String password = "";

    @JsonProperty
    public Set<String> roles = new HashSet<>();

    @JsonProperty
    public Map<String, Object> attributes = new HashMap<>();
}
