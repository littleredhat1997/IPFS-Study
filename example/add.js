var ipfsAPI = require('ipfs-api');

// connect to ipfs daemon API server
var ipfs = ipfsAPI('localhost', '5001', {protocol: 'http'}); // leaving out the arguments will default to these values

var fs = require("fs");

// read simultaneously
var buffer1 = fs.readFileSync('./my/file.txt');
var buffer2 = fs.readFileSync('./my/directory/file.txt');

const files = [{path: '/my/file.txt', content: buffer1}, {path: '/my/directory/file.txt', content: buffer2}];

// Add files and data to IPFS.
ipfs.files.add(files, {progress: (prog) => console.log(`received: ${prog}`)}, function (err, files) {
    if (err) {
        return console.error(err);
    }
    console.log(files);
});

// [
//     {
//         path: '/my/file.txt',
//         hash: 'QmQ4cKbXatsAJEM5hTyr444gmJ6hKAyQ7UgNbxhdScxTeR',
//         size: 201
//     },
//     {
//         path: '/my/directory/file.txt',
//         hash: 'QmWxNGaS8X53ftuTFGUb4UQpXyVbKhpZUfgrUXN7cC2hVP',
//         size: 391
//     },
//     {
//         path: 'my/directory',
//         hash: 'QmaM1kvFK9HD4UHmG2J3vr7UV5FMuyvD86KJTYY7ijBjW8',
//         size: 446
//     },
//     {
//         path: 'my',
//         hash: 'QmPJ7gJZyoWEfKTRNERTywdeGB71krDs4LpPe2CdGgWXzK',
//         size: 754
//     }
// ]