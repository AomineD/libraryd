package com.dagf.dialoglibrary.dialog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.dagf.dialoglibrary.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WallpaperActivity extends AppCompatActivity {

    public static List<Wallpaper> urlImgs = new ArrayList<>();
    class Wallpaper{
      public  String url;
      public  String name;
    }


    public static void openWallpapers(Context c){
        c.startActivity(new Intent(c, WallpaperActivity.class));
       // urlImgs =  Arrays.asList(c.getResources().getStringArray(R.array.url));
    }

    private void loadWallpapers(){
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "https://jreva.app/cpanel/update_apps/api.php?wllpprs", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                for (int i = 0; i < response.length(); i++) {


                        JSONObject o = response.getJSONObject(i);
                   Wallpaper a = new Wallpaper();
                   a.name = o.getString("wallpaper_name");
                   a.url = o.getString("wallpaper_url");

urlImgs.add(a);


                }

                    shuffle(urlImgs);
                adapterWall.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                queue.getCache().clear();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("MAIN", "onErrorResponse: "+error.getMessage() );
                queue.getCache().clear();
            }
        });

        queue.add(jsonArrayRequest);

    }
    AdapterWall adapterWall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper);
        //Log.e("MAIN", "onCreate: "+urlImgs );
        loadWallpapers();

        RecyclerView recyclerView = findViewById(R.id.rec_list);
        adapterWall = new AdapterWall();


        recyclerView.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false));
   recyclerView.setAdapter(adapterWall);
    }


    private class AdapterWall extends RecyclerView.Adapter<AdapterWall.WallViewHolder> {

        @NonNull
        @Override
        public WallViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(WallpaperActivity.this).inflate(R.layout.wallpaper_item, parent, false);

            return new WallViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull WallViewHolder holder, int position) {
            Wallpaper w = urlImgs.get(position);
            Picasso.get().load(Uri.parse(w.url)).fit().placeholder(R.drawable.placeholder).into(holder.img);
            holder.textView.setText(w.name);
        }

        @Override
        public int getItemCount() {
            return urlImgs.size();
        }

        class WallViewHolder extends RecyclerView.ViewHolder{
private ImageView img;
private TextView textView;

            public WallViewHolder(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.textwal);
                img = itemView.findViewById(R.id.wallpaper_img);
            }
        }
    }



    private static Random random;

    /**
     * Code from method java.util.Collections.shuffle();
     */
    public static void shuffle(List<Wallpaper> array) {
        if (random == null) random = new Random();
        int count = array.size();
        for (int i = count; i > 1; i--) {
            swap(array, i - 1, random.nextInt(i));
        }
    }

    private static void swap(List<Wallpaper> array, int i, int j) {
        Wallpaper temp = array.get(i);
       array.set(i, array.get(j));
        array.set(j, temp);
    }
}