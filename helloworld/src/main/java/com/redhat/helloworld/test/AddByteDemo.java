package com.redhat.helloworld.test;

import com.redhat.helloworld.util.Consts;
import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;

import java.io.IOException;

public class AddByteDemo {

    public static void main(String[] args) throws IOException {

        // IPFS ipfs = new IPFS(Consts.IPFS_PRIVATE_API);
        IPFS ipfs = new IPFS(Consts.IPFS_PUBLIC_API);
        System.out.println("connected");
        System.out.println("id: " + ipfs.id());

        // 上传字节流
        byte[] b = "Hello from IPFS Gateway Checker\n".getBytes();
        NamedStreamable.ByteArrayWrapper file = new NamedStreamable.ByteArrayWrapper(b);
        MerkleNode node = ipfs.add(file).get(0);
        String hash = node.hash.toString();
        System.out.println(hash);

        // 下载 https://ipfs.infura.io/ipfs/Qmaisz6NMhDB51cCvNWa1GMS7LU1pAxdF4Ld6Ft9kZEP2a
        Multihash CID = Multihash.fromBase58(hash);
        byte[] content = ipfs.cat(CID);
        System.out.println(new String(content));
    }
}
