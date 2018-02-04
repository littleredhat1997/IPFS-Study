# IPFS-Tutorials

## Environment
1. wget https://dist.ipfs.io/go-ipfs/v0.4.13/go-ipfs_v0.4.13_linux-amd64.tar.gz
2. tar -zxvf go-ipfs_v0.4.13_linux-amd64.tar.gz
3. cd go-ipfs
4. sudo mv ipfs /usr/bin/ipfs
5. sudo chmod 755 /usr/bin/ipfs
6. npm install --save ipfs-api

## Configure
1. run `ipfs init`
2. run `ipfs config --json API.HTTPHeaders.Access-Control-Allow-Methods '["PUT", "GET", "POST", "OPTIONS"]'`
3. run `ipfs config --json API.HTTPHeaders.Access-Control-Allow-Origin '["*"]'`

## Run
1. run `ipfs daemon`
2. open http://localhost:5001/webui

## Learn
please see more details from: https://ipfs.io/docs/ `and` https://github.com/ipfs/js-ipfs-api