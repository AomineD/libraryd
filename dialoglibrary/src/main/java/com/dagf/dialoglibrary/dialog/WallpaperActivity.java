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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dagf.dialoglibrary.R;
import com.squareup.picasso.Picasso;

import java.util.Random;

public class WallpaperActivity extends AppCompatActivity {

    public static String[] urlImgs = new String[]{
      "https://i.pinimg.com/564x/36/da/54/36da54f0cdeff024ccb1635991840132.jpg", //1
      "https://i.pinimg.com/564x/85/b9/a4/85b9a445471e7947f02bd517cd9aa776.jpg", //2
      "https://i.pinimg.com/564x/96/96/c0/9696c07e6cce50ef2695701faf466b34.jpg", //3
       "https://i.pinimg.com/564x/d1/bb/85/d1bb8574266947257b0579ef8e327c3a.jpg", //4
            "https://i.pinimg.com/564x/0b/d3/6c/0bd36c4a084a6b867c7f177dd37af7cf.jpg", //5
            "https://i.pinimg.com/564x/7b/01/10/7b01107fbf60d2040e1fb4dd82ae2b13.jpg", //6
            "https://i.pinimg.com/564x/7e/bb/8f/7ebb8f077920678d8c8d92df72f26c98.jpg", //7
            "https://i.pinimg.com/564x/d6/d9/23/d6d92313d7c7eb6c28208df76c9860a4.jpg", //8
            "https://i.pinimg.com/564x/99/3c/75/993c75d75cc2b2cc0a6f3b58d7aac1df.jpg", //9
            "https://wallpapercave.com/wp/wp4287713.jpg" //10
    };


    public static void openWallpapers(Context c){
        c.startActivity(new Intent(c, WallpaperActivity.class));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper);

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
            Picasso.get().load(Uri.parse(urlImgs[position])).fit().placeholder(R.drawable.placeholder).into(holder.img);
        }

        @Override
        public int getItemCount() {
            return urlImgs.length;
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
    public static void shuffle(String[] array) {
        if (random == null) random = new Random();
        int count = array.length;
        for (int i = count; i > 1; i--) {
            swap(array, i - 1, random.nextInt(i));
        }
    }

    private static void swap(String[] array, int i, int j) {
        String temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}