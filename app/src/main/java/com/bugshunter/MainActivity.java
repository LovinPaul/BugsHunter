package com.bugshunter;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    private MainView main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if(savedInstanceState!=null){
//
//        }else{

            main = (MainView) findViewById(R.id.signature_canvas);
//        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        main.pause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        main.pause();
    }
}
