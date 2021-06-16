 package ru.academy.mytesttask;

 import android.os.Bundle;
 import android.os.Handler;
 import android.view.View;
 import android.widget.Toast;

 import androidx.appcompat.app.AppCompatActivity;
 import androidx.fragment.app.FragmentManager;
 import androidx.fragment.app.FragmentTransaction;
 import androidx.recyclerview.widget.LinearLayoutManager;
 import androidx.recyclerview.widget.RecyclerView;

 import com.appodeal.ads.Appodeal;
 import com.appodeal.ads.NativeAd;
 import com.explorestack.consent.Consent;
 import com.explorestack.consent.ConsentForm;
 import com.explorestack.consent.ConsentFormListener;
 import com.explorestack.consent.ConsentInfoUpdateListener;
 import com.explorestack.consent.ConsentManager;
 import com.explorestack.consent.exception.ConsentManagerException;

 import java.util.ArrayList;
 import java.util.List;

public class MainActivity extends AppCompatActivity {
    private int counterVideo;
    private String APP_KEY;
    private List<NativeAd> nativeAds;
    public String placementName = "default";
    private Handler handler = new Handler();
    private NativeAdapter nativeAdapter;
    List<RowType> dataSet = new ArrayList<>();
    private ConsentForm consentForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppodealBannerCallbacks appodealBannerCallbacks = new AppodealBannerCallbacks(this);
        AppodealInterstitialCallbacks appodealInterstitialCallbacks = new AppodealInterstitialCallbacks(this);
        AppodealRewardedVideoCallbacks appodealRewardedVideoCallbacks = new AppodealRewardedVideoCallbacks(this);
        AppodealNativeCallbacks appodealNativeCallbacks = new AppodealNativeCallbacks(this);

        APP_KEY = "121a60c45245d740876d1849cdc6c877f80b58168086e9a4";
        counterVideo = 1;




        ConsentManager.getInstance(this).requestConsentInfoUpdate(
                APP_KEY,
                new ConsentInfoUpdateListener() {
                    @Override
                    public void onConsentInfoUpdated(Consent consent) {
                    }

                    @Override
                    public void onFailedToUpdateConsentInfo(ConsentManagerException exception) {
                    }
                });

//        ConsentManager consentManager = ConsentManager.getInstance(this);
//        Consent consent = consentManager.getConsent();
//
//
//        consentForm = new ConsentForm.Builder(this)
//                .withListener(new ConsentFormListener() {
//                    @Override
//                    public void onConsentFormLoaded() {
//                        // Consent form was loaded. Now you can display consent form as activity or as dialog
//                        consentForm.showAsActivity();
//                    }
//
//                    @Override
//                    public void onConsentFormError(ConsentManagerException error) {
//                        // Consent form loading or showing failed. More info can be found in 'error' object
//                    }
//
//                    @Override
//                    public void onConsentFormOpened() {
//                        // Conset form was shown
//                    }
//
//                    @Override
//                    public void onConsentFormClosed(Consent consent) {
//                        // Consent form was closed
//                    }
//                })
//                .build();
//
//        consentForm.load();

        Appodeal.setBannerViewId(R.id.appodealBannerView);
//        Appodeal.setTesting(true);


        Appodeal.initialize(MainActivity.this, APP_KEY, Appodeal.BANNER_VIEW
                | Appodeal.INTERSTITIAL | Appodeal.REWARDED_VIDEO | Appodeal.NATIVE, true);



        findViewById(R.id.banners_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Appodeal.setBannerCallbacks(appodealBannerCallbacks);
                if (appodealBannerCallbacks.getShowCounter() < 5) {
//                    Appodeal.hide(MainActivity.this, Appodeal.BANNER_TOP);
//                    Appodeal.show(MainActivity.this, Appodeal.BANNER_TOP);
                    Appodeal.show(MainActivity.this, Appodeal.BANNER_VIEW);
                } else Appodeal.hide(MainActivity.this, Appodeal.BANNER_TOP);
            }
        });

        findViewById(R.id.interstitials_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Appodeal.setInterstitialCallbacks(appodealInterstitialCallbacks);
                boolean isShown = Appodeal.show(MainActivity.this, Appodeal.INTERSTITIAL);
                Toast.makeText(MainActivity.this, String.valueOf(isShown), Toast.LENGTH_SHORT).show();
                Appodeal.hide(MainActivity.this, Appodeal.BANNER_TOP);
                v.setEnabled(false);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        v.setEnabled(true);
                    }
                }, 6000);
            }
        });

        findViewById(R.id.rewarded_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counterVideo < 4) {
                    Appodeal.setRewardedVideoCallbacks(appodealRewardedVideoCallbacks);
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

                Appodeal.setNativeCallbacks(appodealNativeCallbacks);
                Appodeal.hide(MainActivity.this, Appodeal.BANNER_TOP);
                Appodeal.cache(MainActivity.this, Appodeal.NATIVE, 4);
                nativeAds = Appodeal.getNativeAds(4);

                for (int i=1; i<100; i++){
                    if (i%7==0) {
                        dataSet.add(new Native());
                    } else {
                        dataSet.add(new Item("Рандомный текст"));
                    }
                }
                RecyclerView recyclerView = findViewById(R.id.recycler);
                nativeAdapter = new NativeAdapter(dataSet, nativeAds);
                recyclerView.setAdapter(nativeAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));
            }
        });

//        findViewById(R.id.fragment_btn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                BlankFragment blankFragment = new BlankFragment();
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.add(R.id.container, blankFragment).commit();
//            }
//        });
    }
}