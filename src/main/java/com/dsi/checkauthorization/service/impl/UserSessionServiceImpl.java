package com.dsi.checkauthorization.service.impl;


import com.dsi.checkauthorization.dao.UserSessionDao;
import com.dsi.checkauthorization.dao.impl.UserSessionDaoImpl;
import com.dsi.checkauthorization.exception.CustomException;
import com.dsi.checkauthorization.exception.ErrorContext;
import com.dsi.checkauthorization.exception.ErrorMessage;
import com.dsi.checkauthorization.model.UserSession;
import com.dsi.checkauthorization.service.UserSessionService;
import com.dsi.checkauthorization.util.Constants;

/**
 * Created by sabbir on 6/15/16.
 */
public class UserSessionServiceImpl implements UserSessionService {

    private static final UserSessionDao dao = new UserSessionDaoImpl();

    @Override
    public UserSession getUserSessionByUserIdAndAccessToken(String userID, String accessToken) throws CustomException {
        UserSession userSession = dao.getUserSessionByUserIdAndAccessToken(userID, accessToken);
        if(userSession == null){
            ErrorContext errorContext = new ErrorContext(null, "UserSession", "User session not found by userID: "
                    + userID +" & accessToken: "+ accessToken);
            ErrorMessage errorMessage = new ErrorMessage(Constants.CHECK_AUTHORIZATION_SERVICE_0001,
                    Constants.CHECK_AUTHORIZATION_SERVICE_0001_DESCRIPTION, errorContext);
            throw new CustomException(errorMessage);
        }
        return userSession;
    }
}
