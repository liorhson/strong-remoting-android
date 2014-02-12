/**
 * Returns a secret message.
 */
function getSecret(callback) {
  callback(null, 'shhh!');
}
getSecret.shared = true;
getSecret.accepts = [];
getSecret.returns = [{ arg: 'data', type: 'string' }];
getSecret.http = { path: '/customizedGetSecret', verb: 'GET' };

/**
 * Takes a string and returns an updated string.
 */
function transform(str, callback) {
  callback(null, 'transformed: ' + str);
}
transform.shared = true;
transform.accepts = [{ arg: 'str', type: 'string' }];
transform.returns = [{ arg: 'data', type: 'string' }];
transform.http = { path: '/customizedTransform', verb: 'GET' };

/**
 * Takes a GeoPoint and returns back latitude and longitude
 */
function geopoint(here, callback) {
  callback(null, here.lat, here.lng);
}

geopoint.shared = true;
geopoint.accepts = [ {arg: 'here', type: 'GeoPoint', required: true }];
geopoint.returns = [
  {arg: 'lat', type: 'number'},
  {arg: 'lng', type: 'number'}
];
geopoint.http = { path: '/geopoint', verb: 'GET' };

/**
 * A stub for a listing function accepting a filter like
 *   ?filter=where[effectiveRange][gt]=900
 * This method returns back the filter object as JSON.
 *   { data: "{\"where\":{\"age\":{\"gt\":21}}}" }
 */
function list(filter, callback) {
  callback(null, JSON.stringify(filter));
}

list.shared = true;
list.accepts = [{ arg: 'filter', type: 'object', require: true }];
list.returns = [{ arg: 'data', type: 'string' }];
list.http = { path: '/list', verb: 'GET' };

function getAuthorizationHeader(auth, cb) {
    cb(null, auth);
}

getAuthorizationHeader.shared = true;
getAuthorizationHeader.accepts = [{ arg: 'auth', type: 'string', http: function(ctx) {
    return ctx.req.header('authorization');
}}];
getAuthorizationHeader.returns = [{ arg: 'data', type: 'string' }];
getAuthorizationHeader.http = { path: '/get-auth' };

module.exports = {
  getSecret: getSecret,
  transform: transform,
  geopoint: geopoint,
  getAuthorizationHeader: getAuthorizationHeader,
  list: list
};
