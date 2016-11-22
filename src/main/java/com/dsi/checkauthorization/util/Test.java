package com.dsi.checkauthorization.util;

/**
 * Created by sabbir on 8/25/16.
 */
public class Test {

    public static void main(String[] args){

        String s = "v1/employee/1";
        String[] parse = s.split("/");

        for(int i=0; i<parse.length; i++){
            System.out.println(parse[i]);
        }

    }
}
