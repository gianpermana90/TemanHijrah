package com.example.temanhijrah.giantplay.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.temanhijrah.R;
import com.example.temanhijrah.giantplay.object.Surah;

import java.util.ArrayList;

public class SurahAdapter extends RecyclerView.Adapter<SurahAdapter.SurahViewHolder>{

    private ArrayList<Surah> listSurah;
    private OnItemClickListener mOnClickListener;

    public SurahAdapter(ArrayList<Surah> listSurah){
        this.listSurah = listSurah;
    }

    @NonNull
    @Override
    public SurahAdapter.SurahViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout._list_surah, viewGroup, false);
        return new SurahViewHolder(view, mOnClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SurahAdapter.SurahViewHolder holder, int i) {
        holder.txtName.setText(listSurah.get(i).getName());
        holder.txtTranslate.setText(listSurah.get(i).getTranslate());
    }

    @Override
    public int getItemCount() {
        return (listSurah != null) ? listSurah.size() : 0;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
        void onFavClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener){
        this.mOnClickListener = itemClickListener;
    }

    public class SurahViewHolder extends RecyclerView.ViewHolder{
        public TextView txtName, txtTranslate;
        public Button btnFavourite;

        public SurahViewHolder(View itemView, final OnItemClickListener listener){
            super(itemView);
            this.txtName = (TextView) itemView.findViewById(R.id.txt_surah_name);
            this.txtTranslate = (TextView) itemView.findViewById(R.id.txt_surah_translate);
            this.btnFavourite = (Button) itemView.findViewById(R.id.btn_surah_fav);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            btnFavourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onFavClick(position);
                        }
                    }
                }
            });
        }
    }

}
