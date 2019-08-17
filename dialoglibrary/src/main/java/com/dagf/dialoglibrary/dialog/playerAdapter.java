package com.dagf.dialoglibrary.dialog;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dagf.dialoglibrary.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class playerAdapter extends RecyclerView.Adapter<playerAdapter.HolderPlayer> {

    private Context mContext;
    private ArrayList<Integer> intDrawables;
    private ArrayList<PlayerObject> playerObjects;

    public playerAdapter(Context mm){
        this.mContext = mm;
    }

    public void setDrawables(ArrayList<Integer> drawables){
        this.intDrawables = drawables;
    }

    public void setPlayers(ArrayList<PlayerObject> objects){
        this.playerObjects = objects;
    }

    @NonNull
    @Override
    public HolderPlayer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.player_item, parent, false);
        return new HolderPlayer(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderPlayer holder, int position) {
        if(intDrawables != null)
            holder.icon.setImageDrawable(mContext.getResources().getDrawable(intDrawables.get(position))); // TODO: CAMBIAR A ICONO DESEADO
   else if(playerObjects != null){
            Picasso.get().load(Uri.parse(playerObjects.get(position).url_icon)).fit().into(holder.icon);
        }

    }


    @Override
    public int getItemCount() {
        if(intDrawables != null)
        return intDrawables.size();
else if(playerObjects != null)
    return playerObjects.size();
else
    return 0;
    }

    class HolderPlayer extends RecyclerView.ViewHolder{

        private ImageView icon;
        public HolderPlayer(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon_item);
        }
    }
}
