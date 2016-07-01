package com.dsi.checkauthorization.dao;

import com.dsi.checkauthorization.model.UserSession;

/**
 * Created by sabbir on 6/15/16.
 */
public interface UserSessionDao {

    UserSession getUserSessionByUserIdAndAccessToken(String userID, String accessToken);
}
