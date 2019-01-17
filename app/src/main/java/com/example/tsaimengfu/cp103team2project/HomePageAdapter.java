package com.example.tsaimengfu.cp103team2project;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

//繼承FragmentPagerAdapter
public class HomePageAdapter extends FragmentStatePagerAdapter {

    //建立屬性 分頁頁數計數器
    private int numOfTabs;

    //建構式
    public HomePageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    //取得分頁畫面與內容
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PlayerFragment();
            case 1:
                return new ChangePlayerFragment();
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


