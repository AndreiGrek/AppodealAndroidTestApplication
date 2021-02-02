package ru.academy.mytesttask;

import android.app.Activity;

import android.app.Activity;

import com.appodeal.ads.RewardedVideoCallbacks;

import java.util.Locale;

class AppodealRewardedVideoCallbacks implements RewardedVideoCallbacks {

    private final Activity activity;

    AppodealRewardedVideoCallbacks(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onRewardedVideoLoaded(boolean isPrecache) {
    }

    @Override
    public void onRewardedVideoFailedToLoad() {
    }

    @Override
    public void onRewardedVideoShown() {
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
