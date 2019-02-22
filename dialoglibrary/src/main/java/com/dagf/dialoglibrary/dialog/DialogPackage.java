package com.dagf.dialoglibrary.dialog;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

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
    private static String URL_SERVER = "http://besosdeamor.info/update_package/"; // URL del servidor

    private static Drawable gif;
    private static ListenerDialog listenerDialogOrig;

    public static void SetIdApp(int id){
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
     */
    public static void show(Context context, ListenerDialog listenerDialog){
        listenerDialogOrig = listenerDialog;
        sendGet(context);
    }



    /**
     * Se encarga de realizar la peticion y obtener la respuesta del servicio
     * para decidir si muestra o no el Dialog
     * @param context - en donde se ejecuta
     */
    private static void sendGet(final Context context){

        @SuppressLint("StaticFieldLeak")
        AsyncTask<String, Void, String> asyncTask = new AsyncTask<String, Void, String>() {

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
                      //  Log.e("MAIN", "onPostExecute: "+verifyPackage);
                        if(!verifyPackage.equals(context.getPackageName())){

//                            AlertDialog.Builder builder;
                            DialogPersonalized personalized;
                           if(gif == null) {
                               //builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
                               personalized = new DialogPersonalized(context, listenerDialogOrig, new DialogPersonalized.Lister() {
                                   @Override
                                   public void onClickToDownload() {
                                       downloadApp(context, context.getPackageName());
                                   }
                               });
                           }else{
                               personalized = new DialogPersonalized(context, listenerDialogOrig, new DialogPersonalized.Lister() {
                                   @Override
                                   public void onClickToDownload() {
                                       downloadApp(context, context.getPackageName());
                                   }
                               }, gif);
                           }
                            personalized.show();
                          //  Log.e("MAIN", "onPostExecute: 2 = "+verifyPackage);
                            isShowing = true;
                        }
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    Log.e("MAIN", "onPostExecute: "+e.getMessage());
                }
            }
        };
        asyncTask.execute(URL_SERVER+"api.php?getPackage&id_app="+ID_APP);
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

    private static void downloadApp(Context context, String packageName){
        try {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id="+packageName)));
        } catch (ActivityNotFoundException var4) {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id="+packageName)));
        }
    }

    public interface ListenerDialog{
        void OnOk();

        void OnCancel();
    }
}
