### Simple Rest-based Basic Auth Authenticator

#### Build

```
./gradlew clean build
```

#### Run

```
./bin/start.sh config.dev.yml
```

go to: http://localhost:9478/login/basic

and login with credentials here: https://github.com/dashbase/rest-auth/blob/master/config.dev.yml

#### Configuration

Default Authenticators:

* Authenticator based on the configuration file: https://github.com/dashbase/rest-auth/blob/master/src/main/java/io/dashbase/auth/ConfigAuthenticatorFactory.java

* Authenticator via a rest api call to external server: https://github.com/dashbase/rest-auth/blob/master/src/main/java/io/dashbase/auth/RestAuthenticatorFactory.java

To implement additional authenticators:

1. implement https://github.com/dashbase/rest-auth/blob/master/src/main/java/io/dashbase/auth/AuthenticatorFactory.java
2. add class to https://github.com/dashbase/rest-auth/blob/master/src/main/resources/META-INF/services/io.dashbase.auth.AuthenticatorFactory

#### Python login program

Assuming the rest auth server is running on `localhost:9478`, you can also test authentication via the python login program (requires python 3).

```
python login.py
```
