package com.studiodevelopers.fulito.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.studiodevelopers.fulito.R;
import com.studiodevelopers.fulito.adapter.EquipoAdapter;
import com.studiodevelopers.fulito.application.ObjectPreference;
import com.studiodevelopers.fulito.model.Campeonato;
import com.studiodevelopers.fulito.util.ComplexPreferences;
import com.studiodevelopers.fulito.view.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ignacio on 15/02/2015.
 */
public class PosicionesFragment extends Fragment {
    static class SamplePagerItem {
        private final CharSequence mTitle;
        private final int mIndicatorColor;
        private final int mDividerColor;

        SamplePagerItem(CharSequence title, int indicatorColor, int dividerColor) {
            mTitle = title;
            mIndicatorColor = indicatorColor;
            mDividerColor = dividerColor;
        }

        Fragment createFragment() {
            return TablaFragment.newInstance();
        }

        CharSequence getTitle() {
            return mTitle;
        }
        int getIndicatorColor() {
            return mIndicatorColor;
        }

        int getDividerColor() {
            return mDividerColor;
        }
    }
    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;
    private List<SamplePagerItem> mTabs = new ArrayList<SamplePagerItem>();
    private ObjectPreference objectPreference;
    public static PosicionesFragment newInstance() {
        PosicionesFragment fragment = new PosicionesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mTabs.add(new SamplePagerItem(
                getString(R.string.grupo_a), // Title
                Color.WHITE, // Indicator color
                Color.TRANSPARENT // Divider color
        ));

        mTabs.add(new SamplePagerItem(
                getString(R.string.grupo_b), // Title
                Color.WHITE, // Indicator color
                Color.TRANSPARENT // Divider color
        ));

        mTabs.add(new SamplePagerItem(
                getString(R.string.grupo_c), // Title
                Color.WHITE, // Indicator color
                Color.TRANSPARENT // Divider color
        ));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        objectPreference = (ObjectPreference) getActivity().getApplicationContext();
        ComplexPreferences complexPreferences = objectPreference.getComplexPreference();

        Campeonato campeonato = complexPreferences.getObject("campeonato", Campeonato.class);

        return inflater.inflate(R.layout.fragment_posiciones, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mViewPager.setAdapter(new SampleFragmentPagerAdapter(getChildFragmentManager()));

        mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setViewPager(mViewPager);
        mSlidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {

            @Override
            public int getIndicatorColor(int position) {
                return mTabs.get(position).getIndicatorColor();
            }

            @Override
            public int getDividerColor(int position) {
                return mTabs.get(position).getDividerColor();
            }

        });
    }
    class SampleFragmentPagerAdapter extends FragmentStatePagerAdapter {

        SampleFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return the {@link android.support.v4.app.Fragment} to be displayed at {@code position}.
         * <p>
         * Here we return the value returned from {@link SamplePagerItem#createFragment()}.
         */
        @Override
        public Fragment getItem(int i) {

            return mTabs.get(i).createFragment();
        }

        @Override
        public int getCount() {
            return mTabs.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabs.get(position).getTitle();
        }

    }
}
