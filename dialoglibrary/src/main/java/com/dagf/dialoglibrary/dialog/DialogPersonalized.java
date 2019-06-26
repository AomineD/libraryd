package com.dagf.dialoglibrary.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dagf.dialoglibrary.R;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import pl.droidsonroids.gif.GifImageView;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class DialogPersonalized extends AlertDialog {



    private DialogPackage.ListenerDialog listenerDialog;
    private Lister lister;

    public String tit;

    protected DialogPersonalized(@NonNull Context context) {
        super(context);
    }

    protected DialogPersonalized(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    private Activity activity;
    public DialogPersonalized(Activity c, DialogPackage.ListenerDialog listener, Lister ll){
        super(c);

        this.activity = c;
        this.lister = ll;
        this.listenerDialog = listener;

    }

    private Drawable gfDrawable;

    public DialogPersonalized(Activity c, DialogPackage.ListenerDialog listener, Lister ll, Drawable gifdr){
        super(c);

        this.lister = ll;
        this.listenerDialog = listener;
        this.gfDrawable = gifdr;

    }


    private TextView accept;
    private TextView no_acc;


    private TextView desc;
    private TextView title_d;

    public boolean isMaintaneance;


    public String mess = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogpersonalized);

        desc = findViewById(R.id.desc_d);

        String jj = getContext().getString(R.string.dialog_descr)+" " +tit+" "+getContext().getString(R.string.dialog_descr_2);

        desc.setText(jj);

        title_d = findViewById(R.id.title_d);

        if(isMaintaneance){
            if(mess == null) {
                desc.setText(getContext().getString(R.string.dialog_desc_maint));
            }else{
                desc.setText(mess);
            }
            title_d.setText(getContext().getString(R.string.dialog_maintaneance));
        }

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        getWindow().setBackgroundDrawable(ActivityCompat.getDrawable(getContext(), R.color.transparent));

        ImageView v = findViewById(R.id.iconimg);
        accept = findViewById(R.id.accept);
        if(v != null) {
            if (isMaintaneance) {
                accept.setText(getContext().getString(R.string.ok_maint));
                v.setImageDrawable(getContext().getResources().getDrawable(R.drawable.clocko));
            } else {
                v.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorPrimary), android.graphics.PorterDuff.Mode.MULTIPLY);
            }
        }

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


    }

   public interface Lister{
        void onClickToDownload();
   }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        Log.e("MAIN", "key "+keyCode);


        if(keyCode == 4){
            activity.finish();
        }

        return super.onKeyDown(keyCode, event);
    }
}
