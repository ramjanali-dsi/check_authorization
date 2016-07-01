package com.dsi.checkauthorization.model;

/**
 * Created by sabbir on 6/30/16.
 */
public enum DefaultApiType {

    PUBLIC("Public"), AUTHENTICATED("Authenticated"), SYSTEM("System");
    private String value;

    DefaultApiType(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
