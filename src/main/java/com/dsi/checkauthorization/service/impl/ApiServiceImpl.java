package com.dsi.checkauthorization.service.impl;

import com.dsi.checkauthorization.dao.ApiDao;
import com.dsi.checkauthorization.dao.impl.ApiDaoImpl;
import com.dsi.checkauthorization.exception.CustomException;
import com.dsi.checkauthorization.exception.ErrorContext;
import com.dsi.checkauthorization.exception.ErrorMessage;
import com.dsi.checkauthorization.model.Api;
import com.dsi.checkauthorization.service.ApiService;
import com.dsi.checkauthorization.util.Constants;

/**
 * Created by sabbir on 6/30/16.
 */
public class ApiServiceImpl implements ApiService {

    private static final ApiDao apiDao = new ApiDaoImpl();

    @Override
    public boolean isAllowedApiByType(String url, String method, String type) throws CustomException {
        Api api = apiDao.getApiByUrlAndMethod(url, method);
        if(api == null){
            ErrorContext errorContext = new ErrorContext(null, "Api", "Api is not found by url: "
                    + url + " And method: " + method);
            ErrorMessage errorMessage = new ErrorMessage(Constants.CHECK_AUTHORIZATION_SERVICE_0001,
                    Constants.CHECK_AUTHORIZATION_SERVICE_0001_DESCRIPTION, errorContext);
            throw new CustomException(errorMessage);
        }

        boolean res = apiDao.isAllowedApiByType(api.getApiId(), type);
        if(!res){
            ErrorContext errorContext = new ErrorContext(null, "Api", "Api is not allowed by type: " + type);
            ErrorMessage errorMessage = new ErrorMessage(Constants.CHECK_AUTHORIZATION_SERVICE_0001,
                    Constants.CHECK_AUTHORIZATION_SERVICE_0001_DESCRIPTION, errorContext);
            throw new CustomException(errorMessage);
        }
        return true;
    }

    @Override
    public boolean isAllowedApiByUserID(String userID, String url, String method) throws CustomException {
        Api api = apiDao.getApiByUrlAndMethod(url, method);
        if(api == null){
            ErrorContext errorContext = new ErrorContext(null, "Api", "Api is not found by url: "
                    + url + " And method: " + method);
            ErrorMessage errorMessage = new ErrorMessage(Constants.CHECK_AUTHORIZATION_SERVICE_0001,
                    Constants.CHECK_AUTHORIZATION_SERVICE_0001_DESCRIPTION, errorContext);
            throw new CustomException(errorMessage);
        }

        boolean res = apiDao.isAllowedApiByUserID(userID);
        if(!res){
            ErrorContext errorContext = new ErrorContext(userID, "Api", "Api is not allowed by userID: " + userID);
            ErrorMessage errorMessage = new ErrorMessage(Constants.CHECK_AUTHORIZATION_SERVICE_0001,
                    Constants.CHECK_AUTHORIZATION_SERVICE_0001_DESCRIPTION, errorContext);
            throw new CustomException(errorMessage);
        }
        return true;
    }
}
