package com.dagf.dialogpacka;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.dagf.dialoglibrary.dialog.DialogAdvertency;
import com.dagf.dialoglibrary.dialog.DialogHelper;
import com.dagf.dialoglibrary.dialog.DialogPackage;
import com.dagf.dialoglibrary.dialog.LoadingDialog;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String ii = "https://moviesapp.website/secure/cpanels/update_apps/";

        int id = 69;


       DialogPackage.setUrlServer(ii);

        DialogPackage.setTest(true);

        DialogPackage.setIdApp(id);

        DialogPackage.show(this, new DialogPackage.ListenerDialog() {
            @Override
            public void OnOk(boolean maint) {

            }

            @Override
            public void OnCancel() {

            }
        }, "APP jaja");
       // DialogHelper.showDialogVersion(this, ii, id);

       /* DialogHelper.showAdvertencyLinks(this, new DialogAdvertency.OkListenerAdvertency() {
            @Override
            public void Okbro() {
                Toast.makeText(MainActivity.this, "CLICKED", Toast.LENGTH_SHORT).show();
            }
        });

       /* final LoadingDialog loadingDialog = new LoadingDialog(this);
        loadingDialog.show();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
runOnUiThread(new Runnable() {
    @Override
    public void run() {
        loadingDialog.loadListener().onCompleteLoad(true);
    }
});

            }
        }, 8000);*/



    }
}
