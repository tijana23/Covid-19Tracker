package com.example.msapproject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {
    private  static final String Emulator_URL="https://disease.sh/v3/covid-19/all";

    static String Connect(){
        InputStream inputStream=null;
        HttpURLConnection conn=null;
        String resultString=null;
        try{
            URL url =new URL(Emulator_URL);
            conn=(HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            inputStream=conn.getInputStream();
            StringBuilder builder = new StringBuilder();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line + "\n");
            }
            if (builder.length() == 0) {
                return null;
            }
            resultString = builder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            conn.disconnect();
        }
        return resultString;

    }
}
