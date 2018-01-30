### Simple Rest-based Basic Auth Authenticator

#### Build

```
mvn clean package
```

#### Run

```
java -jar target/rest-auth-*.jar server config.yml
```

go to: http://localhost:8080/v1/auth/validate

and login with credentials here: https://github.com/dashbase/rest-auth/blob/master/config.yml

#### Configuration

By defaut, we implement only a simple authenticator based on the configuration file.

To implement additional authenticators:

1. implement https://github.com/dashbase/rest-auth/blob/master/src/main/java/io/dashbase/auth/AuthenticatorFactory.java
2. add class to https://github.com/dashbase/rest-auth/blob/master/src/main/resources/META-INF/services/io.dashbase.auth.AuthenticatorFactory

See example at: https://github.com/dashbase/rest-auth/blob/master/src/main/java/io/dashbase/auth/ConfigAuthenticatorFactory.java
