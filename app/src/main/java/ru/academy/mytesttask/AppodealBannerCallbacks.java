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


    @Override
    public void onBannerLoaded(int i, boolean b) {

    }

    @Override
    public void onBannerFailedToLoad() {

    }

    @Override
    public void onBannerShown() {

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

}

