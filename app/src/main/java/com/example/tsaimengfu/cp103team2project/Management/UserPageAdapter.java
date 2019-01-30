package com.example.tsaimengfu.cp103team2project.Management;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class UserPageAdapter extends FragmentStatePagerAdapter {

    private int numOfTabs;

    public UserPageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ManagerFragment();
            case 1:
                return new UserFragment();

            default:
                return null;
        }
    }
    //取得分頁頁數
    @Override
    public int getCount() {
        return numOfTabs;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
