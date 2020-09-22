package com.dagf.dialoglibrary.dialog;

import android.content.Context;
import android.text.Html;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class UtilsDialog {

    public static void reviewVersionInPlay(Context context, String url, VersionListener listener){
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
String[] splits = response.split("Current Version");
String[] splits2 = stripHtml(splits[1]).split("\n");
if(splits2.length > 0){
    listener.onLoadCool(splits2[0]);
}else
    listener.onFail("no size");
       //         Log.e("MAIN", "onResponse: has spaces "+splits2[0].contains("\n") );
          //      Log.e("MAIN", "onResponse: "+splits2[0] );
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
listener.onFail(error.getMessage());
            }
        });

        queue.add(request);

    }

    private static String stripHtml(String html) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY).toString();
        } else {
            return Html.fromHtml(html).toString();
        }
    }

    public interface VersionListener{
        void onLoadCool(String versionInGooglePlay);
        void onFail(String erno);
    }
}
