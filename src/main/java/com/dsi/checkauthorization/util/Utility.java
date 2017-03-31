package com.dsi.checkauthorization.util;

import org.apache.log4j.Logger;

import java.util.*;

/**
 * Created by sabbir on 6/13/16.
 */
public class Utility {

    private static final Logger logger = Logger.getLogger(Utility.class);

    public static boolean isNullOrEmpty(String s){

        if(s==null ||s.isEmpty() ){
            return true;
        }
        return false;
    }

    public static boolean isNullOrEmpty(List list){

        if(list==null || list.size() == 0 ){
            return true;
        }
        return false;
    }

    public static final String generateRandomString(){
        return UUID.randomUUID().toString();
    }

    public static final Date today() {
        return new Date();
    }

    public static final String getFinalToken(String header) {
        String[] tokenPart = header.split("[\\$\\(\\)]");
        return tokenPart[2];
    }

    public static final String getTokenSecretKey(String key){
        byte[] valueDecoded = Base64.getDecoder().decode(key.getBytes());
        return new String(valueDecoded);
    }

    public static final List<String> getSystemInfoList(){
        List<String> systemList = new ArrayList<>();
        systemList.add("1b549aa6-fd9c-4e7f-941e-bbed0a4c6ec8");
        systemList.add("0759a760-b9df-4c8a-8a64-fa31f256d13b");
        return systemList;
    }

    public static final boolean findSystemInfo(String item){
        List<String> systemList = getSystemInfoList();
        for(String s:systemList){
            if(s.equals(item)){
                return true;
            }
        }
        return false;
    }
}
