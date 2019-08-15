package com.example.temanhijrah;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.temanhijrah.giantplay.Parameter.DataSurah;
import com.example.temanhijrah.giantplay.adapter.SurahAdapter;
import com.example.temanhijrah.giantplay.dev.Message;
import com.example.temanhijrah.giantplay.object.Surah;
import com.example.temanhijrah.giantplay.ui.activity.PlayerSurahActivity;

import java.util.ArrayList;

public class FragmentQuran extends Fragment {

    private RecyclerView recyclerView;
    private SurahAdapter adapter;
    private ArrayList<Surah> listSurah;

    private DataSurah dataSurah = new DataSurah();

    public FragmentQuran(){}

    public static FragmentQuran getInstance(){
        FragmentQuran fragment = new FragmentQuran();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_quran, container, false);
        addData();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_surah);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new SurahAdapter(listSurah);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new SurahAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Message.message(getContext(), "item click "+position);
            }

            @Override
            public void onFavClick(int position) {
                Message.message(getContext(), "Fav Click "+position);
            }
        });

        return view;
    }

    void addData(){
        listSurah = new ArrayList<>();
        for (int i = 0; i < dataSurah.surah.length; i++) {
            listSurah.add(new Surah(dataSurah.surah[i], dataSurah.translate[i], ""));
        }
    }

}
