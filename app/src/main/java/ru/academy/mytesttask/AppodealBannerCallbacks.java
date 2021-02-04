package ru.academy.mytesttask;


import android.app.Activity;
import android.widget.Toast;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.BannerCallbacks;

class AppodealBannerCallbacks implements BannerCallbacks {

    private final Activity activity;

    AppodealBannerCallbacks(Activity activity) {
        this.activity = activity;
    }

    private int showCounter = 0;

    @Override
    public void onBannerLoaded(int i, boolean b) {

    }

    @Override
    public void onBannerFailedToLoad() {

    }

    @Override
    public void onBannerShown() {
        if (showCounter > 4) {
            Appodeal.hide(activity, Appodeal.BANNER_TOP);
        } else {
            showCounter++;
            Toast.makeText(activity, Integer.toString(showCounter), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBannerShowFailed() {

    }

    @Override
    public void onBannerClicked() {

    }

    @Override
    public void onBannerExpired() {

    }

    public int getShowCounter() {
        return showCounter;
    }
}

