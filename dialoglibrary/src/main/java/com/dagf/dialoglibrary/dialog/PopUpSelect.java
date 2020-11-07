package com.dagf.dialoglibrary.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dagf.dialoglibrary.R;
import com.dagf.presentlogolib.nextview.NextViewItem;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

import java.util.ArrayList;

/**
 * @author Diego Garcia
 */
public class PopUpSelect extends AlertDialog {


    private Context mContext;
    private InterstitialAd facebook;
    private Uri urr;
    private String namee;
    private ArrayList<PlayerObject> playerObjects = new ArrayList<>();
    private View Linss;
 //   private View backwh;

    private boolean Already;
    private RelativeLayout rr;


    public static boolean urlExtern;
    public static long item_id;
    private boolean ok_bol;
    private DiscreteScrollView scrollView;

    protected PopUpSelect(@NonNull Context context) {
        super(context);

    }


    public void setPlayerObjects(ArrayList<PlayerObject> objects){
        this.playerObjects = objects;
    }

    public PopUpSelect(Context context, String url, String nam, InterstitialAd ad) {
        super(context);
        this.mContext = context;
        this.urr = Uri.parse(url);
        this.namee = nam;
        this.facebook = ad;




    }
    public PopUpSelect(Context context, String url, String nam) {
        super(context);
        this.mContext = context;
        this.urr = Uri.parse(url);
        this.namee = nam;




    }

    private ArrayList<Integer> drawablesInt = new ArrayList<>();
    private ArrayList<String> packages = new ArrayList<>();
    private String[] apiskeys;

    public void setKeys(String[] ke){
        this.apiskeys = ke;
    }
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

        if(!urlExtern) {
            playerAdapter adapter = new playerAdapter(mContext);

            adapter.setDrawables(drawablesInt);

            scrollView.setAdapter(adapter);

        }else if(playerObjects != null){
            playerAdapter adapter = new playerAdapter(mContext);

            adapter.setPlayers(playerObjects);

            scrollView.setAdapter(adapter);
        }
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

                   if(facebook != null && facebook.isAdLoaded()){

                        facebook.setAdListener(new InterstitialAdListener() {
                            @Override
                            public void onInterstitialDisplayed(Ad ad) {

                            }

                            @Override
                            public void onInterstitialDismissed(Ad ad) {
                                OpenWithFLIX(scrollView.getCurrentItem());


                                dismiss();

                                String id = facebook.getPlacementId();

                                facebook = new com.facebook.ads.InterstitialAd(getContext(), id);

                                facebook.loadAd();
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


        private ArrayList<NextViewItem> itemsParcealables = new ArrayList<>();

    public void setItemsParcealables(ArrayList<NextViewItem> ui){
        this.itemsParcealables = ui;
    }

    private void OpenWithFLIX(int pos) {
        if(!urlExtern) {
            if(packages.size() > 0) {
                String packageName = packages.get(pos);
                try {

                    Intent mx = new Intent(Intent.ACTION_VIEW);
                    mx.setPackage(packageName);
                    mx.setDataAndType(urr, "video/*");
                    mx.putExtra("title", namee);
                    mx.putExtra("is_new", true);
                    if (apiskeys != null && apiskeys.length > 0)
                        mx.putExtra("key_apis", apiskeys);

                    if (itemsParcealables.size() > 0)
                        mx.putParcelableArrayListExtra("next_items", itemsParcealables);

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
        }else if(playerObjects.size() > 0){
            String packageName = playerObjects.get(pos).packageName;
            try {
                Intent mx = new Intent(Intent.ACTION_VIEW);
                mx.setPackage(packageName);
                mx.setDataAndType(urr, "video/*");
                mx.putExtra("title", namee);
                mx.putExtra("is_new", true);
                if (apiskeys != null && apiskeys.length > 0)
                    mx.putExtra("key_apis", apiskeys);

                if (itemsParcealables.size() > 0)
                    mx.putParcelableArrayListExtra("next_items", itemsParcealables);

                // mx.putExtra("from_start", false);

                mContext.startActivity(mx);

            } catch (Exception e) {
                    mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(playerObjects.get(pos).url_to)));


            }
        }
    }

// ==================================================================== Editar lista user =============================================================== //
}
