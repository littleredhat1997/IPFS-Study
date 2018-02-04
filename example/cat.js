var ipfsAPI = require('ipfs-api');

// connect to ipfs daemon API server
var ipfs = ipfsAPI('localhost', '5001', {protocol: 'http'}); // leaving out the arguments will default to these values

const validCID = 'QmQ4cKbXatsAJEM5hTyr444gmJ6hKAyQ7UgNbxhdScxTeR';
// QmWxNGaS8X53ftuTFGUb4UQpXyVbKhpZUfgrUXN7cC2hVP

// ipfs.files.cat(validCID, function (err, example) {
//     if (err) {
//         console.error(err);
//     } else {
//         console.log(example.toString('utf8'));
//     }
// });

// Returns a example addressed by a valid IPFS Path.
ipfs.files.cat(validCID, function (err, file) {
    if (err) {
        return console.error(err);
    }
    console.log(file.toString('utf8'));
});