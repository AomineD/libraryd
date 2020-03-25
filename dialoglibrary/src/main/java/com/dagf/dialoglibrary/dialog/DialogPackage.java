package com.dagf.dialoglibrary.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created on 26/11/2018.
 * @author Yulian Martinez
 * @version 1.0.1
 */
public class DialogPackage {

   // public static final String url = "http://besosdeamor.info/gif_d.gif"; // URL del gif
    public static boolean isShowing;
    private static int ID_APP = 23; // id de la app
    public static String URL_SERVER = "http://besosdeamor.info/update_package/"; // URL del servidor

    public static void setTest(boolean isTest) {
        DialogPackage.isTest = isTest;
    }

    private static boolean isTest;
    private static Drawable gif;
    private static ListenerDialog listenerDialogOrig;

    public static void setIdApp(int id){
        ID_APP = id;
    }

    public static void setUrlServer(String server){
        URL_SERVER = server;
    }

    public static void setGif(Drawable gifsetter){
        gif = gifsetter;
    }
    /**
     * Inicializa el proceso de verificacion del Dialog de actualizacion
     * @param context - en donde se ejecuta
     *
     *
     */

    private static String nmapp;
    public static void show(AppCompatActivity context, ListenerDialog listenerDialog, String nameapp){
        listenerDialogOrig = listenerDialog;
        nmapp = nameapp;
        sendGet(context);
    }



    /**
     * Se encarga de realizar la peticion y obtener la respuesta del servicio
     * para decidir si muestra o no el Dialog
     * @param context - en donde se ejecuta
     */
    private static void sendGet(final AppCompatActivity context){

        @SuppressLint("StaticFieldLeak")
        AsyncTask<String, Void, String> asyncTask = new AsyncTask<String, Void, String>() {

/** ================================= MENSAJE NUEVO 2 ============================================= **/


private void showDial(final AppCompatActivity context, final String verifyPackage, final int maint, String mens){

    AlertDialog.Builder builder;
    DialogPersonalized personalized;
    if(gif == null) {

        //builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
        personalized = new DialogPersonalized(context, listenerDialogOrig, new DialogPersonalized.Lister() {
            @Override
            public void onClickToDownload() {
                if(maint == 1)
                downloadApp(context, verifyPackage);
            }
        });
    }else{
        personalized = new DialogPersonalized(context, listenerDialogOrig, new DialogPersonalized.Lister() {
            @Override
            public void onClickToDownload() {
                if(maint == 1)
                downloadApp(context, verifyPackage);
            }
        }, gif);
    }

    if(maint == 0){
        personalized.isMaintaneance = true;
        personalized.mess = mens;
    }

    personalized.tit = nmapp;

    personalized.show();
    //  Log.e("MAIN", "onPostExecute: 2 = "+verifyPackage);
    isShowing = true;
}


            /** ====================================== MENSAJE NUEVO 1 ======================================= **/
            private void showDial(final AppCompatActivity context, final String verifyPackage, final int maint){

                AlertDialog.Builder builder;
                DialogPersonalized personalized;
                if(gif == null) {

                    //builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
                    personalized = new DialogPersonalized(context, listenerDialogOrig, new DialogPersonalized.Lister() {
                        @Override
                        public void onClickToDownload() {



                            if(maint == 1)
                            downloadApp(context, verifyPackage);
                        }
                    });
                }else{
                    personalized = new DialogPersonalized(context, listenerDialogOrig, new DialogPersonalized.Lister() {
                        @Override
                        public void onClickToDownload() {
                            if(maint == 1)
                            downloadApp(context, verifyPackage);
                        }
                    }, gif);
                }

                if(maint == 0){
                    personalized.isMaintaneance = true;
                }

                personalized.tit = nmapp;
                personalized.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        listenerDialogOrig.OnCancel();
                        Log.e("MAIN", "EXC");
                    }
                });

                if(isTest)
                    Log.e("MAIN", "onPostExecute: 2 = "+verifyPackage);
                personalized.show();

                isShowing = true;
            }


            @Override
            protected String doInBackground(String... params) {
                return getJSONString(params[0]);
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                try{

                    JSONObject jsonObject = new JSONObject(result);

                    if(!jsonObject.isNull("package_app")){
                        final String verifyPackage = jsonObject.getString("package_app");
                        final int maint = Integer.parseInt(jsonObject.getString("maint"));
                        final String mss = jsonObject.getString("message_perso");

                        urlToApp = jsonObject.getString("urlto");
                        final String version_app = jsonObject.getString("version_app");
                        String version = "";

                        try {
                            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                            version = pInfo.versionName;
                        } catch (PackageManager.NameNotFoundException e) {
                            e.printStackTrace();
                        if(isTest){
                            Log.e("MAIN", "getVersion DialogPackage "+e.getMessage() );
                        }
                        }

                        if(isTest)
                        Log.e("MAIN", "onPostExecute: "+verifyPackage + " ID APP "+ID_APP+" URL = "+URL_SERVER+"api.php?getPackage&id_app="+ID_APP);
                        if(!verifyPackage.equals(context.getPackageName())){
                            showDial(context, context.getPackageName(), maint);
//
                        }else if(maint == 0 && verifyPackage.equals(context.getPackageName())){
if(version_app.equals(" ") || version.equals(version_app))
                            showDial(context, context.getPackageName(), maint, mss);
                        }
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    Log.e("MAIN", "onPostExecute: "+e.getMessage() + " : "+e.getCause());
                }
            }
        };

String version = "";
        PackageInfo pInfo = null;
        try {
            pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            version = pInfo.versionName;
            asyncTask.execute(URL_SERVER+"api.php?getPackage&id_app="+ID_APP+"&version_app="+version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            asyncTask.execute(URL_SERVER+"api.php?getPackage&id_app="+ID_APP);
        }



    }

    /**
     * Encargado de realizar una peticion
     * @param url - url de la peticion
     * @return - respuesta del servicio
     */
    private static String getJSONString(String url) {
        String jsonString = null;
        HttpURLConnection urlConnection = null;
        try {
            URL linkurl = new URL(url);
            urlConnection = (HttpURLConnection) linkurl.openConnection();
            int code = urlConnection.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
             //   Log.e("MAIN", "getJSONString: CC");
                InputStream input = urlConnection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                int j;
                while ((j = input.read()) != -1) {
                    baos.write(j);
                }
                byte[] data = baos.toByteArray();
                jsonString = new String(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
      //  Log.e("MAIN", "getJSONString: "+jsonString);
        return jsonString;
    }

    private static boolean isGooglePlay = true;

    public static void noGooglePlay(){
        isGooglePlay = false;
    }

    private static String urlToApp = "nothing";

    public static String getUrlToApp(){
        return urlToApp;
    }
    private static void downloadApp(Context context, String packageName){
        if(isGooglePlay) {
            try {
                context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + packageName)));
            } catch (ActivityNotFoundException var4) {
                context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)));
            }
        }else{
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(urlToApp)));

        }
    }

    public interface ListenerDialog{
        void OnOk(boolean maintaneance);

        void OnCancel();
    }



}
