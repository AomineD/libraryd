package com.dagf.dialoglibrary.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;

import com.dagf.dialoglibrary.R;

public class DialogVersion extends AlertDialog implements View.OnClickListener {
    protected DialogVersion(Context context) {
        super(context);
    }

    public DialogVersion(Context c, String appnm, String packageold){
        super(c);
this.nameapp = appnm;
this.packold = packageold;
    }

private String packold;
    private String nameapp;
    private View btn;
    private WebView desct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialogversion);

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        getWindow().setBackgroundDrawable(ActivityCompat.getDrawable(getContext(), R.color.transparent));


        desct = findViewById(R.id.text_version);
    String total = "<html><head></head><body style='text-align:justify;color:black;background-color:white;'>"+ getContext().getString(R.string.dialog_ver_desc) + " "+nameapp+getContext().getString(R.string.dialog_ver_desc2)+"</body></html>";
        Log.e("MAIN", "onCreate: "+total);
    desct.loadData(total, "text/html; charset=utf-8", "utf-8");

btn = findViewById(R.id.btnversion);
btn.setOnClickListener(this);



    }


    @Override
    public void onClick(View v) {

        Intent pack = getContext().getPackageManager().getLaunchIntentForPackage(packold);

        if(pack != null){
            Intent intent = new Intent(Intent.ACTION_DELETE);
            intent.setData(Uri.parse("package:"+packold));
            getContext().startActivity(intent);
        }

    }
}
