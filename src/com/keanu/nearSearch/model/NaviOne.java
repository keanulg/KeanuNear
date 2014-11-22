package com.keanu.nearSearch.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2014-11-22 .
 */
public class NaviOne implements Serializable{
    private int naviOneId;
    private String naviOneName;

    public int getNaviOneId() {
        return naviOneId;
    }

    public String getNaviOneName() {
        return naviOneName;
    }

    public void setNaviOneId(int naviOneId) {
        this.naviOneId = naviOneId;
    }

    public void setNaviOneName(String naviOneName) {
        this.naviOneName = naviOneName;
    }
}
