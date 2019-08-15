package com.example.temanhijrah;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.temanhijrah.giantplay.adapter.HafalanAdapter;
import com.example.temanhijrah.giantplay.object.Hafalan;
import com.example.temanhijrah.giantplay.ui.fragment.FragmentHafalanDetail;

import java.util.ArrayList;


public class FragmentHafalan extends Fragment {

    private RecyclerView recyclerView;
    private HafalanAdapter adapter;
    private ArrayList<Hafalan> listHafalan;

    public FragmentHafalan(){

    }

    public static FragmentHafalan getInstance(){
        FragmentHafalan fragment = new FragmentHafalan();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hafalan, container, false);
        addData();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_hafalan);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new HafalanAdapter(listHafalan);
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

            showHafalanDetail();
        }
    };

    private void showHafalanDetail(){
        FragmentHafalanDetail fragment = new FragmentHafalanDetail();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.hafalan_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    void addData(){
        listHafalan = new ArrayList<>();
        listHafalan.add(new Hafalan("Al-Fatihah", "Pembukaan", 80));
        listHafalan.add(new Hafalan("Al-Baqarah", "Sapi Betina", 0));
        listHafalan.add(new Hafalan("Ali Imran", "Keluarga Imran", 100));
        listHafalan.add(new Hafalan("Al-Haqqah", "Realita", 10));
        listHafalan.add(new Hafalan("Al-Fatihah", "Pembukaan", 80));
        listHafalan.add(new Hafalan("Al-Baqarah", "Sapi Betina", 0));
        listHafalan.add(new Hafalan("Ali Imran", "Keluarga Imran", 100));
        listHafalan.add(new Hafalan("Al-Haqqah", "Realita", 10));
        listHafalan.add(new Hafalan("Al-Fatihah", "Pembukaan", 80));
        listHafalan.add(new Hafalan("Al-Baqarah", "Sapi Betina", 0));
        listHafalan.add(new Hafalan("Ali Imran", "Keluarga Imran", 100));
        listHafalan.add(new Hafalan("Al-Haqqah", "Realita", 10));
    }

}
