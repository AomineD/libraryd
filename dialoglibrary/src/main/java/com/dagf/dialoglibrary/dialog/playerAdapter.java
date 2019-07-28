package com.dagf.dialoglibrary.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dagf.dialoglibrary.R;

import java.util.ArrayList;


public class playerAdapter extends RecyclerView.Adapter<playerAdapter.HolderPlayer> {

    private Context mContext;
    private ArrayList<Integer> intDrawables = new ArrayList<>();

    public playerAdapter(Context mmm,@NonNull ArrayList<Integer> drawables){
        this.mContext = mmm;
        this.intDrawables = drawables;
    }

    @NonNull
    @Override
    public HolderPlayer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.player_item, parent, false);
        return new HolderPlayer(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderPlayer holder, int position) {
            holder.icon.setImageDrawable(mContext.getResources().getDrawable(intDrawables.get(position))); // TODO: CAMBIAR A ICONO DESEADO
    }


    @Override
    public int getItemCount() {
        return intDrawables.size();
    }

    class HolderPlayer extends RecyclerView.ViewHolder{

        private ImageView icon;
        public HolderPlayer(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon_item);
        }
    }
}
