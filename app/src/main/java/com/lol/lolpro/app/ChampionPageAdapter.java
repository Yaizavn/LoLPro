package com.lol.lolpro.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by sergio on 4/06/14.
 */
public class ChampionPageAdapter extends FragmentStatePagerAdapter {
    public static final int numPages = 2;
    private Bundle argsCampeon;

    public ChampionPageAdapter(FragmentManager fm, Bundle args) {
        super(fm);
        argsCampeon = args;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frg = null;
        switch(position){
            case 0:
                frg = new CampeonInfo();
                break;
            case 1:
                frg = new Historia();
                break;
            default:
                break;
        }
        frg.setArguments(argsCampeon);
        return frg;
    }

    @Override
    public int getCount() {
        return numPages;
    }
}
