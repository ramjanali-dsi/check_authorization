package com.dsi.checkauthorization.filter;

import com.dsi.checkauthorization.exception.ErrorContext;
import com.dsi.checkauthorization.exception.ErrorMessage;
import com.dsi.checkauthorization.service.impl.CheckAuthService;
import com.dsi.checkauthorization.util.Constants;
import com.dsi.checkauthorization.util.Utility;
import io.jsonwebtoken.Claims;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by sabbir on 6/27/16.
 */

public class CheckAuthorizationFilter implements ContainerRequestFilter {

    @Context
    HttpServletRequest request;

    private static final Logger logger = Logger.getLogger(CheckAuthorizationFilter.class);

    private static final CheckAuthService authService = new CheckAuthService();

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String accessToken = request.getHeader(Constants.AUTHORIZATION);
        String path = requestContext.getUriInfo().getPath();
        String method = requestContext.getRequest().getMethod();

        if(!Utility.isNullOrEmpty(request.getQueryString())){
            path = path + Constants.QUESTION_SIGN + request.getQueryString();
        }

        logger.info("Request path: " + path);
        logger.info("Request method: " + method);

        if(!path.startsWith(Constants.API_DOCS)) {

            if (Utility.isNullOrEmpty(accessToken)) {
                logger.info("AccessToken not defined.");

                if (!authService.isAllowedApiForPublic(path, method)) {

                    String system = request.getHeader(Constants.SYSTEM);
                    if (Utility.isNullOrEmpty(system)) {
                        ErrorContext errorContext = new ErrorContext(null, "System", "System header not defined.");
                        ErrorMessage errorMessage = new ErrorMessage(Constants.CHECK_AUTHORIZATION_SERVICE_0001,
                                Constants.CHECK_AUTHORIZATION_SERVICE_0001_DESCRIPTION, errorContext);

                        requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).entity(errorMessage).build());
                        return;
                    }

                    if (!Utility.findSystemInfo(system)) {
                        ErrorContext errorContext = new ErrorContext(system, "System", "System info not found: " + system);
                        ErrorMessage errorMessage = new ErrorMessage(Constants.CHECK_AUTHORIZATION_SERVICE_0001,
                                Constants.CHECK_AUTHORIZATION_SERVICE_0001_DESCRIPTION, errorContext);

                        requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).entity(errorMessage).build());
                        return;
                    }

                    if (!authService.isAllowedApiForSystem(path, method)) {
                        ErrorContext errorContext = new ErrorContext(null, "Api", "Api is not allowed by system url: " +
                                "" + path + " AND method: " + method);
                        ErrorMessage errorMessage = new ErrorMessage(Constants.CHECK_AUTHORIZATION_SERVICE_0001,
                                Constants.CHECK_AUTHORIZATION_SERVICE_0001_DESCRIPTION, errorContext);

                        requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).entity(errorMessage).build());
                        return;
                    }
                }
            } else {

                String finalAccessToken = Utility.getFinalToken(accessToken);
                logger.info("AccessToken found: " + accessToken);

                Claims tokenObj = authService.parseToken(finalAccessToken);
                if (tokenObj == null) {
                    ErrorContext errorContext = new ErrorContext(null, "AccessToken", "AccessToken parse failed.");
                    ErrorMessage errorMessage = new ErrorMessage(Constants.CHECK_AUTHORIZATION_SERVICE_0001,
                            Constants.CHECK_AUTHORIZATION_SERVICE_0001_DESCRIPTION, errorContext);

                    requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).entity(errorMessage).build());
                    return;
                }

                if (!authService.isUserSessionExist(tokenObj.getId(), accessToken)) {
                    ErrorContext errorContext = new ErrorContext(tokenObj.getId(), "UserSession", "UserSession don't exist by userID: "
                            + tokenObj.getId());
                    ErrorMessage errorMessage = new ErrorMessage(Constants.CHECK_AUTHORIZATION_SERVICE_0001,
                            Constants.CHECK_AUTHORIZATION_SERVICE_0001_DESCRIPTION, errorContext);

                    requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).entity(errorMessage).build());
                    return;
                }

                request.setAttribute(Constants.ACCESS_TOKEN, accessToken);
                request.setAttribute(Constants.USER_ID, tokenObj.getId());

                if (!authService.isAllowedApiForAuthenticated(path, method) &&
                        !authService.isAllowedApiByUserID(tokenObj.getId(), path, method)) {

                    ErrorContext errorContext = new ErrorContext(tokenObj.getId(), "Api", "Api is not allowed by userID: "
                            + tokenObj.getId());
                    ErrorMessage errorMessage = new ErrorMessage(Constants.CHECK_AUTHORIZATION_SERVICE_0001,
                            Constants.CHECK_AUTHORIZATION_SERVICE_0001_DESCRIPTION, errorContext);

                    requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).entity(errorMessage).build());
                    return;
                }
            }
        }

        logger.info("Authorized url found.");
    }
}
