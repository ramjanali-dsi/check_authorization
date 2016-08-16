package com.dsi.checkauthorization.service;


/**
 * Created by sabbir on 6/30/16.
 */
public interface ApiService {

    boolean isAllowedApiByType(String url, String method, String type);

    boolean isAllowedApiByUserID(String userID, String url, String method);
}
