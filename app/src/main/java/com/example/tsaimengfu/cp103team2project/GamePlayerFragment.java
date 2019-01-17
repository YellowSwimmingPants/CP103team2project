package com.example.tsaimengfu.cp103team2project;


import android.os.Bundle;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GamePlayerFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager homeViewPager;
    private PagerAdapter homePagerAdapter;
    private TabItem tabPlayer, tabChangePlayer;
    private View game_player_fragment;

    public GamePlayerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        game_player_fragment = inflater.inflate(R.layout.fragment_game_player, container, false);
        initial();
        return game_player_fragment;
    }


    //initial 初始化
    private void initial() {
        tabLayout = game_player_fragment.findViewById(R.id.tabLayout);
        homeViewPager = game_player_fragment.findViewById(R.id.homeViewPager);
        tabPlayer = game_player_fragment.findViewById(R.id.player);
        tabChangePlayer = game_player_fragment.findViewById(R.id.changePlayer);

        //tabLayout監聽
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                homeViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //刷新 Fragment 頁面
        homeViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //取得FragmentManager權限 並取得目前分頁所在的頁數
        homePagerAdapter = new HomePageAdapter(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        //將剛剛取到的分頁所在的頁數 顯示在Fragment上
        homeViewPager.setAdapter(homePagerAdapter);

    }
}

