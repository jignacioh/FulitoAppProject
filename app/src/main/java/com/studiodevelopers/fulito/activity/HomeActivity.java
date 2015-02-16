package com.studiodevelopers.fulito.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.studiodevelopers.fulito.R;
import com.studiodevelopers.fulito.adapter.MenuLateralAdapter;
import com.studiodevelopers.fulito.fragment.EquipoListFragment;
import com.studiodevelopers.fulito.fragment.PosicionesFragment;
import com.studiodevelopers.fulito.fragment.VistaRapidaFragment;
import com.studiodevelopers.fulito.model.GenericItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ignacio on 05/02/2015.
 */
public class HomeActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private FrameLayout mFrameLayoutContent,mFrameLayoutLeft;
    ActionBarDrawerToggle actionBarDrawerToggle;
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
     static List<Integer> listMenuIcon= new ArrayList<Integer>(Arrays.asList(R.drawable.ver_ahora,R.drawable.ver_ahora,R.drawable.ver_ahora,
    R.drawable.ver_ahora,R.drawable.ver_ahora,R.drawable.ver_ahora,R.drawable.ver_ahora));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer);
        mFrameLayoutContent=(FrameLayout)findViewById(R.id.content_frame);
        mFrameLayoutLeft=(FrameLayout)findViewById(R.id.frame_left);
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();

        MenuFragment menuFragment=MenuFragment.getInstance();
        fragmentTransaction.replace(R.id.frame_left,menuFragment);
        fragmentTransaction.commit();

        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        VistaRapidaFragment principalFragment=VistaRapidaFragment.newInstance();
        fragmentTransaction.replace(R.id.content_frame,principalFragment);
        fragmentTransaction.commit();

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close){
            public void onDrawerClosed(View v){
                super.onDrawerClosed(v);
                invalidateOptionsMenu();
                syncState();
            }
            public void onDrawerOpened(View v){
                super.onDrawerOpened(v);
                invalidateOptionsMenu();
                syncState();
            }
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        actionBarDrawerToggle.syncState();
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if (id==android.R.id.home){
            if (drawerLayout.isDrawerOpen(mFrameLayoutLeft)){
                drawerLayout.closeDrawer(mFrameLayoutLeft);
            } else {
                drawerLayout.openDrawer(mFrameLayoutLeft);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public static class MenuFragment extends Fragment {
        List<String> listMenuName;
        ListView listView;
        List<GenericItem> list;
        MenuLateralAdapter menuLateralAdapter;
        public static MenuFragment getInstance(){
            MenuFragment menu=new MenuFragment();
            return menu;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View view=inflater.inflate(R.layout.fragment_menu_rapido,container,false);
            listView=(ListView)view.findViewById(R.id.mylist);
            menuLateralAdapter=new MenuLateralAdapter(getActivity(),getItemsMenu());
            listView.setAdapter(menuLateralAdapter);
            listView.setOnItemClickListener(new DrawerItemClickListener());
            return view;
        }

        public List<GenericItem> getItemsMenu(){
            list=new ArrayList<GenericItem>();
            listMenuName= Arrays.asList(getActivity().getResources().getStringArray(R.array.listaMenuOptions));

            for (int i=0;i<listMenuName.size();i++){
                list.add(new GenericItem(listMenuName.get(i),listMenuIcon.get(i)));
            }

            return list;
        }
         class DrawerItemClickListener implements ListView.OnItemClickListener {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                selectItem(position);
            }
             private void selectItem(int position) {

                 switch (position){
                     case 0:break;
                     case 1:
                         Fragment fragment =  EquipoListFragment.newInstance();

                         // Insert the fragment by replacing any existing fragment
                         FragmentManager fragmentManager = getFragmentManager();
                         fragmentManager.beginTransaction()
                                 .replace(R.id.content_frame, fragment)
                                 .commit();


                         break;
                     case 2:
                         Fragment fragment1= PosicionesFragment.newInstance();
                         FragmentManager fragmentManager1 = getFragmentManager();
                         fragmentManager1.beginTransaction()
                                 .replace(R.id.content_frame, fragment1)
                                 .commit();
                         break;
                     case 3:break;
                     case 4:break;
                     case 5:break;
                 }
                 // Highlight the selected item, update the title, and close the drawer
                 // mDrawerList.setItemChecked(position, true);
                 setTitle(list.get(position).getTittle());
                 // mDrawerLayout.closeDrawer(mDrawerList

             }

             public void setTitle(CharSequence title) {

                 ((HomeActivity)getActivity()).getSupportActionBar().setTitle(title);
             }
        }
    }
}
