package com.dagf.dialoglibrary.dialog;

import android.animation.Animator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.dagf.dialoglibrary.R;

public class LoadingDialog extends AlertDialog {

    public interface LoadingComplete{
        void onCompleteLoad(boolean positive);
    }

    public LoadingDialog(Activity context) {
        super(context);
        this.ac = context;
    }

    protected LoadingDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    protected LoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    Activity ac;


    private LottieAnimationView animationView;
    private TextView t;
    private TextView desc;

    private String tt = "title";
    private String desc_text = "desc";
    private String tt_error = "Error";

    private String desc_errno = "Algo salió muy mal";
    private String title_success = "Éxito";
    private String desc_success = "¡Cargo correctamente!";

    public void setTexts(String tilte, String descrip, String title_errno, String errno){
        this.tt = tilte;
        this.desc_text = descrip;
        this.tt_error = title_errno;
        this.desc_errno = errno;
    }

    public void setTextSucc(String titleS, String descS){
        this.title_success = titleS;
        this.desc_success = descS;
    }

    private String rawRes = "loading_def.json";
    private String rawResSuc = "success_def.json";
    private String rawResErno = "errno_def.json";

    private LoadingComplete listener;

    public LoadingComplete loadListener(){
        return listener;
    }

    public void setRaw(String whatrew, String succ, String erno){
        this.rawRes = whatrew;
        this.rawResSuc = succ;
        this.rawResErno = erno;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.loading_dialog);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        getWindow().setBackgroundDrawable(ActivityCompat.getDrawable(getContext(), R.color.transparent));


        t = findViewById(R.id.title_loading);
        desc = findViewById(R.id.title_desc_loading);


        if(!desc_text.equals("desc")){
            desc.setText(desc_text);
        }

        if(!tt.equals("title")){
            t.setText(tt);
        }

        animationView = findViewById(R.id.loading_anim);
if(!rawRes.equals("loading_def.json")){
   animationView.setAnimation(rawRes);
   animationView.playAnimation();
}


final View bnt = findViewById(R.id.kk);

        listener = new LoadingComplete() {

            @Override
            public void onCompleteLoad(boolean positive) {
                animationView.loop(false);

                if(positive){
                    animationView.setAnimation(rawResSuc);

                    animationView.playAnimation();
                    desc.setText(desc_success);
                    t.setText(title_success);
                }else{
                    animationView.setAnimation(rawResErno);

                    desc.setText(desc_errno);
                    t.setText(tt_error);
                    animationView.playAnimation();
                }

                bnt.setVisibility(View.VISIBLE);
                bnt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                });

            }
        };


    }


}
