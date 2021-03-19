package ru.startandroid.kinopoiskappclocks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class LoadingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        Timer timer = new Timer();
        TimerTask ts = new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(LoadingScreen.this, SignIn.class));
            }
        };
        timer.schedule(ts, 3000L);
    }
}
