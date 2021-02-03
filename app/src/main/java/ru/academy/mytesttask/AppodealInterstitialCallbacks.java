package ru.academy.mytesttask;

import android.app.Activity;
import android.widget.Toast;

import com.appodeal.ads.InterstitialCallbacks;

class AppodealInterstitialCallbacks implements InterstitialCallbacks {

    private final Activity activity;
    private int showCounter = 0;

    AppodealInterstitialCallbacks(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onInterstitialLoaded(boolean isPrecache) {
    }

    @Override
    public void onInterstitialFailedToLoad() {
    }

    @Override
    public void onInterstitialShown() {
        showCounter++;
        Toast.makeText(activity, Integer.toString(showCounter), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInterstitialShowFailed() {
    }

    @Override
    public void onInterstitialClicked() {

    }

    @Override
    public void onInterstitialClosed() {

    }

    @Override
    public void onInterstitialExpired() {
    }

}

