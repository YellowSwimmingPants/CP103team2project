package com.example.tsaimengfu.cp103team2project.QRCode.qrcode;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.tsaimengfu.cp103team2project.R;

public class CreateTeamActivity extends AppCompatActivity {
    private ActionBar actionBar;

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.item_ScanQrCode:
                    fragment = new ScanQRFragment();
                    changeFragment(fragment);
                    setTitle(R.string.textScanQrCode);
                    if (actionBar != null) {
                        actionBar.setIcon(R.drawable.scan_code);
                    }
                    return true;
                case R.id.item_GenQrCode:
                    fragment = new GenQRFragment();
                    changeFragment(fragment);
                    setTitle(R.string.textCreateCode);
                    if (actionBar != null) {
                        actionBar.setIcon(R.drawable.create_code);
                    }
                    return true;
                default:
                    initContent();
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afterlogin);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            // 設定action bar可以顯示圖示 01
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setIcon(R.drawable.scan_code);
        }
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        initContent();
    }

    private void initContent() {
        Fragment fragment = new ScanQRFragment();
        changeFragment(fragment);
        setTitle(R.string.textScanQrCode);
    }

    private void changeFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content, fragment);
        fragmentTransaction.commit();
    }
}
