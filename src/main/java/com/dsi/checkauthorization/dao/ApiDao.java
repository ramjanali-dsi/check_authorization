package com.dsi.checkauthorization.dao;

import com.dsi.checkauthorization.model.Api;

/**
 * Created by sabbir on 6/30/16.
 */
public interface ApiDao {

    Api getApiByUrlAndMethod(String url, String method);

    boolean isAllowedApiByType(String apiID, String type);

    boolean isAllowedApiByUserID(String userID);
}
