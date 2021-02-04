package ru.academy.mytesttask;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appodeal.ads.NativeAd;
import com.appodeal.ads.native_ad.views.NativeAdViewNewsFeed;

import java.util.List;

public class NativeAdapter extends RecyclerView.Adapter{

    private List<RowType> dataSet;
    private List<NativeAd> nativeAds;

    public NativeAdapter(List<RowType> dataSet, List<NativeAd> nativeAds) {
        this.dataSet = dataSet;
        this.nativeAds = nativeAds;
    }

        @Override
    public int getItemViewType(int position) {
        if (dataSet.get(position) instanceof Item) {
            return RowType.ITEM_ROW_TYPE;
        } else if (dataSet.get(position) instanceof Native) {
            return RowType.NATIVE_ROW_TYPE;
        } else {
            return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == RowType.ITEM_ROW_TYPE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            TextViewHolder textViewHolder = new TextViewHolder(view);
            return textViewHolder;

        } else if (viewType == RowType.NATIVE_ROW_TYPE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.native_layout, parent, false);
            NativeViewHolder nativeViewHolder = new NativeViewHolder(view);
            return nativeViewHolder;
        } else return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if ((holder instanceof TextViewHolder)) {
            ((TextViewHolder)  holder).textView.setText(((Item)dataSet.get(position)).getText());
        }
        else
            {
            if ((holder instanceof NativeViewHolder)) {

                for (NativeAd i : nativeAds) {
                    ((NativeViewHolder) holder).linearLayout.addView(new NativeAdViewNewsFeed(((NativeViewHolder) holder).linearLayout.getContext(),
                            i,  ((MainActivity) ((NativeViewHolder) holder).linearLayout
                                    .getContext()).placementName));
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

  public  static class TextViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public TextViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.texttitle);
        }
    }

    public  static class NativeViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout linearLayout;

        public NativeViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.linear);
        }
    }
}
