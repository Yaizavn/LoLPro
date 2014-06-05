package com.lol.lolpro.app;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class Champion extends Fragment {

    private ActionBar actionBar;
    // When requested, this adapter returns a fragment,
    // representing a page.
    private ChampionPageAdapter mPagerAdapter;
    private ViewPager mViewPager;

    private int numPages;
    private BBDDHelper helper;

    public Champion() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(container == null){
            return null;
        }
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_champion, container, false);
        helper = new BBDDHelper(view.getContext());
        Bundle args = getArguments();
        args.putStringArray("data", helper.obtenerDatosCampeon(args.getInt("id", -1)));

        actionBar = ((ActionBarActivity)getActivity()).getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        mPagerAdapter = new ChampionPageAdapter(getChildFragmentManager(), args);
        numPages = mPagerAdapter.numPages;
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        mViewPager.setAdapter(mPagerAdapter);


        // Create a tab listener that is called when the user changes tabs.
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {}

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {}
        };

        mViewPager.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        // When swiping between pages, select the
                        // corresponding tab.
                        actionBar.setSelectedNavigationItem(position);
                    }
                });
        if(actionBar.getTabCount() >= numPages) {
            for(int i = 0; i < numPages; i++){
                actionBar.getTabAt(i).setTabListener(tabListener);
            }
            actionBar.getTabAt(0).select();
        }
        else{
            // Add the tabs, specifying the tab's text and TabListener
            for (int i = 0; i < numPages; i++) {
                actionBar.addTab(
                        actionBar.newTab()
                                .setText(getResources().getStringArray(R.array.titulosCampeones)[i])
                                .setTabListener(tabListener)
                );
            }
        }
        // Inflate the layout for this fragment
        return view;
    }

    /*@Override
    public void onPause(){
        super.onPause();
        getActivity().supportInvalidateOptionsMenu();
    }*/
}
