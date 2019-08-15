package com.example.temanhijrah.giantplay.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.temanhijrah.R;
import com.example.temanhijrah.giantplay.object.Favorit;

import java.util.ArrayList;

public class FavoriteDoaAdapter extends RecyclerView.Adapter<FavoriteDoaAdapter.FavoriteDoaViewHolder> {

    private ArrayList<Favorit> listFavoritDoa;
    private OnItemClickListener mListener;

    public FavoriteDoaAdapter(ArrayList<Favorit> listFavoritDoa){
        this.listFavoritDoa = listFavoritDoa;
    }

    @NonNull
    @Override
    public FavoriteDoaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout._list_favorit_doa, viewGroup, false);
        return new FavoriteDoaViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteDoaViewHolder favoriteDoaViewHolder, int i) {
        favoriteDoaViewHolder.txtDoaName.setText(listFavoritDoa.get(i).getSurahName());
    }

    @Override
    public int getItemCount() {
        return (listFavoritDoa != null) ? listFavoritDoa.size() : 0;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }

    public class FavoriteDoaViewHolder extends RecyclerView.ViewHolder{

        public TextView txtDoaName;

        public FavoriteDoaViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            this.txtDoaName = (TextView) itemView.findViewById(R.id.txt_doa_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        listener.onItemClick(position);
                    }
                }
            });
        }
    }

}
