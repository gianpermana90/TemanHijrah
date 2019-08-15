package com.example.temanhijrah.giantplay.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.temanhijrah.R;

public class FragmentFavoriteDoa extends Fragment {

    public FragmentFavoriteDoa() {

    }

    public static FragmentFavoriteDoa getInstance(){
        FragmentFavoriteDoa fragment = new FragmentFavoriteDoa();
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
        return inflater.inflate(R.layout.fragment_favorite_doa, container, false);
    }

}
