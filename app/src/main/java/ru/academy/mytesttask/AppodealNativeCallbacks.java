package ru.academy.mytesttask;

import android.app.Activity;

import com.appodeal.ads.NativeAd;
import com.appodeal.ads.NativeCallbacks;

public class AppodealNativeCallbacks implements NativeCallbacks {

    private final Activity activity;

    AppodealNativeCallbacks(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onNativeLoaded() {
    }

    @Override
    public void onNativeFailedToLoad(){
    }

    @Override
    public void onNativeShown(NativeAd nativeAd) {

    }

    @Override
    public void onNativeShowFailed(NativeAd nativeAd) {

    }

    @Override
    public void onNativeClicked(NativeAd nativeAd) {

    }

    @Override
    public void onNativeExpired() {

    }

}

