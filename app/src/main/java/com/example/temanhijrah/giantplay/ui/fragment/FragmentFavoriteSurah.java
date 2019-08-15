package com.example.temanhijrah.giantplay.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.temanhijrah.R;
import com.example.temanhijrah.giantplay.adapter.FavoriteSurahAdapter;
import com.example.temanhijrah.giantplay.dev.Message;
import com.example.temanhijrah.giantplay.object.Favorit;

import java.util.ArrayList;

public class FragmentFavoriteSurah extends Fragment {

    private RecyclerView recyclerView;
    private FavoriteSurahAdapter adapter;
    private ArrayList<Favorit> listFavorit;

    public FragmentFavoriteSurah() {

    }

    public static FragmentFavoriteSurah getInstance(){
        FragmentFavoriteSurah fragment = new FragmentFavoriteSurah();
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

        View view = inflater.inflate(R.layout.fragment_favorite_surah, container, false);
        addData();

        recyclerView = view.findViewById(R.id.recyclerview_favorit_surah);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new FavoriteSurahAdapter(listFavorit);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new FavoriteSurahAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Message.message(getContext(), "Clicked");
            }
        });

        return view;
    }

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            Message.message(getContext(), "Clicked");
        }
    };

    void addData(){
        listFavorit = new ArrayList<>();
        listFavorit.add(new Favorit(1, "Surah", "001", "Al-Fatihah", "Pembukaan"));
        listFavorit.add(new Favorit(2, "Surah", "002", "Al-Baqarah", "Sapi Betina"));
        listFavorit.add(new Favorit(3, "Surah", "003", "Al-Imran", "Keluarga Imran"));
        listFavorit.add(new Favorit(4, "Surah", "004", "An-Naas", "Manusia"));
    }

}
