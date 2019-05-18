package com.dagf.dialogpackage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dagf.dialoglibrary.dialog.DialogPackage;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String ii = "http://wineberryhalley.com/secure/mrapps/update_apps/";

        int id = 66;

        DialogPackage.setUrlServer(ii);

        DialogPackage.setIdApp(id);

        DialogPackage.show(this, new DialogPackage.ListenerDialog() {
            @Override
            public void OnOk() {
                finish();
            }

            @Override
            public void OnCancel() {
finish();
            }
        });
    }
}
