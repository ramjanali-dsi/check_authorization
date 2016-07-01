package com.dsi.checkauthorization.exception;

import org.apache.log4j.Logger;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by sabbir on 6/20/16.
 */

@Provider
public class CustomExceptionHandler implements ExceptionMapper<CustomException> {

    private static final Logger logger = Logger.getLogger(CustomExceptionHandler.class);

    @Override
    public Response toResponse(CustomException ex) {
        logger.error("---Permission forbidden: " + ex.getMessage());

        return Response.status(Response.Status.FORBIDDEN).entity(ex.getErrorMessage()).build();
    }
}
