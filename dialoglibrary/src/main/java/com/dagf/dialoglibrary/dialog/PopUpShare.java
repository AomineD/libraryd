package com.dagf.dialoglibrary.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.dagf.dialoglibrary.R;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;


public class PopUpShare extends AlertDialog {
    public PopUpShare(Context context) {
        super(context);
    }

    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }

    public void setTitle(String title, String description) {
        this.title = title;
    this.description = description;
    }

    public void setShare_btn(String share_btn) {
        this.share_btn = share_btn;
    }

    public void setCancel_tn(String cancel_tn) {
        this.cancel_tn = cancel_tn;
    }

    private String url_img;
    private String title, description;
    private String share_btn;
    private String cancel_tn;

    public void setShareTexT(String shareTexT) {
        this.shareTexT = shareTexT;
    }

    private String shareTexT;

    private ImageView img;
    private TextView title_text, share_text, cancel_text, desc_text;


    protected PopUpShare(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    protected PopUpShare(Context context, int themeResId) {
        super(context, themeResId);
    }


    private int session_frec;

    public void setFrecuency(int frecuency){
        this.session_frec = frecuency;
    }

    public static final String key_session = "session_kfew";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_up_share);

        SharedPreferences preferences = getContext().getSharedPreferences("pop_up", Context.MODE_PRIVATE);

        if(preferences.getInt(key_session, 0) < session_frec){
            int k = preferences.getInt(key_session, 0);
            k++;
            SharedPreferences.Editor editor = preferences.edit();

            editor.putInt(key_session, k).commit();

            dismiss();

        }else{
            SharedPreferences.Editor editor = preferences.edit();

            editor.putInt(key_session, 0).commit();
        }

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        getWindow().setBackgroundDrawable(ActivityCompat.getDrawable(getContext(), R.color.transparent));


        img = findViewById(R.id.img_big);
        title_text = findViewById(R.id.title_text_share);
        share_text = findViewById(R.id.share_btn);
        desc_text = findViewById(R.id.desc_text_share);

        cancel_text = findViewById(R.id.cancel_btn);

        Picasso.get().load(Uri.parse(url_img)).fit().placeholder(R.drawable.loading_ic).into(img);

        title_text.setText(title);
        share_text.setText(share_btn);
        cancel_text.setText(cancel_tn);
        desc_text.setText(description);


        cancel_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        share_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareTo();
            }
        });
    }

    private void shareTo(){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                shareTexT+" https://play.google.com/store/apps/details?id="+getContext().getPackageName());
        sendIntent.setType("text/plain");
        getContext().startActivity(Intent.createChooser(sendIntent, "Select one"));


    }
}
