# Widen Simple One-Way SSO Ruby Example

This is an example [Ruby](https://www.ruby-lang.org/) project that leverages [Sinatra](http://sinatrarb.com/) to illustrate how to easily implement Widen's Simple One-Way SSO, complete with fully functional examples.

## Usage

Simply clone and run:

```bash
$ git clone git@github.com:widen/widen-sso-examples
$ cd widen-sso-examples/widen-simple-one-way-sso-ruby
$ bundle install
$ bundle exec ruby app.rb
```

A running web application can now be accessed from http://localhost:8080 with the following resources available:

* http://localhost:8080/ - Root page with links to the following:
    * http://localhost:8080/post (HTTP POST Example)
    * http://localhost:8080/get (HTTP GET Example)

## Configuration

There are two configuration properties defined within the `app.rb` file. These can be modified to perform test validation against your own Collective endpoint using your supplied Shared Secret.

```ruby
ENDPOINT = 'https://example.widencollective.com/login/simple'
SHARED_SECRET = rand(36**8).to_s(36)
```

## Further Documentation

* https://community.widen.com/collective/s/article/How-do-I-configure-a-simple-one-way-HTTP-post-login-SSO
