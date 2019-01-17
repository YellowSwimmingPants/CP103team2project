package com.example.tsaimengfu.cp103team2project;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.tsaimengfu.cp103team2project.Management.ManagerInfoFragment;

public class FunctionActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
//                case R.id.itemMemberInfo:
//                    fragment = new MemberInfoFragment();
//                    break;
//                case R.id.itemRecordForm:
//                    fragment = new GameRecordFragment();
//                    break;
                case R.id.itemManagement:
                    fragment = new ManagerInfoFragment();
                    item.setChecked(true);
                    changeFragment(fragment);
                    setTitle(item.getTitle());
                    break;
//
                default:
                    fragment = new GameRecordFragment();
                    item.setChecked(true);
                    changeFragment(fragment);
                    setTitle(item.getTitle());
                    break;

//                default:
//                    fragment = new BillBoardFragment();
//                    break;
            }
//            fragment = new GameRecordFragment();
//            item.setChecked(true);
//            changeFragment(fragment);
//            setTitle(item.getTitle());
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);

        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        initContent();
    }

    private void initContent() {
        bottomNavigationView.setSelectedItemId(R.id.itemRecordForm);
    }

    private void changeFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content, fragment);
        fragmentTransaction.commit();
    }
}
