package com.example.temanhijrah.giantplay.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.temanhijrah.R;
import com.example.temanhijrah.giantplay.adapter.DoaAdapter;
import com.example.temanhijrah.giantplay.object.Doa;
import com.example.temanhijrah.giantplay.ui.activity.PlayerDoaActivity;

import java.util.ArrayList;

public class FragmentDoaList extends Fragment {

    private RecyclerView recyclerView;
    private DoaAdapter adapter;
    private ArrayList<Doa> listDoa;

    public FragmentDoaList() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doa_list, container, false);
        addData();

        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerview_doa);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new DoaAdapter(listDoa);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(onItemClickListener);

        return view;
    }

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
            int position = viewHolder.getAdapterPosition();
            Toast.makeText(getActivity(), "Clicked position "+position, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(v.getContext(), PlayerDoaActivity.class);
            startActivity(intent);
        }
    };

    void addData(){
        listDoa = new ArrayList<>();
        listDoa.add(new Doa("Doa Sebelum Makan", false));
        listDoa.add(new Doa("Doa Sebelum Tidur", true));
        listDoa.add(new Doa("Doa Belajar", true));
        listDoa.add(new Doa("Doa Ingin Bepergian", false));
        listDoa.add(new Doa("Doa Sebelum Makan", false));
        listDoa.add(new Doa("Doa Sebelum Tidur", true));
        listDoa.add(new Doa("Doa Belajar", true));
        listDoa.add(new Doa("Doa Ingin Bepergian", false));
        listDoa.add(new Doa("Doa Sebelum Makan", false));
        listDoa.add(new Doa("Doa Sebelum Tidur", true));
        listDoa.add(new Doa("Doa Belajar", true));
        listDoa.add(new Doa("Doa Ingin Bepergian", false));
    }
}
