package com.dsi.checkauthorization.service.impl;

import com.dsi.checkauthorization.exception.CustomException;
import com.dsi.checkauthorization.model.DefaultApiType;
import com.dsi.checkauthorization.model.UserSession;
import com.dsi.checkauthorization.service.ApiService;
import com.dsi.checkauthorization.service.UserSessionService;
import com.dsi.checkauthorization.util.Constants;
import com.dsi.checkauthorization.util.SessionUtil;
import com.dsi.checkauthorization.util.Utility;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.log4j.Logger;

import javax.xml.bind.DatatypeConverter;

/**
 * Created by sabbir on 6/30/16.
 */
public class CheckAuthService {

    private static final Logger logger = Logger.getLogger(CheckAuthService.class);

    private static final ApiService apiService = new ApiServiceImpl();
    private static final UserSessionService userSessionService = new UserSessionServiceImpl();

    public boolean isAllowedApiForPublic(String url, String method) {
        boolean res = false;
        try {
            res = apiService.isAllowedApiByType(url, method, DefaultApiType.PUBLIC.getValue());

        } catch (CustomException e) {
            e.printStackTrace();
            logger.error("Check Api allowed for public failed: " + e.getMessage());
        }
        return res;
    }

    public boolean isAllowedApiForSystem(String url, String method) {
        boolean res = false;
        try {
            res = apiService.isAllowedApiByType(url, method, DefaultApiType.SYSTEM.getValue());

        } catch (CustomException e) {
            e.printStackTrace();
            logger.error("Check Api allowed for system failed: " + e.getMessage());
        }
        return res;
    }

    public boolean isAllowedApiForAuthenticated(String url, String method) {
        boolean res = false;
        try {
            res = apiService.isAllowedApiByType(url, method, DefaultApiType.AUTHENTICATED.getValue());

        } catch (CustomException e) {
            e.printStackTrace();
            logger.error("Check Api allowed for authenticated failed: " + e.getMessage());
        }
        return res;
    }

    public boolean isAllowedApiByUserID(String userID, String url, String method) {
        boolean res = false;
        try {
            res = apiService.isAllowedApiByUserID(userID, url, method);

        } catch (CustomException e) {
            e.printStackTrace();
            logger.error("Check Api allowed for userID failed: " + e.getMessage());
        }
        return res;
    }

    public boolean isUserSessionExist(String userID, String accessToken) {
        UserSession userSession = null;
        try {
            userSession = userSessionService.getUserSessionByUserIdAndAccessToken(userID, accessToken);

        } catch (CustomException e) {
            e.printStackTrace();
            logger.error("Check UserSession exist failed: " + e.getMessage());
        }

        if(userSession == null)
            return false;

        return true;
    }

    public Claims parseToken(String accessToken) {
        try {
            return Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(Utility.getTokenSecretKey(Constants.SECRET_KEY)))
                    .parseClaimsJws(accessToken).getBody();

        } catch (Exception e){
            logger.error("Failed to parse token: " + e.getMessage());
        }
        return null;
    }
}
