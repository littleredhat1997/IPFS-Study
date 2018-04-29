package com.cyber.blockchain.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

public class Constants {

    private static Properties p;

    // 初始化配置
    static {
        p = new Properties();
        InputStream in = Constants.class.getResourceAsStream("/config.properties");
        InputStreamReader r = new InputStreamReader(in, Charset.forName("UTF-8"));
        try {
            p.load(r);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * IPFS 集群 MASTER HOST 地址
     */
    public static final String IPFS_CLUSTER_MASTER_HOST = p.getProperty("ipfs.cluster.master.host");

    /**
     * IPFS 集群 HTTP API 地址
     */
    public static final String IPFS_CLUSTER_HTTP_API = p.getProperty("ipfs.cluster.http.api");
}
