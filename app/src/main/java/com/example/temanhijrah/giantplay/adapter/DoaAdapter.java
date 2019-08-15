package com.example.temanhijrah.giantplay.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.temanhijrah.R;
import com.example.temanhijrah.giantplay.object.Doa;

import java.util.ArrayList;

public class DoaAdapter extends RecyclerView.Adapter<DoaAdapter.DoaViewHolder>{

    private ArrayList<Doa> listDoa;
    private View.OnClickListener mOnClickListener;

    public DoaAdapter (ArrayList<Doa> listDoa){
        this.listDoa = listDoa;
    }

    @NonNull
    @Override
    public DoaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout._list_doa, viewGroup, false);
        DoaViewHolder viewHolder = new DoaViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DoaViewHolder doaViewHolder, int i) {
        doaViewHolder.txtName.setText(listDoa.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return (listDoa != null) ? listDoa.size() : 0;
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener){
        this.mOnClickListener = itemClickListener;
    }

    public class DoaViewHolder extends RecyclerView.ViewHolder{
        public TextView txtName;
        public Button btnDoaFav;
        RelativeLayout layoutDoa;

        public DoaViewHolder(final View view){
            super(view);
            this.layoutDoa = (RelativeLayout)view.findViewById(R.id.layout_doa);
            layoutDoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(view.getContext(), txtName.getText(), Toast.LENGTH_SHORT).show();
                }
            });

            this.txtName = (TextView)view.findViewById(R.id.txt_doa_name);

            this.btnDoaFav = (Button)view.findViewById(R.id.btn_doa_fav);
            btnDoaFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(view.getContext(), "Added to Favourite", Toast.LENGTH_SHORT).show();
                }
            });

            itemView.setTag(this);
            itemView.setOnClickListener(mOnClickListener);
        }

    }
}
