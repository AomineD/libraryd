package com.dagf.dialogpackage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dagf.dialoglibrary.dialog.DialogHelper;
import com.dagf.dialoglibrary.dialog.DialogPackage;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String ii = "http://wineberryhalley.com/secure/mrapps/update_apps/";

        int id = 68;

        DialogHelper.showDialogVersion(this, ii, id);
    }
}
