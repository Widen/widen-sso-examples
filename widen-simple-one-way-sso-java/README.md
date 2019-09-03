# Widen Simple One-Way SSO Java Example

This is an example Java project that leverages Sprint Boot to illustrate how to easily implement Widen's Simple One-Way SSO, complete with fully functional examples.

## Usage

Simply clone and run:

```bash
$ git clone git@github.com:widen/widen-sso-examples
$ cd widen-sso-examples/widen-simple-one-way-sso-java
$ ./gradlew bootRun
```

A running web application can now be accessed from http://localhost:8080 with the following resources available:

* http://localhost:8080/ - Root page with links to the following:
    * http://localhost:8080/post (HTTP POST Example)
    * http://localhost:8080/get (HTTP GET Example)

## Configuration

There are two configuration properties defined within the `src/main/resources/application.properties` file. These can be modified to perform test validation against your own Collective endpoint using your supplied Shared Secret.

```
com.widen.sso.simple.endpoint=https://example.widencollective.com/auth/simple
com.widen.sso.simple.shared-secret=${random.uuid}
```

## Further Documentation

* https://community.widen.com/collective/s/article/How-do-I-configure-a-simple-one-way-HTTP-post-login-SSO
