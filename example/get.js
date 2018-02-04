var ipfsAPI = require('ipfs-api');

// connect to ipfs daemon API server
var ipfs = ipfsAPI('localhost', '5001', {protocol: 'http'}); // leaving out the arguments will default to these values

const validCID = 'QmPJ7gJZyoWEfKTRNERTywdeGB71krDs4LpPe2CdGgWXzK';
// QmaM1kvFK9HD4UHmG2J3vr7UV5FMuyvD86KJTYY7ijBjW8

// Fetch a example or an entire directory tree from IPFS that is addressed by a valid IPFS Path.
ipfs.files.get(validCID, function (err, files) {
    if (err) {
        return console.error(err);
    }
    files.forEach((file) => {
        console.log(file);
        if (file.content) console.log(file.content.toString('utf8'));
    });
});