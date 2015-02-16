package com.studiodevelopers.fulito.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.app.ListFragment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.studiodevelopers.fulito.R;

import com.studiodevelopers.fulito.adapter.EquipoAdapter;
import com.studiodevelopers.fulito.application.ObjectPreference;
import com.studiodevelopers.fulito.fragment.dummy.DummyContent;
import com.studiodevelopers.fulito.model.Campeonato;
import com.studiodevelopers.fulito.util.ComplexPreferences;


public class EquipoListFragment extends Fragment {

    private RecyclerView recyclerView;
    private ObjectPreference objectPreference;
    // TODO: Rename and change types of parameters
    public static EquipoListFragment newInstance() {
        EquipoListFragment fragment = new EquipoListFragment();
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public EquipoListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lista_equipos, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        objectPreference = (ObjectPreference) getActivity().getApplicationContext();
        ComplexPreferences complexPreferences = objectPreference.getComplexPreference();

        Campeonato campeonato = complexPreferences.getObject("campeonato", Campeonato.class);

        recyclerView.setAdapter(new EquipoAdapter(campeonato.getEquiposCampeonato(),getActivity()));
        return v;
    }
}
