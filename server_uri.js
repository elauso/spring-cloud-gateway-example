const http = require('http');
const os = require('os');
const url = require('url');

var handleRequest = function(req, res) {

if(req.url === '/hello-world' ) {
  res.writeHead(200, {"Content-Type": "application/json"});
  res.write("{\"result\":\"hello world!\"}");
  res.end();
 }
}

const server = http.createServer(handleRequest);
console.log("Listening on port 8081\n");

server.listen(8081);