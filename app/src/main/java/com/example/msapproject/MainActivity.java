package com.example.msapproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private TextView cases;
    private TextView recovered;
    private TextView deaths;
    private TextView todayCases;
    private TextView todayRecovered;
    private TextView todayDeaths;
    private TextView activeCases;
    private TextView criticalCases;
    private TextView population;
    private TextView affectedCountries;
    private static final int NOTIFICATION_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle queryBundle = new Bundle();
        queryBundle.putString("a", "");
        setContentView(R.layout.activity_main);
        cases = (TextView) findViewById(R.id.cases);
        recovered = (TextView) findViewById(R.id.recovered);
        deaths = (TextView) findViewById(R.id.deaths);
        todayCases = (TextView) findViewById(R.id.todaycases);
        todayRecovered = (TextView) findViewById(R.id.todayrecovered);
        todayDeaths = (TextView) findViewById(R.id.todaydeaths);
        activeCases= (TextView) findViewById(R.id.active);
        criticalCases= (TextView) findViewById(R.id.critical);
        population= (TextView) findViewById(R.id.population);
        affectedCountries= (TextView) findViewById(R.id.affectedCountries);

        new Task(this).execute();
        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(this);
        cases.setText(prefs.getString("cases",""));
        recovered.setText(prefs.getString("recovered",""));
        deaths.setText(prefs.getString("deaths",""));
        todayCases.setText(prefs.getString("todayCases",""));
        todayRecovered.setText(prefs.getString("todayRecovered",""));
        todayDeaths.setText(prefs.getString("todayDeaths",""));
        activeCases.setText(prefs.getString("activeCases",""));
        criticalCases.setText(prefs.getString("criticalCases",""));
        population.setText(prefs.getString("population",""));
        affectedCountries.setText(prefs.getString("affectedCountries",""));

        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(this, NOTIFICATION_ID, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long repeatingInterval = AlarmManager.INTERVAL_DAY;
        long triggerTime = 0;
        if (alarmManager != null) {
            alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerTime, repeatingInterval, alarmPendingIntent);
        }


    }
}

