package com.dsi.checkauthorization.service;

import com.dsi.checkauthorization.exception.CustomException;
import com.dsi.checkauthorization.model.UserSession;

/**
 * Created by sabbir on 7/1/16.
 */
public interface UserSessionService {

    UserSession getUserSessionByUserIdAndAccessToken(String userID, String accessToken) throws CustomException;
}
