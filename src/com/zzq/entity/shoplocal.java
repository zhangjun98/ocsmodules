package com.zzq.entity;

public class shoplocal {
    private Integer localid;

    private String shopinglocal;

    public Integer getLocalid() {
        return localid;
    }

    public void setLocalid(Integer localid) {
        this.localid = localid;
    }

    public String getShopinglocal() {
        return shopinglocal;
    }

    public void setShopinglocal(String shopinglocal) {
        this.shopinglocal = shopinglocal == null ? null : shopinglocal.trim();
    }
}