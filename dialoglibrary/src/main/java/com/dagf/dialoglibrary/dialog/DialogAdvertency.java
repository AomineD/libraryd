package com.dagf.dialoglibrary.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.CheckBox;

import com.dagf.dialoglibrary.R;

public class DialogAdvertency extends AlertDialog implements View.OnClickListener {
    protected DialogAdvertency(Context context) {
        super(context);
    }

    public static final String key_show = "sjadjsadad";

    public static boolean shouldAppear(SharedPreferences preferences){
        return preferences.getInt(key_show, 0) == 0;
    }

    public DialogAdvertency(Activity ss, OkListenerAdvertency oklistener){
        super(ss);
this.aM = ss;
this.listener = oklistener;
    }

    private OkListenerAdvertency listener;
private Activity aM;
    private View btn;
    private WebView desct;
    private CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_advertency);

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        getWindow().setBackgroundDrawable(ActivityCompat.getDrawable(getContext(), R.color.transparent));


        checkBox = findViewById(R.id.checkok);
        desct = findViewById(R.id.text_version);
    String total = "<html><head></head><body style='text-align:justify;color:black;background-color:white;'>"+ getContext().getString(R.string.dialog_advertency_des) +"</body></html>";
        Log.e("MAIN", "onCreate: "+total);
    desct.loadData(total, "text/html; charset=utf-8", "utf-8");

btn = findViewById(R.id.btnversion);
btn.setOnClickListener(this);



    }


    @Override
    public void onClick(View v) {

        if(checkBox.isChecked()){
            aM.getPreferences(Context.MODE_PRIVATE).edit().putInt(key_show, 1).commit();
        }

        listener.Okbro();
        dismiss();

    }

    public interface OkListenerAdvertency{
        void Okbro();
    }
}
