package com.dagf.dialoglibrary.dialog.anti;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;

import com.dagf.dialoglibrary.R;
import com.wineberryhalley.bclassapp.BottomBaseShet;
import com.wineberryhalley.bclassapp.baseapi.BaseApi;
import com.wineberryhalley.bclassapp.baseapi.Interfaces;
import com.wineberryhalley.bclassapp.baseapi.ObjectType;
import com.wineberryhalley.bclassapp.baseapi.RequestType;

public class BottomAnti extends BottomBaseShet {
    @Override
    public int layoutID() {
        return R.layout.bottom_anti;
    }

    @Override
    public boolean Modal() {
        return false;
    }

    private AppCompatActivity activity;
  public BottomAnti(AppCompatActivity activity){
        this.activity = activity;
  }



    public interface LoadListener{
        void OnLoad(boolean checked, boolean captcha);
        void OnFail(String erno);
    }

    private String url;
    public void setUrl(String url){
        this.url = url;
    }
    private boolean needToShowAutomatic = true;

    public void noShowAutomatic(){
        needToShowAutomatic = false;
    }

    private String TAG ="MAIN";
    private AppBn appBn;
    public void load(LoadListener loadListener){

            BaseApi<AppBn> bnBaseApi = new BaseApi.ApiBuilder<AppBn>(url, "api_rest.php?banned=" + activity.getPackageName())
                    .requestType(RequestType.GET)
                    .setListener(new Interfaces.SingleListener<AppBn>(){
                        @Override
                        public void OnLoadSuccess(AppBn models) {
                            super.OnLoadSuccess(models);

                                try {
                                    loadListener.OnLoad(models.isBanned(activity), models.getType_redi().equals("0"));

                                    appBn = models;
                                //    Log.e(TAG, "OnLoadSuccess: "+(models.isBanned(activity) && needToShowAutomatic && !models.getType_redi().equalsIgnoreCase("0")) );
                                if (models.isBanned(activity) && needToShowAutomatic && !models.getType_redi().equalsIgnoreCase("0")) {
                                    showNow2(activity.getSupportFragmentManager());
                                }

                                } catch (PackageManager.NameNotFoundException e) {
                                    loadListener.OnFail(e.getMessage());
                                    e.printStackTrace();
                                }
                            }


                        @Override
                        public void OnError(String erno) {
                            super.OnError(erno);
                            loadListener.OnFail(erno);
                        }
                    })
                    .build(ObjectType.JsonOBJECT, AppBn.class);

            bnBaseApi.executeUrl();


    }



    public void showNow(FragmentManager fragmentManager){
        if(!needToShowAutomatic)
        show(fragmentManager, "updtaq");
    }

    private void showNow2(FragmentManager fragmentManager){

            show(fragmentManager, "updtaq");
    }

    private TextView desc;
    private CardView install;
    @Override
    public void OnStart() {
install = find(R.id.install);
desc = find(R.id.desc_);

install.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(appBn.getUrl_to())));
        activity.finish();
    }
});
String d = String.format(getString(R.string.new_version_desc), appBn.getName());

desc.setText(d);
    }
}
