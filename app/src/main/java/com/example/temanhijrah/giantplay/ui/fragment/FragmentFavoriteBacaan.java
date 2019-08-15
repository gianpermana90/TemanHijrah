package com.example.temanhijrah.giantplay.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.temanhijrah.R;
import com.example.temanhijrah.giantplay.adapter.FavoriteDoaAdapter;
import com.example.temanhijrah.giantplay.dev.Message;
import com.example.temanhijrah.giantplay.object.Favorit;

import java.util.ArrayList;

public class FragmentFavoriteBacaan extends Fragment {

    private RecyclerView recyclerView;
    private FavoriteDoaAdapter adapter;
    private ArrayList<Favorit> listFavoritDoa;

    public FragmentFavoriteBacaan() {

    }

    public static FragmentFavoriteBacaan getInstance(){
        FragmentFavoriteBacaan fragment = new FragmentFavoriteBacaan();
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

        View view = inflater.inflate(R.layout.fragment_favorite_bacaan, container, false);
        addData();

        recyclerView = view.findViewById(R.id.recyclerview_favorit_bacaan);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new FavoriteDoaAdapter(listFavoritDoa);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new FavoriteDoaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Message.message(getContext(), "clicked");
            }
        });

        return view;
    }

    void addData(){
        listFavoritDoa = new ArrayList<>();
        listFavoritDoa .add(new Favorit(1, "Doa Harian", "001", "Doa Sebelum Makan", ""));
        listFavoritDoa .add(new Favorit(2, "Doa Harian", "002", "Doa Setelah Makan", ""));
        listFavoritDoa .add(new Favorit(3, "Doa Harian", "003", "Doa Sebelum Tidur", ""));
        listFavoritDoa .add(new Favorit(4, "Doa Harian", "004", "Doa Bangun Tidur", ""));
    }

}
