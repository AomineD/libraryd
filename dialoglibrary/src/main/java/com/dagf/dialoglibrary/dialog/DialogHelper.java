package com.dagf.dialoglibrary.dialog;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dagf.dialoglibrary.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;

public class DialogHelper {

private static String firsth = "api.php?getPackage&id_app=";
    public static void showDialogVersion(final Context gg,String ur ,int id){

        final RequestQueue queue = Volley.newRequestQueue(gg);

        final StringRequest request = new StringRequest(Request.Method.GET, ur+firsth+ id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject b = new JSONObject(response);

                    final String packs = b.getString("old_package");
                    Intent pack = gg.getPackageManager().getLaunchIntentForPackage(packs);

                    if(pack != null){


                        DialogVersion v = new DialogVersion(gg, gg.getString(R.string.app_name), packs);
                        v.show();

                    }

                } catch (JSONException e) {
                    Log.e("MAIN", "onResponse: "+e.getMessage() );
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("MAIN", "onErrorResponse: "+error.networkResponse );
            }
        });

       queue.add(request);

    }
}
