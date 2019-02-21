package com.redhat.helloworld.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

public class Consts {

    private static Properties p;

    // 初始化配置
    static {
        p = new Properties();
        InputStream in = Consts.class.getResourceAsStream("/config.properties");
        InputStreamReader r = new InputStreamReader(in, Charset.forName("UTF-8"));
        try {
            p.load(r);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 私网网关 - /ip4/127.0.0.1/tcp/5001
     */
    public static final String IPFS_PRIVATE_API = p.getProperty("ipfs.private.api");

    /**
     * 公网网关 - /dnsaddr/ipfs.infura.io/tcp/5001/https
     */
    public static final String IPFS_PUBLIC_API = p.getProperty("ipfs.public.api");
}
