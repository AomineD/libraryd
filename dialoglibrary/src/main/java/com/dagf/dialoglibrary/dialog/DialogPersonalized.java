package com.dagf.dialoglibrary.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.morocco.arabic.music.R;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import pl.droidsonroids.gif.GifImageView;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class DialogPersonalized extends AlertDialog {



    private DialogPackage.ListenerDialog listenerDialog;
    private Lister lister;


    protected DialogPersonalized(@NonNull Context context) {
        super(context);
    }

    protected DialogPersonalized(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public DialogPersonalized(Context c, DialogPackage.ListenerDialog listener, Lister ll){
        super(c);

        this.lister = ll;
        this.listenerDialog = listener;

    }


    private GifImageView gif;
    private TextView accept;
    private TextView no_acc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogpersonalized);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        getWindow().setBackgroundDrawable(ActivityCompat.getDrawable(getContext(), R.color.transparent));

        gif = findViewById(R.id.gif_img);

accept = findViewById(R.id.accept);
no_acc = findViewById(R.id.cancel);

accept.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        dismiss();
        listenerDialog.OnOk();
        lister.onClickToDownload();

    }
});

no_acc.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        dismiss();
        listenerDialog.OnCancel();
    }
});


        //Ion.with(getContext()).load(url).withBitmap().animateGif(AnimateGifMode.ANIMATE).intoImageView(gif);
        Glide.with(getContext()).asGif().load(R.drawable.gif_d).apply(bitmapTransform(new RoundedCornersTransformation(20, 0))).into(gif);
    }

   public interface Lister{
        void onClickToDownload();
   }


}
