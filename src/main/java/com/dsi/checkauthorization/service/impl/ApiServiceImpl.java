package com.dsi.checkauthorization.service.impl;

import com.dsi.checkauthorization.dao.ApiDao;
import com.dsi.checkauthorization.dao.impl.ApiDaoImpl;
import com.dsi.checkauthorization.service.ApiService;

/**
 * Created by sabbir on 6/30/16.
 */
public class ApiServiceImpl implements ApiService {

    private static final ApiDao apiDao = new ApiDaoImpl();

    @Override
    public boolean isAllowedApiByType(String url, String method, String type) {
        return apiDao.isAllowedApiByType(url, method, type);
    }

    @Override
    public boolean isAllowedApiByUserID(String userID, String url, String method) {
        return apiDao.isAllowedApiByUserID(userID, url, method);
    }
}
