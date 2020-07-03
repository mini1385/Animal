package com.je1224.animal;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyAdapter extends FragmentPagerAdapter {

    Fragment[] fragments=new Fragment[4];

    public MyAdapter(@NonNull FragmentManager fm) {
        super(fm);

        fragments[0]=new Fragment_calendar();
        fragments[1]=new Fragment_photo();
        fragments[2]=new Fragment_hospital();
        fragments[3]=new Fragment_shop();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}
