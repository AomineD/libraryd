package com.dagf.dialoglibrary.dialog.adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.dagf.dialoglibrary.dialog.model.AppliModel;

import java.util.ArrayList;

public class VipagAdapter extends FragmentPagerAdapter {

    private Context mc;
    private ArrayList<AppliModel> apps = new ArrayList<>();

    public VipagAdapter(FragmentManager fragmentManager, Context m, ArrayList<AppliModel> appnews){
        super(fragmentManager);
        this.mc = m;
        this.apps = appnews;
    }

    @Override
    public int getCount() {
        return apps.size();
    }

    @Override
    public Fragment getItem(int i) {

        switch(i){
            case 0:
                ImageFragment imgf = new ImageFragment();

                imgf.urlimg = apps.get(i).urlImage;
                imgf.urlApp = apps.get(i).urlApp;

                return imgf;
            case 1:
                ImageFragment imgf2 = new ImageFragment();

                imgf2.urlimg = apps.get(i).urlImage;
                imgf2.urlApp = apps.get(i).urlApp;

                return imgf2;
            case 2:
                ImageFragment imgf3 = new ImageFragment();

                imgf3.urlimg = apps.get(i).urlImage;
                imgf3.urlApp = apps.get(i).urlApp;

                return imgf3;
            case 3:
                ImageFragment imgf4 = new ImageFragment();

                imgf4.urlimg = apps.get(i).urlImage;
                imgf4.urlApp = apps.get(i).urlApp;

                return imgf4;


            default: break;

        }
        return null;

    }



    public static void downloadApp(Context context, String packageName){
        if(!packageName.startsWith("http")) {
         //   Log.e("MAIN", "downloadApp1: "+packageName );
            try {
                context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + packageName)));
            } catch (ActivityNotFoundException var4) {
                context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)));
            }
        }else{
         //   Log.e("MAIN", "downloadApp2: "+packageName );
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(packageName)));

        }
    }


}
