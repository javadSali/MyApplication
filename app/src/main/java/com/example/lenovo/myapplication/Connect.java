package com.example.lenovo.myapplication;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by lenovo on 12/16/2017.
 */

public class Connect extends AsyncTask {

    public String link="";
    public String user="";
    public Connect(String link){
        this.link = link;
    }
    @Override
    protected Object doInBackground(Object[] params) {
        try
        {
            URL myLink = new URL(link);
            URLConnection connection = myLink.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String data = null;

            while((data=bufferedReader.readLine()) != null)
            {
                sb.append(data);
            }
            MainActivity.dataServer = sb.toString();

        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}