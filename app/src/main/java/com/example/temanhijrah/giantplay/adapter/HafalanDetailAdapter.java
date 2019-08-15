package com.example.temanhijrah.giantplay.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.temanhijrah.R;
import com.example.temanhijrah.giantplay.object.Hafalan;

import java.util.ArrayList;

public class HafalanDetailAdapter extends RecyclerView.Adapter<HafalanDetailAdapter.HafalanDetailViewHolder> {

    private ArrayList<Hafalan> listHafalan;
    private OnItemClickListener mListener;

    public HafalanDetailAdapter(ArrayList<Hafalan> listHafalan) {
        this.listHafalan = listHafalan;
    }

    @Override
    public HafalanDetailAdapter.HafalanDetailViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout._list_hafalan_detail, viewGroup, false);
        return new HafalanDetailViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(HafalanDetailAdapter.HafalanDetailViewHolder holder, int i) {
        holder.txtAyat.setText(listHafalan.get(i).getSurat());
    }

    @Override
    public int getItemCount() {
        return (listHafalan != null) ? listHafalan.size() : 0;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
        void onListenClick(int position);
        void onRecordClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }

    public class HafalanDetailViewHolder extends RecyclerView.ViewHolder {

        public TextView txtAyat;
        public Button btnRecord, btnListen;

        public HafalanDetailViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            this.txtAyat = (TextView) itemView.findViewById(R.id.txt_ayat);
            this.btnRecord = (Button) itemView.findViewById(R.id.btn_hafalan_record);
            this.btnListen = (Button) itemView.findViewById(R.id.btn_hafalan_listen);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            btnListen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onListenClick(position);
                        }
                    }
                }
            });

            btnRecord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onRecordClick(position);
                        }
                    }
                }
            });

        }

    }

}
