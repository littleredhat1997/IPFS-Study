var ipfsAPI = require('ipfs-api');
var fs = require('fs');

// connect to ipfs daemon API server
var ipfs = ipfsAPI('localhost', '5001', {protocol: 'http'}); // leaving out the arguments will default to these values

const validCID = 'QmayXdjpWqb8LiZsGsUVNWQq3GvUn3F7Dx3L6qWsethvKZ';

// Fetch a standalone or an entire directory tree from IPFS that is addressed by a valid IPFS Path.
ipfs.files.get(validCID, function (err, files) {
    if (err) {
        return console.error(err);
    }
    files.forEach((file) => {
        if (file.content) {
            // content = file.content.toString('utf8');
            console.log(file);

            fs.writeFile(file.path, file.content, {flag: 'w'}, function (err) {
                if (err) {
                    console.error(err);
                } else {
                    console.log('download successful!');
                }
            });
        }
    });
});