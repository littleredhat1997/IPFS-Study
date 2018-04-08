var ipfsAPI = require('ipfs-api');

// connect to ipfs daemon API server
var ipfs = ipfsAPI('localhost', '5001', {protocol: 'http'}); // leaving out the arguments will default to these values

var hash = 'QmayXdjpWqb8LiZsGsUVNWQq3GvUn3F7Dx3L6qWsethvKZ';

test();

async function test() {
    await _add(hash);
    await _ls();
    await _rm(hash);
}

function _add(hash) {
    return new Promise(function (resolve, reject) {
        // Adds an IPFS object to the pinset and also stores it to the IPFS repo. pinset is the set of hashes currently pinned (not gc'able).
        ipfs.pin.add(hash, function (err) {
            if (err) {
                return console.error(err);
            }
            resolve(true);
        });
    });
}

function _ls() {
    return new Promise(function (resolve, reject) {
        // List all the objects pinned to local storage or under a specific hash.
        ipfs.pin.ls(function (err, pinset) {
            if (err) {
                return console.error(err);
            }
            console.log(pinset);
            resolve(true);
        });
    });
}

function _rm(hash) {
    return new Promise(function (resolve, reject) {
        // Remove a hash from the pinset.
        ipfs.pin.rm(hash, function (err, pinset) {
            if (err) {
                return console.error(err);
            }
            console.log(pinset); // prints the hashes that were unpinned
            resolve(true);
        });
    });
}