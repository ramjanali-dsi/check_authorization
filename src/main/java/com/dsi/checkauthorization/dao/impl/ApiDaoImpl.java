package com.dsi.checkauthorization.dao.impl;

import com.dsi.checkauthorization.dao.ApiDao;
import com.dsi.checkauthorization.model.Api;
import com.dsi.checkauthorization.model.DefaultApi;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Created by sabbir on 6/30/16.
 */
public class ApiDaoImpl extends BaseDao implements ApiDao {

    private static final Logger logger = Logger.getLogger(ApiDaoImpl.class);

    @Override
    public Api getApiByUrlAndMethod(String url, String method) {
        Session session = null;
        Api api = null;
        try{
            session = getSession();
            Query query = session.createQuery("FROM Api a WHERE a.url =:url AND a.method =:method AND a.isActive = true");
            query.setParameter("url", url);
            query.setParameter("method", method);

            api = (Api) query.uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Database error occurs when get: " + e.getMessage());
        } finally {
            if(session != null) {
                close(session);
            }
        }
        return api;
    }

    @Override
    public boolean isAllowedApiByType(String url, String method, String type) {
        Session session = null;
        DefaultApi defaultApi = null;
        try{
            session = getSession();
            Query query = session.createQuery("FROM DefaultApi da WHERE da.api.url =:url AND " +
                    "da.api.method =:method AND da.allowType =:allowType");
            query.setParameter("url", url);
            query.setParameter("method", method);
            query.setParameter("allowType", type);

            defaultApi = (DefaultApi) query.uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Database error occurs when get: " + e.getMessage());
        } finally {
            if(session != null) {
                close(session);
            }
        }

        if(defaultApi == null)
            return false;

        return true;
    }

    @Override
    public boolean isAllowedApiByUserID(String userID, String url, String method) {
        Session session = null;
        Api api = null;
        try{
            session = getSession();
            Query query = session.createQuery("FROM Api a WHERE a.url =:url AND a.method =:method AND a.apiId in (SELECT ma.api.apiId FROM MenuApi ma WHERE ma.menu.menuId " +
                    "in (SELECT rm.menu.menuId FROM RoleMenu rm WHERE rm.role.roleId in (SELECT ur.role.roleId FROM UserRole ur " +
                    "WHERE ur.user.userId =:userID)))");

            query.setParameter("url", url);
            query.setParameter("method", method);
            query.setParameter("userID", userID);

            api = (Api) query.uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Database error occurs when get: " + e.getMessage());
        } finally {
            if(session != null) {
                close(session);
            }
        }

        if(api == null)
            return false;

        return true;
    }
}
