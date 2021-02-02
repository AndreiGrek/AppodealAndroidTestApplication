package ru.academy.mytesttask;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.InterstitialCallbacks;
import com.appodeal.ads.NativeAd;
import com.appodeal.ads.NativeCallbacks;
import com.appodeal.ads.RewardedVideoCallbacks;
import com.appodeal.ads.native_ad.views.NativeAdViewContentStream;
import com.appodeal.ads.native_ad.views.NativeAdViewNewsFeed;
import com.appodeal.ads.utils.PermissionsHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private int counterBanner;
    private int counterVideo;
    private String APP_KEY;
    private List<NativeAd> nativeAds = new ArrayList<>();
    private String placementName = "default";
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        APP_KEY = "121a60c45245d740876d1849cdc6c877f80b58168086e9a4";
        counterBanner = 1;
        counterVideo = 1;

        findViewById(R.id.banners_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Appodeal.initialize(MainActivity.this, APP_KEY, Appodeal.BANNER_TOP);
                if (counterBanner < 6) {
                    Appodeal.hide(MainActivity.this, Appodeal.BANNER_TOP);
                    Appodeal.show(MainActivity.this, Appodeal.BANNER_TOP);
                    Toast.makeText(MainActivity.this, "Show " + counterBanner + " Banner", Toast.LENGTH_SHORT).show();
                    counterBanner++;
                } else Appodeal.hide(MainActivity.this, Appodeal.BANNER_TOP);
            }
        });

        findViewById(R.id.interstitials_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Appodeal.initialize(MainActivity.this, APP_KEY, Appodeal.INTERSTITIAL | Appodeal.BANNER_TOP);
                Appodeal.setInterstitialCallbacks(new AppodealInterstitialCallbacks(MainActivity.this));
                boolean isShown = Appodeal.show(MainActivity.this, Appodeal.INTERSTITIAL);
                Toast.makeText(MainActivity.this, String.valueOf(isShown), Toast.LENGTH_SHORT).show();
                Appodeal.hide(MainActivity.this, Appodeal.BANNER_TOP);
                v.setEnabled(false);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        v.setEnabled(true);
                    }
                }, 15000);
            }
        });

        findViewById(R.id.rewarded_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(counterVideo <4) {
                    Appodeal.initialize(MainActivity.this, APP_KEY, Appodeal.REWARDED_VIDEO | Appodeal.BANNER_TOP);
                    Appodeal.setRewardedVideoCallbacks(new AppodealRewardedVideoCallbacks(MainActivity.this));
                    boolean isShown = Appodeal.show(MainActivity.this, Appodeal.REWARDED_VIDEO);
                    Toast.makeText(MainActivity.this, String.valueOf(counterVideo), Toast.LENGTH_SHORT).show();
                    Appodeal.hide(MainActivity.this, Appodeal.BANNER_TOP);
                    counterVideo++;
                } else {
                    v.setEnabled(false);
                }
            }
        });

        findViewById(R.id.native_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Appodeal.setNativeCallbacks(new AppodealNativeCallbacks(MainActivity.this));
                Appodeal.initialize(MainActivity.this, APP_KEY, Appodeal.NATIVE | Appodeal.BANNER_TOP);
                Appodeal.hide(MainActivity.this, Appodeal.BANNER_TOP);
                Appodeal.cache(MainActivity.this, Appodeal.NATIVE, 1);
                nativeAds = Appodeal.getNativeAds(3);
                LinearLayout nativeAdsListView = findViewById(R.id.nativeAdsListView);
                nativeAdsListView.setTag(nativeAdsListView);

                for (NativeAd nativeAd : nativeAds) {
                    nativeAdsListView.addView(new NativeAdViewNewsFeed(nativeAdsListView.getContext(), nativeAd,
                            ((MainActivity) nativeAdsListView
                            .getContext()).placementName));
                }
            }
        });
    }
}