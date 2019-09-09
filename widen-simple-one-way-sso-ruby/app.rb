require 'sinatra'
require 'haml'
require 'time'
require 'digest'

set :port, 8080

ENDPOINT = 'https://example.widencollective.com'
SHARED_SECRET = rand(36**8).to_s(36)

get '/' do
  haml :app
end

get '/post' do
  @endpoint = ENDPOINT
  @fields = userFields()
  haml :post
end

get '/get' do
  queryParams = userFields().map { |k,v| "#{k}=#{v}" }.join('&')
  @endpointWithParams = "#{ENDPOINT}?#{queryParams}"
  haml :get
end

def userFields
  fields = {
    :timestamp => Time.now.httpdate,
    :guid => '123456789',
    :email => 'example@widen.com',
    :first_name => 'Example',
    :last_name => 'User',
    :roles => 'General Role'
  }
  puts "fields = #{fields}"

  # Calculating the signature...

  # First join all the field values without a delimiter (in ascending order by key)
  fieldString = fields.sort_by { |key| key }.to_h.map { |k,v| v }.join('')
  puts "fieldString = #{fieldString}"

  # Append the shared secret to the joined field String
  fieldStringWithSharedSecret = fieldString + SHARED_SECRET
  puts "fieldStringWithSharedSecret = #{fieldStringWithSharedSecret}"

  # Calculate md5 hash of the field String with shared secret
  signature = Digest::MD5.new.update(fieldStringWithSharedSecret).hexdigest
  puts "signature = #{signature}"

  fields[:signature] = signature

  fields
end
