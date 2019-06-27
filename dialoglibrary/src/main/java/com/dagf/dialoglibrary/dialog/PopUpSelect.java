package com.dagf.dialoglibrary.dialog;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dagf.dialoglibrary.R;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

import java.util.ArrayList;

/**
 * @author Diego Garcia
 */
public class PopUpSelect extends AlertDialog {


    private Context mContext;
    private InterstitialAd interstitialAd;
    private com.facebook.ads.InterstitialAd facebook;
    private Uri urr;
    private String namee;
    private View Linss;
 //   private View backwh;

    private boolean Already;
    private RelativeLayout rr;


    public static boolean edit;
    public static long item_id;
    private boolean ok_bol;
    private DiscreteScrollView scrollView;

    protected PopUpSelect(@NonNull Context context) {
        super(context);

    }

    public PopUpSelect(Context context, String url, String nam, InterstitialAd ad) {
        super(context);
        this.mContext = context;
        this.urr = Uri.parse(url);
        this.namee = nam;
        this.interstitialAd = ad;




    }

    public PopUpSelect(Context context, String url, String nam, com.facebook.ads.InterstitialAd ad) {
        super(context);
        this.mContext = context;
        this.urr = Uri.parse(url);
        this.namee = nam;
        this.facebook = ad;




    }

    private ArrayList<Integer> drawablesInt = new ArrayList<>();
    private ArrayList<String> packages = new ArrayList<>();

    public void setDrawables(ArrayList<Integer> dr){
        this.drawablesInt = dr;
    }

    public void setPackages(ArrayList<String> strings){
        this.packages = strings;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_pop_up_layout);

        //animationView.setScale(1f);
        rr = findViewById(R.id.kk);
    //    backwh = findViewById(R.id.white_backg);
        Linss = findViewById(R.id.lin_ss);
        scrollView = findViewById(R.id.reprod);

        playerAdapter adapter = new playerAdapter(mContext, drawablesInt);

        scrollView.setAdapter(adapter);

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        getWindow().setBackgroundDrawable(ActivityCompat.getDrawable(getContext(), R.color.tran));

        TextView button_cancel = findViewById(R.id.cancel_button);

        TextView button_save = findViewById(R.id.go_player);


        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Play();
            }
        });


    }

    private void Play() {
        if (!Already) {


         //   backwh.setVisibility(View.VISIBLE);
            Linss.setVisibility(View.GONE);
            rr.setVisibility(View.GONE);
            scrollView.setVisibility(View.GONE);

                    if (interstitialAd != null && interstitialAd.isLoaded()) {
                        interstitialAd.setAdListener(new AdListener() {



                            @Override
                            public void onAdClosed() {
                                OpenWithFLIX(scrollView.getCurrentItem());


                                dismiss();


                                interstitialAd.loadAd(new AdRequest.Builder().build());
                            }
                        });


                        interstitialAd.show();
                    }// FACEBOOK AUDIENCE
                    else if(facebook != null && facebook.isAdLoaded()){

                        facebook.setAdListener(new InterstitialAdListener() {
                            @Override
                            public void onInterstitialDisplayed(Ad ad) {

                            }

                            @Override
                            public void onInterstitialDismissed(Ad ad) {
                                OpenWithFLIX(scrollView.getCurrentItem());


                                dismiss();


                                //facebook.loadAd();
                            }

                            @Override
                            public void onError(Ad ad, AdError adError) {

                            }

                            @Override
                            public void onAdLoaded(Ad ad) {

                            }

                            @Override
                            public void onAdClicked(Ad ad) {

                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {

                            }
                        });

                        facebook.show();

                    }
                    else {
                        OpenWithFLIX(scrollView.getCurrentItem());
                        dismiss();
                    }


                }
            //  Log.e("MAIN", "Play: CONCRETE");
            Already = true;


        }


    private void OpenWithFLIX(int pos) {
        String packageName = packages.get(pos);
        try {
            Intent mx = new Intent(Intent.ACTION_VIEW);
            mx.setPackage(packageName);
            mx.setDataAndType(urr, "video/*");
            mx.putExtra("title", namee);
            // mx.putExtra("from_start", false);

            mContext.startActivity(mx);

        } catch (Exception e) {
            Log.e("MAIN", "OpenWithMX: " + e.getMessage());

            try {
                mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName)));
            } catch (android.content.ActivityNotFoundException ee) {
                mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)));
            }

        }
    }

// ==================================================================== Editar lista user =============================================================== //
}
