package com.dsi.checkauthorization.service.impl;

import com.dsi.checkauthorization.model.DefaultApiType;
import com.dsi.checkauthorization.model.UserSession;
import com.dsi.checkauthorization.service.ApiService;
import com.dsi.checkauthorization.service.UserSessionService;
import com.dsi.checkauthorization.util.Constants;
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
        logger.info("Is allowed api for public:: " + url);
        return apiService.isAllowedApiByType(url, method, DefaultApiType.PUBLIC.getValue());
    }

    public boolean isAllowedApiForSystem(String url, String method) {
        logger.info("Is allowed api for system:: " + url);
        return apiService.isAllowedApiByType(url, method, DefaultApiType.SYSTEM.getValue());
    }

    public boolean isAllowedApiForAuthenticated(String url, String method) {
        logger.info("Is allowed api for authenticated:: " + url);
        return apiService.isAllowedApiByType(url, method, DefaultApiType.AUTHENTICATED.getValue());
    }

    public boolean isAllowedApiByUserID(String userID, String url, String method) {
        logger.info("Is allowed api for user:: " + url);
        return apiService.isAllowedApiByUserID(userID, url, method);
    }

    public boolean isUserSessionExist(String userID, String accessToken) {
        logger.info("Is user session exist checking:: " + accessToken);
        UserSession userSession = userSessionService.getUserSessionByUserIdAndAccessToken(userID, accessToken);
        return userSession != null;
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
