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

import com.dagf.dialoglibrary.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WallpaperActivity extends AppCompatActivity {

    public static List<String> urlImgs = new ArrayList<>();


    public static void openWallpapers(Context c){
        c.startActivity(new Intent(c, WallpaperActivity.class));
        urlImgs =  Arrays.asList(c.getResources().getStringArray(R.array.url));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper);
        //Log.e("MAIN", "onCreate: "+urlImgs );
        shuffle(urlImgs);
        RecyclerView recyclerView = findViewById(R.id.rec_list);
        AdapterWall adapterWall = new AdapterWall();


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
            Picasso.get().load(Uri.parse(urlImgs.get(position))).fit().placeholder(R.drawable.placeholder).into(holder.img);
        }

        @Override
        public int getItemCount() {
            return urlImgs.size();
        }

        class WallViewHolder extends RecyclerView.ViewHolder{
private ImageView img;

            public WallViewHolder(@NonNull View itemView) {
                super(itemView);
                img = itemView.findViewById(R.id.wallpaper_img);
            }
        }
    }



    private static Random random;

    /**
     * Code from method java.util.Collections.shuffle();
     */
    public static void shuffle(List<String> array) {
        if (random == null) random = new Random();
        int count = array.size();
        for (int i = count; i > 1; i--) {
            swap(array, i - 1, random.nextInt(i));
        }
    }

    private static void swap(List<String> array, int i, int j) {
        String temp = array.get(i);
       array.set(i, array.get(j));
        array.set(j, temp);
    }
}