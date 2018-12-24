package com.food.test.finalproject.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlToBitmap {
    Bitmap bitmap;
    public Bitmap returnBitMap(final String url){

        class ThreadA extends Thread {

            public void run() {
                URL imageurl = null;

                try {
                    imageurl = new URL(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    HttpURLConnection conn = (HttpURLConnection)imageurl.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try{
            ThreadA a =new ThreadA();
            a.start();
            a.join();
        }catch (Exception e){
            e.printStackTrace();
        }

        return bitmap;
    }
}
