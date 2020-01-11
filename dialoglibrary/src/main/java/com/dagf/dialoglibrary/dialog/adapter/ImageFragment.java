package com.dagf.dialoglibrary.dialog.adapter;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.dagf.dialoglibrary.R;
import com.labo.kaji.fragmentanimations.CubeAnimation;
import com.labo.kaji.fragmentanimations.MoveAnimation;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageFragment extends Fragment {


    public ImageFragment() {
        // Required empty public constructor
    }
    public String urlimg="";
    public String urlApp="";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmentVio

        View v = inflater.inflate(R.layout.fragment_image, container, false);

        ImageView img = v.findViewById(R.id.img_big);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VipagAdapter.downloadApp(getContext(), urlApp);
            }
        });

        if(!urlimg.isEmpty())
        Picasso.get().load(Uri.parse(urlimg)).fit().into(img);

        return v;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if (enter) {
            return CubeAnimation.create(CubeAnimation.LEFT, enter, 2000);
        } else {
            return CubeAnimation.create(CubeAnimation.RIGHT, enter, 2000);
        }
    }

}
