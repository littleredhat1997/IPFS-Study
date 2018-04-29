package com.cyber.blockchain.ipfs;

import java.util.Map;

public class PIN {

    private String cid;

    private Map<String, PINDetail> peer_map;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Map<String, PINDetail> getPeer_map() {
        return peer_map;
    }

    public void setPeer_map(Map<String, PINDetail> peer_map) {
        this.peer_map = peer_map;
    }
}
