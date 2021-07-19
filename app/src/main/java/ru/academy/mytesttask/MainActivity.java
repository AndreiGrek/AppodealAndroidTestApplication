 package ru.academy.mytesttask;

 import android.os.Bundle;
 import android.view.View;

 import androidx.appcompat.app.AppCompatActivity;
 import androidx.recyclerview.widget.LinearLayoutManager;
 import androidx.recyclerview.widget.RecyclerView;

 import com.appodeal.ads.Appodeal;
 import com.appodeal.ads.NativeAd;
 import com.explorestack.consent.ConsentForm;

 import java.util.ArrayList;
 import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String APP_KEY;
    private List<NativeAd> nativeAds;
    public String placementName = "default";
    private NativeAdapter nativeAdapter;
    List<RowType> dataSet = new ArrayList<>();
    private ConsentForm consentForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        APP_KEY = "121a60c45245d740876d1849cdc6c877f80b58168086e9a4";

        findViewById(R.id.init_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Appodeal.initialize(MainActivity.this, APP_KEY, Appodeal.BANNER
                        | Appodeal.INTERSTITIAL | Appodeal.REWARDED_VIDEO | Appodeal.NATIVE, true);
            }
        });


        findViewById(R.id.init_banner).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Appodeal.initialize(MainActivity.this, APP_KEY, Appodeal.BANNER, true);
            }
        });

        findViewById(R.id.init_inter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Appodeal.initialize(MainActivity.this, APP_KEY, Appodeal.INTERSTITIAL, true);
            }
        });

        findViewById(R.id.banners_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Appodeal.show(MainActivity.this, Appodeal.BANNER_BOTTOM);
            }
        });

        findViewById(R.id.interstitials_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Appodeal.show(MainActivity.this, Appodeal.INTERSTITIAL);
            }
        });


        findViewById(R.id.rewarded_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   Appodeal.show(MainActivity.this, Appodeal.REWARDED_VIDEO);
            }
        });

        findViewById(R.id.native_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    }
}