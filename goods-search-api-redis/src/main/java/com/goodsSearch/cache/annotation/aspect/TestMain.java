package com.goodsSearch.cache.annotation.aspect;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by hanhansongjiang on 17/5/13.
 */
public class TestMain {

    public  String staticImag="http://img.1000.com/qm-a-img/test5/";
    public static String img1="http://img.1000.com/qm-a-img/test2/889358/2f44fa4085bb97f7740c55d47882127f.jpg@244w_244h.jpg";
    public static String  img2="http://img.1000.com/qm-a-img/test2/889358/d8e96c5fc7395b0257ea9197549db203.jpg@244w_244h.jpg";


    public static void main(String args[]){



        exists(img2);


    }



    public static boolean exists(String URLName){
        try {
            HttpURLConnection.setFollowRedirects(false);
            // note : you may also need
            //        HttpURLConnection.setInstanceFollowRedirects(false)
            HttpURLConnection con =
                    (HttpURLConnection) new URL(URLName).openConnection();
            con.setRequestMethod("HEAD");
            return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}




