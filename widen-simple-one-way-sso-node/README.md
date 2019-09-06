# Widen Simple One-Way SSO Node.js Example

This is an example Node.js project that leverages Express to illustrate how to easily implement Widen's Simple One-Way SSO, complete with fully functional examples.

## Usage

Simply clone and run:

```bash
$ git clone git@github.com:widen/widen-sso-examples
$ cd widen-sso-examples/widen-simple-one-way-sso-node
$ node app.js
```

A running web application can now be accessed from http://localhost:8080 with the following resources available:

* http://localhost:8080/ - Root page with links to the following:
    * http://localhost:8080/post (HTTP POST Example)
    * http://localhost:8080/get (HTTP GET Example)

## Configuration

There are two configuration properties defined within the `app.js` file. These can be modified to perform test validation against your own Collective endpoint using your supplied Shared Secret.

```javascript
const endpoint = 'https://example.widencollective.com/auth/simple'
const sharedSecret = Math.random().toString(36).substring(2, 15)
```

## Further Documentation

* https://community.widen.com/collective/s/article/How-do-I-configure-a-simple-one-way-HTTP-post-login-SSO
