package com.lol.lolpro.app.objetos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lol.lolpro.app.Activity_General;
import com.lol.lolpro.app.R;
import com.lol.lolpro.app.bbdd.DBManager;
import com.lol.lolpro.app.json.Objetos.Item;
import com.lol.lolpro.app.utillidades.Constants;

public class ObjetoContenedor extends Fragment {
    private ActionBar actionBar;
    // When requested, this adapter returns a fragment,
    // representing a page.
    private ObjetoPageAdapter mPagerAdapter;
    private ViewPager mViewPager;

    private int numPages;

    /**
     * Item vacio
     */
    public  ObjetoContenedor() {
        // Required empty public constructor
    }

    /**
     * Se encarga del tratamiento necesario para poder crear la vista
     *
     * @param inflater           Sirve para traer un layout hecho en xml como una vista en java
     * @param container          Contenedos para otros elementos View
     * @param savedInstanceState Bundle donde se almacenaran los parámetros del fragment
     * @return Vista de los campeones
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }
        ((Activity_General) getActivity()).updateTitle(Constants.DRAWER_OBJECT);
        ((Activity_General) getActivity()).setInternalFragment(true);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_objeto_contenedor, container, false);
        DBManager dbMan = DBManager.getInstance();
        dbMan.openDatabase(false);

        Bundle args = getArguments();
        dbMan.closeDatabase(false);
        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        mPagerAdapter = new ObjetoPageAdapter(getChildFragmentManager(), (Item) args.getParcelable("item"));
        numPages = mPagerAdapter.getCount();
        mViewPager = (ViewPager) view.findViewById(R.id.objeto_viewPager);
        mViewPager.setAdapter(mPagerAdapter);

        // Create a tab listener that is called when the user changes tabs.
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
            }
        };

        mViewPager.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        // When swiping between pages, select the
                        // corresponding tab.
                        actionBar.setSelectedNavigationItem(position);
                    }
                }
        );
        actionBar.removeAllTabs();
        // Add the tabs, specifying the tab's text and TabListener
        for (int i = 0; i < numPages; i++) {
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(getResources().getStringArray(R.array.titulosObjetos)[i])
                            .setTabListener(tabListener)
            );
        }
        return view;
    }

    /**
     * Se encarga del tratamiento al cerrar el fragment
     * Elimina los tags creados.
     */
    public void onStop() {
        super.onStop();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
    }

    /**
     * Se encaga del tratamiento cuando se va a iniciar el fragment
     * Muestra los tabs que están creados
     */
    @Override
    public void onResume() {
        super.onResume();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
    }
}
