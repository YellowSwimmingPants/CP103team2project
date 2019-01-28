package com.example.tsaimengfu.cp103team2project.Management;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.tsaimengfu.cp103team2project.R;


public class UserManagementActivity extends AppCompatActivity {
    private TabLayout userTabLayout;
    private ViewPager usersViewPager;
    private PagerAdapter usersPageAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_user_list_tab);
        userTabLayout = findViewById(R.id.userTabLayout);
        usersViewPager = findViewById(R.id.usersViewPager);
//        Fragment ManagerFragment = new ManagerFragment();
//        Fragment UserFragment = new UserFragment();
//        getSupportFragmentManager().beginTransaction().add(R.id.flUsers, ManagerFragment).commit();
//        getSupportFragmentManager().beginTransaction().add(R.id.flUsers, UserFragment).commit();
        userTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                usersViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        usersViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(userTabLayout));
        usersPageAdapter = new UsersPageAdapter(this.getSupportFragmentManager(), userTabLayout.getTabCount());
    }

    private class UsersPageAdapter extends FragmentStatePagerAdapter {

        private int numOfTabs;

        public UsersPageAdapter(FragmentManager fm, int numOfTabs) {
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
}
