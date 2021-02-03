package ru.academy.mytesttask;

import android.app.Activity;

import android.app.Activity;
import android.widget.Toast;

import com.appodeal.ads.RewardedVideoCallbacks;

import java.util.Locale;

class AppodealRewardedVideoCallbacks implements RewardedVideoCallbacks {

    private final Activity activity;

    AppodealRewardedVideoCallbacks(Activity activity) {
        this.activity = activity;
    }
    private int showCounter = 0;

    @Override
    public void onRewardedVideoLoaded(boolean isPrecache) {
    }

    @Override
    public void onRewardedVideoFailedToLoad() {
    }

    @Override
    public void onRewardedVideoShown() {
        showCounter++;
        Toast.makeText(activity, Integer.toString(showCounter), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoShowFailed() {
    }

    @Override
    public void onRewardedVideoClicked() {
    }

    @Override
    public void onRewardedVideoFinished(double amount, String name) {
    }

    @Override
    public void onRewardedVideoClosed(boolean finished) {
    }

    @Override
    public void onRewardedVideoExpired() {
    }

}
