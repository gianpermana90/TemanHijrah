package com.example.temanhijrah.giantplay.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.temanhijrah.R;
import com.example.temanhijrah.giantplay.object.Favorit;

import java.util.ArrayList;

public class FavoriteSurahAdapter extends RecyclerView.Adapter<FavoriteSurahAdapter.FavoriteSurahViewHolder> {

    private ArrayList<Favorit> listFavoritSurah;
    private OnItemClickListener mListener;

    public FavoriteSurahAdapter(ArrayList<Favorit> listFavoritSurah){
        this.listFavoritSurah = listFavoritSurah;
    }

    @NonNull
    @Override
    public FavoriteSurahViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout._list_favorit_surat, viewGroup, false);
        return new FavoriteSurahViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteSurahViewHolder favoriteSurahViewHolder, int i) {
        favoriteSurahViewHolder.txtSurahName.setText(listFavoritSurah.get(i).getSurahName());
        favoriteSurahViewHolder.txtSurahTranslate.setText(listFavoritSurah.get(i).getSurahTranslate());
    }

    @Override
    public int getItemCount() {
        return (listFavoritSurah != null) ? listFavoritSurah.size() : 0;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }

    public class FavoriteSurahViewHolder extends RecyclerView.ViewHolder{

        public TextView txtSurahName, txtSurahTranslate;
        public Button btnFavorit;

        public FavoriteSurahViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            this.txtSurahName = (TextView) itemView.findViewById(R.id.txt_surah_name);
            this.txtSurahTranslate = (TextView) itemView.findViewById(R.id.txt_surah_translate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        listener.onItemClick(position);
                    }
                }
            });
        }
    }

}
