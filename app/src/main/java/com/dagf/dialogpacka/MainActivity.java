package com.dagf.dialogpacka;

import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.bugsnag.android.Bugsnag;
import com.dagf.dialoglibrary.dialog.DialogAdvertency;
import com.dagf.dialoglibrary.dialog.DialogHelper;
import com.dagf.dialoglibrary.dialog.DialogPackage;
import com.dagf.dialoglibrary.dialog.LoadingDialog;
import com.dagf.dialoglibrary.dialog.PopUpShare;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bugsnag.notify(new RuntimeException("Test error"));
        PopUpShare popUpShare = new PopUpShare(this);


        popUpShare.setTitle("Regala esta app a tus amigos", "Hoy es un gran día para hacer un regalo especial a un amigo");
        popUpShare.setCancel_tn("Luego");
popUpShare.setShare_btn("Regalar");
popUpShare.setUrl_img("https://wallpaperplay.com/walls/full/2/5/d/129759.jpg");
popUpShare.setShareTexT("Prueba ahora esta app gratis");

if(PopUpShare.shouldShow(this, 2))
popUpShare.show();
    }
}
