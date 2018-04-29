package com.cyber.blockchain.ipfs;

import com.cyber.blockchain.util.Constants;
import com.cyber.blockchain.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;
import net.dongliu.requests.Requests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IPFSController {

    private IPFS ipfs = null;

    /**
     * 备注：
     * 1. 后续添加异常处理
     */
    public IPFSController() {
        ipfs = new IPFS(Constants.IPFS_CLUSTER_MASTER_HOST);
    }

    /**
     * 上传文件
     *
     * @param content 文件流
     * @return 文件寻址
     * @throws IOException
     */
    private String AddFile(byte[] content) throws IOException {
        NamedStreamable.ByteArrayWrapper file = new NamedStreamable.ByteArrayWrapper("", content);
        MerkleNode node = ipfs.add(file).get(0);
        String hash = node.hash.toString();
        return hash;
    }

    /**
     * 下载文件
     *
     * @param hash 文件寻址
     * @return 文件字节数组
     * @throws IOException
     */
    private byte[] GetFile(String hash) throws IOException {
        Multihash CID = Multihash.fromBase58(hash);
        byte[] content = ipfs.cat(CID);
        return content;
    }

    /**
     * 查询集群全部文件
     *
     * @return 文件寻址列表
     */
    private static List<String> LsAllFileCluster() {
        String url = Constants.IPFS_CLUSTER_HTTP_API + "/pins";
        String resp = Requests.get(url).send().readToText();
        System.out.println(resp);

        // GSON ? FastJSON
        List<PIN> jsonArr = new Gson().fromJson(resp, new TypeToken<List<PIN>>() {}.getType());
        List<String> hashList = new ArrayList<String>();
        for (PIN pin : jsonArr) {
            Map<String, PINDetail> pinMap = pin.getPeer_map();
            for (Map.Entry<String, PINDetail> entry : pinMap.entrySet()) {
                PINDetail pd = entry.getValue();
                if (pd.getError().isEmpty() && pd.getStatus().equals("pinned")) {
                    String hash = pin.getCid();
                    hashList.add(hash);
                }
            }
        }
        return hashList;
    }

    /**
     * 辅助函数
     * 集群上传
     *
     * @param hash 文件寻址
     * @return 是否集群上传成功
     */
    private boolean AddFileCluster(String hash) {
        String url = Constants.IPFS_CLUSTER_HTTP_API + "/pins/" + hash;
        String resp = Requests.post(url).send().readToText();
        if (resp.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 辅助函数
     * 集群删除
     *
     * @param hash 文件寻址
     * @return 是否集群删除成功
     */
    private boolean DeleteFileCluster(String hash) {
        String url = Constants.IPFS_CLUSTER_HTTP_API + "/pins/" + hash;
        String resp = Requests.delete(url).send().readToText();
        if (resp.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * IPFS IPFS IPFS IPFS IPFS IPFS IPFS IPFS IPFS IPFS
     * IPFS IPFS IPFS IPFS IPFS IPFS IPFS IPFS IPFS IPFS
     * IPFS IPFS IPFS IPFS IPFS IPFS IPFS IPFS IPFS IPFS
     * IPFS IPFS IPFS IPFS IPFS IPFS IPFS IPFS IPFS IPFS
     * IPFS IPFS IPFS IPFS IPFS IPFS IPFS IPFS IPFS IPFS
     */

    /**
     * 上传文件
     *
     * @param content 文件流
     * @return 文件寻址
     */
    public String AddFileIPFS(byte[] content) {
        try {
            String hash = AddFile(content);
            if (AddFileCluster(hash)) {
                Log.info("上传文件成功: " + hash);
                return hash;
            }
        } catch (IOException e) {
            Log.error(e.getMessage());
        }
        return null;
    }

    /**
     * 批量文件上传
     *
     * @param contents 文件流列表
     * @return 文件寻址列表
     */
    public List<String> AddFileBatchIPFS(List<byte[]> contents) {
        try {
            List<String> hashs = new ArrayList<String>();
            for (byte[] content : contents) {
                String hash = AddFile(content);
                if (AddFileCluster(hash)) {
                    hashs.add(hash);
                }
            }
            Log.info("批量上传文件成功: " + hashs);
            return hashs;
        } catch (IOException e) {
            Log.error(e.getMessage());
        }
        return null;
    }

    /**
     * 下载文件
     *
     * @param hash 文件寻址
     * @return 文件流
     */
    public byte[] GetFileIPFS(String hash) {
        try {
            return GetFile(hash);
        } catch (IOException e) {
            Log.error(e.getMessage());
        }
        return null;
    }

    /**
     * 批量下载文件
     *
     * @param hashs 文件寻址列表
     * @return 文件流列表
     */
    public List<byte[]> GetFileBatchIPFS(List<String> hashs) {
        try {
            List<byte[]> contents = new ArrayList<byte[]>();
            for (String hash : hashs) {
                byte[] content = GetFile(hash);
                contents.add(content);
            }
            return contents;
        } catch (IOException e) {
            Log.error(e.getMessage());
        }
        return null;
    }

    /**
     * 删除集群文件
     *
     * @param hash 文件寻址
     * @return 删除文件的寻址
     */
    public String DeleteFileIPFS(String hash) {
        boolean res = DeleteFileCluster(hash);
        if (res) {
            Log.info("文件删除成功: " + res);
            return hash;
        }
        return null;
    }

    /**
     * 批量删除集群文件
     *
     * @param hashs 文件寻址列表
     * @return 删除文件的寻址列表
     */
    public List<String> DeleteBatchFileIPFS(List<String> hashs) {
        List<String> result = new ArrayList<String>();
        for (String hash : hashs) {
            boolean res = DeleteFileCluster(hash);
            if (res) {
                result.add(hash);
            }
        }
        Log.info("批量删除文件成功: " + result);
        return result;
    }

    /**
     * 查询集群文件
     *
     * @return 文件寻址列表
     */
    public List<String> LsFileIPFS() {
        return LsAllFileCluster();
    }
}
