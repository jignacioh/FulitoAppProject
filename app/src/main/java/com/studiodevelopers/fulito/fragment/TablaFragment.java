package com.studiodevelopers.fulito.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.studiodevelopers.fulito.R;
import com.studiodevelopers.fulito.application.ObjectPreference;
import com.studiodevelopers.fulito.model.Campeonato;
import com.studiodevelopers.fulito.util.ComplexPreferences;
import com.studiodevelopers.fulito.view.SlidingTabLayout;

/**
 * Created by Ignacio on 15/02/2015.
 */
public class TablaFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_tabla, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    public static TablaFragment newInstance() {
        TablaFragment fragment=new TablaFragment();
        return  fragment;
    }
}
