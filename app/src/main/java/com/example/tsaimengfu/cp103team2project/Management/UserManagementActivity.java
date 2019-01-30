package com.example.tsaimengfu.cp103team2project.Management;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
//        show();


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
        usersPageAdapter = new UserPageAdapter(getSupportFragmentManager(), userTabLayout.getTabCount());
        usersViewPager.setAdapter(usersPageAdapter);

    }


    private void show() {
        Fragment fragment = new ManagerFragment();
        switchFragment(fragment);
    }

    private void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flUsers, fragment);
        fragmentTransaction.commit();
    }

}
