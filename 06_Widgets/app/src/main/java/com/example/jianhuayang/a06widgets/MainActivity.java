package com.example.jianhuayang.a06widgets;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private int progressBarStatus;
    private static int progressStatic;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        while (progressBarStatus < 100) {
                            progressBarStatus = doSomeWork();
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(progressStatic);
                                }
                            });
                        }
                    }

                    private int doSomeWork() {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return ++progressStatic;
                    }
                }


        ).start();
    }

}
