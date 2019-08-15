package com.example.temanhijrah.giantplay.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.temanhijrah.R;
import com.example.temanhijrah.giantplay.object.Hafalan;

import java.util.ArrayList;

public class HafalanAdapter extends RecyclerView.Adapter<HafalanAdapter.HafalanViewHolder>  {

    private ArrayList<Hafalan> listHafalan;
    private View.OnClickListener mOnClickListener;

    public HafalanAdapter(ArrayList<Hafalan> listHafalan){
        this.listHafalan = listHafalan;
    }

    @NonNull
    @Override
    public HafalanAdapter.HafalanViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout._list_hafalan, viewGroup, false);
        HafalanViewHolder viewHolder = new HafalanViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HafalanAdapter.HafalanViewHolder holder, int i) {
        holder.txtName.setText(listHafalan.get(i).getSurat());
        holder.txtTranslate.setText(listHafalan.get(i).getTranslate());
        holder.txtProgress.setText(Integer.toString(listHafalan.get(i).getProgress())+"%");
    }

    @Override
    public int getItemCount() {
        return (listHafalan != null) ? listHafalan.size() : 0;
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener){
        this.mOnClickListener = itemClickListener;
    }

    public class HafalanViewHolder extends RecyclerView.ViewHolder{
        public TextView txtName, txtTranslate, txtProgress;
        RelativeLayout layoutHafalanDetail;

        public HafalanViewHolder(final View itemView){
            super(itemView);
            this.txtName = (TextView) itemView.findViewById(R.id.txt_surah_name);
            this.txtTranslate = (TextView) itemView.findViewById(R.id.txt_surah_translate);
            this.txtProgress = (TextView) itemView.findViewById(R.id.txt_hafalan_progress);
            this.layoutHafalanDetail = (RelativeLayout) itemView.findViewById(R.id.layout_hafalan);

            itemView.setTag(this);
            itemView.setOnClickListener(mOnClickListener);
        }
    }

}
