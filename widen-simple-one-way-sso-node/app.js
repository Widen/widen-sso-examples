const md5 = require('md5');
const express = require('express')
const app = express()
const port = 8080

const endpoint = 'https://example.widencollective.com/auth/simple'
const sharedSecret = Math.random().toString(36).substring(2, 15)

app.get('/', function (req, res) { res.render('app') })
app.get('/post', function (req, res) { res.render('post', { endpoint, ...userFields() }) })
app.get('/get', function (req, res) {
  const queryParams = Object.entries(userFields()).map(([key, value]) => `${key}=${value}`).join('&')
  res.render('get', {
    endpointWithParams: `${endpoint}?${queryParams}`
  })
})

app.set('view engine', 'pug')
app.listen(port, () => console.log(`Widen Simple One-Way SSO Node.js Example app listening on port ${port}!`))

const userFields = () => {
  let fields = {
    timestamp: new Date().toUTCString(),
    guid: '123456789',
    email: 'example@widen.com',
    first_name: 'Example',
    last_name: 'User',
    roles: 'General Role'
  }
  console.log(`fields = ${JSON.stringify(fields)}`)

  // Calculating the signature...

  // First join all the field values without a delimiter (in ascending order by key)
  let fieldString = Object.keys(fields).sort().map(k => fields[k]).join('')
  console.log(`fieldString = ${fieldString}`)

  // Append the shared secret to the joined field String
  let fieldStringWithSharedSecret = fieldString + sharedSecret
  console.log(`fieldStringWithSharedSecret = ${fieldStringWithSharedSecret}`)

  // Calculate md5 hash of the field String with shared secret
  let signature = md5(fieldStringWithSharedSecret)
  console.log(`signature = ${signature}`)

  fields.signature = signature

  return fields
}
