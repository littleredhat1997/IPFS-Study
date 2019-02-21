package com.redhat.helloworld.test;

import com.redhat.helloworld.util.Consts;
import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;

import java.io.File;
import java.io.IOException;

public class AddFileDemo {

    public static void main(String[] args) throws IOException {

        // IPFS ipfs = new IPFS(Consts.IPFS_PRIVATE_API);
        IPFS ipfs = new IPFS(Consts.IPFS_PUBLIC_API);
        System.out.println("connected");
        System.out.println("id: " + ipfs.id());

        // 上传文件
        File f = new File("src/main/resources/bg.jpg");
        NamedStreamable.FileWrapper file = new NamedStreamable.FileWrapper(f);
        MerkleNode node = ipfs.add(file).get(0);
        String hash = node.hash.toString();
        System.out.println(hash);

        // 下载 https://ipfs.infura.io/ipfs/QmdUmT1pEWNoGQtWVMQQJNuvLU9vDRptAqqZ6HYhfvHFKn
//        Multihash CID = Multihash.fromBase58(hash);
//        byte[] content = ipfs.cat(CID);
//        System.out.println(new String(content));
    }
}
