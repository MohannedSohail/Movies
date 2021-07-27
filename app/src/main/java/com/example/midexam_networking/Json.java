package com.example.midexam_networking;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class Json {

    private static final String TAG = "Json";


    public static String Films_Url="https://api.themoviedb.org/3/movie/top_rated?api_key=27230e1667c419dd452ea128b62c8055";

    public static URL curl(String url){

        URL url1 = null;
        try {
            url1 = new URL(url);
        } catch (
                MalformedURLException e) {
            e.printStackTrace();
        }
        return url1;
    }





    public static ArrayList<Attributes> getjson(String json) {

        ArrayList<Attributes> data = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray results = jsonObject.getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {

                JSONObject currentPlaceObject = results.getJSONObject(i);

                String img = currentPlaceObject.getString("poster_path");
                String titel=currentPlaceObject.getString("title");
                Attributes earthquake = new Attributes(titel,img);
                data.add(earthquake);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return data;
    }



    public static String makeHttpConnection(URL url){
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        String jsonResponse=null;
        try {

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(15000);
            urlConnection.setReadTimeout(10000);
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                //200 >> success
                inputStream = urlConnection.getInputStream();
                jsonResponse = readInputStream(inputStream);
            } else {
                Log.e(TAG, "Error response code : " + urlConnection.getResponseCode());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {

            if (urlConnection != null) urlConnection.disconnect();
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return  jsonResponse;
    }


    public static String readInputStream(InputStream inputStream) throws IOException {
        //read input stream and convert to json string

        StringBuilder response=new StringBuilder();
        if (inputStream!=null){
            InputStreamReader inputStreamReader=  new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader= new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();

            while (line!=null){
                response.append(line);
                line=bufferedReader.readLine();
            }

        }
        return response.toString();
    }



}
