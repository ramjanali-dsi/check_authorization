package com.dsi.checkauthorization.service;

import com.dsi.checkauthorization.exception.CustomException;
import com.dsi.checkauthorization.model.Api;

/**
 * Created by sabbir on 6/30/16.
 */
public interface ApiService {

    boolean isAllowedApiByType(String url, String method, String type) throws CustomException;

    boolean isAllowedApiByUserID(String userID, String url, String method) throws CustomException;
}
