package ru.academy.mytesttask;

import android.app.Activity;
import android.widget.Toast;

import com.appodeal.ads.NativeAd;
import com.appodeal.ads.NativeCallbacks;

public class AppodealNativeCallbacks implements NativeCallbacks {

    private final Activity activity;
    AppodealNativeCallbacks(Activity activity) {
        this.activity = activity;
    }
private int showCounter = 0;

    @Override
    public void onNativeLoaded() {
    }

    @Override
    public void onNativeFailedToLoad(){
    }

    @Override
    public void onNativeShown(NativeAd nativeAd) {
        showCounter++;
        Toast.makeText(activity, Integer.toString(showCounter), Toast.LENGTH_SHORT).show();
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

