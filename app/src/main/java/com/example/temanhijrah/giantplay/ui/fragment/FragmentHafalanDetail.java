package com.example.temanhijrah.giantplay.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.temanhijrah.R;
import com.example.temanhijrah.giantplay.adapter.HafalanDetailAdapter;
import com.example.temanhijrah.giantplay.object.Hafalan;
import com.example.temanhijrah.giantplay.ui.activity.HafalanActivity;
import com.example.temanhijrah.giantplay.ui.activity.PlayerHafalanActivity;

import java.util.ArrayList;

public class FragmentHafalanDetail extends Fragment {

    private RecyclerView recyclerView;
    private HafalanDetailAdapter adapter;
    private ArrayList<Hafalan> listHafalan;

    public FragmentHafalanDetail() {
    }

    public static FragmentHafalanDetail getInstance(){
        FragmentHafalanDetail fragment = new FragmentHafalanDetail();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hafalan_detail, container, false);
        addData();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_hafalan_detail);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new HafalanDetailAdapter(listHafalan);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new HafalanDetailAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getContext(), "Item Click "+position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onListenClick(int position) {
                Toast.makeText(getContext(), "Listen Click "+position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), PlayerHafalanActivity.class);
                startActivity(intent);
            }

            @Override
            public void onRecordClick(int position) {
                Toast.makeText(getContext(), "Record Click "+position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), HafalanActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    void addData() {
        listHafalan = new ArrayList<>();
        listHafalan.add(new Hafalan("Ayat 1", "Pembukaan", 80));
        listHafalan.add(new Hafalan("Ayat 2", "Sapi Betina", 0));
        listHafalan.add(new Hafalan("Ayat 3", "Keluarga Imran", 100));
        listHafalan.add(new Hafalan("Ayat 4", "Realita", 10));
        listHafalan.add(new Hafalan("Ayat 5", "Pembukaan", 80));
        listHafalan.add(new Hafalan("Ayat 6", "Sapi Betina", 0));
        listHafalan.add(new Hafalan("Ayat 7", "Keluarga Imran", 100));
    }
}
