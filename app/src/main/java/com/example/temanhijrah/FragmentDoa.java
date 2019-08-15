package com.example.temanhijrah;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.temanhijrah.giantplay.ui.fragment.FragmentDoaList;


public class FragmentDoa extends Fragment {

    private ImageButton btnDoaHarian, btnBacaanSholat;

    public FragmentDoa() {
    }

    public static FragmentDoa getInstance(){
        FragmentDoa fragment = new FragmentDoa();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doa, container, false);

        btnDoaHarian = (ImageButton)view.findViewById(R.id.btn_doa_harian);
        btnDoaHarian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showListDoa();
            }
        });

        return view;
    }

    private void showListDoa(){
        FragmentDoaList fragment = new FragmentDoaList();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.doa_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}
