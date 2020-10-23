package com.kardelenapp.agnohesaplama;

/**
 * Created by mustafa on 1/22/2017.
 */


import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.util.Timer;
import java.util.TimerTask;

import io.paperdb.Paper;

public class SonucEkran extends AppCompatActivity {

    private static final String AD_UNIT_ID = "INSERT_AD_UNIT_HERE";
    private static final String APP_ID = "INSERT_APP_ID_HERE";


    InterstitialAd mInterstitialAd;

    boolean showed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sonucekrani);

        TextView textViewKredi = (TextView) findViewById(R.id.textViewKredi);
        TextView textViewNot = (TextView) findViewById(R.id.textViewNot);

        Paper.init(getApplicationContext());

        float sonuc = Paper.book().read("sonuc");
        float kreditoplam = Paper.book().read("kreditoplam");

        textViewKredi.setText(   String.valueOf(kreditoplam));
        textViewNot.setText(   String.valueOf(sonuc));



        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3312738864772003/3874422574");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                //requestNewInterstitial();
                super.onAdClosed();

            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mInterstitialAd.show();
            }
        });

        requestNewInterstitial();

        Timer timer = new Timer();






    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                //.addTestDevice("DBAD9A986CC0C65B0A00DB764D1F7F41")
                .build();

        mInterstitialAd.loadAd(adRequest);

    }


        @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        ActionBar actionBar;
        actionBar = getSupportActionBar();

        if (actionBar != null) {
            // enabling action bar app icon and behaving it as toggle button
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setIcon(R.drawable.save);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.entry2, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
               // return true;





            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }


}