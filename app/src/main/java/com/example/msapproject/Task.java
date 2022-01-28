package com.example.msapproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Task extends AsyncTask<Void,Void,String> {
    public Context context;

    Task(Context context){
        this.context=context;

    }
    @Override
    protected String doInBackground(Void... voids) {
        return NetworkUtils.Connect();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            JSONObject object= new JSONObject(s);
            String json="Today Cases: "+object.getString("todayCases")+"\nToday Recovered: "+object.getString("todayRecovered")+"\nToday Deaths: "+object.getString("todayDeaths");
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("cases", object.getString("cases"));
            editor.putString("recovered",object.getString("recovered"));
            editor.putString("deaths",object.getString("deaths"));
            editor.putString("todayCases",object.getString("todayCases"));
            editor.putString("todayRecovered",object.getString("todayRecovered"));
            editor.putString("todayDeaths",object.getString("todayDeaths"));
            editor.putString("activeCases",object.getString("active"));
            editor.putString("criticalCases",object.getString("criticalCases"));
            editor.putString("population",object.getString("population"));
            editor.putString("affectedCountries",object.getString("affectedCountries"));
            editor.commit();
            new NotifyMe(context).createNotificationChannel(json);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}

