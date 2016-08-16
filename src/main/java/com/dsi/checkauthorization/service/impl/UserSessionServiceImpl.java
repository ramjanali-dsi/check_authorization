package com.dsi.checkauthorization.service.impl;


import com.dsi.checkauthorization.dao.UserSessionDao;
import com.dsi.checkauthorization.dao.impl.UserSessionDaoImpl;
import com.dsi.checkauthorization.model.UserSession;
import com.dsi.checkauthorization.service.UserSessionService;

/**
 * Created by sabbir on 6/15/16.
 */
public class UserSessionServiceImpl implements UserSessionService {

    private static final UserSessionDao dao = new UserSessionDaoImpl();

    @Override
    public UserSession getUserSessionByUserIdAndAccessToken(String userID, String accessToken) {
        return dao.getUserSessionByUserIdAndAccessToken(userID, accessToken);
    }
}
