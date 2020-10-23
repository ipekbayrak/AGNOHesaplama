package com.kardelenapp.agnohesaplama;

import android.widget.Button;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

/**
 * Created by mustafa on 1/29/2017.
 */

public class RewardedAds {
    private static final String AD_UNIT_ID = "INSERT_AD_UNIT_HERE";
    private static final String APP_ID = "INSERT_APP_ID_HERE";

    private RewardedVideoAd mRewardedVideoAd;
    private Button mRetryButton;
    private Button mShowVideoButton;
    private long mTimeRemaining;

    RewardedVideoAdListener adListener = new RewardedVideoAdListener() {
        @Override
        public void onRewardedVideoAdLoaded() {

        }

        @Override
        public void onRewardedVideoAdOpened() {

        }

        @Override
        public void onRewardedVideoStarted() {

        }

        @Override
        public void onRewardedVideoAdClosed() {

        }

        @Override
        public void onRewarded(RewardItem rewardItem) {

        }

        @Override
        public void onRewardedVideoAdLeftApplication() {

        }

        @Override
        public void onRewardedVideoAdFailedToLoad(int i) {

        }
    };

   /* MobileAds.initialize(this, APP_ID);

    mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
    mRewardedVideoAd.setRewardedVideoAdListener(adListener);
    loadRewardedVideoAd();

    mRewardedVideoAd.show();*/


    /*
    *  private void loadRewardedVideoAd() {
        if (!mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.loadAd(AD_UNIT_ID, new AdRequest.Builder().build());
        }
    }
    * */
}
