var ipfsAPI = require('ipfs-api');

// connect to ipfs daemon API server
var ipfs = ipfsAPI('localhost', '5001', {protocol: 'http'}); // leaving out the arguments will default to these values

var fs = require("fs");

// read simultaneously
var buffer = fs.readFileSync('resources/草帽.jpg');

const files = [{path: 'resources/草帽.mp4', content: buffer}];

// Add files and data to IPFS.
ipfs.files.add(files, {progress: (prog) => console.log(`received: ${prog}`)}, function (err, files) {
    if (err) {
        return console.error(err);
    }
    console.log(files);
});