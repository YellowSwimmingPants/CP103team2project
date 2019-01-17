package com.example.tsaimengfu.cp103team2project.Management;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.tsaimengfu.cp103team2project.Management.ManagerFragment;
import com.example.tsaimengfu.cp103team2project.Management.UserFragment;
import com.example.tsaimengfu.cp103team2project.R;

public class UserManagementActivity extends AppCompatActivity {
    private TextView tvManager, tvCommonUser;


// 01
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_management);
        Fragment ManagerFragment = new ManagerFragment();
        Fragment UserFragment = new UserFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.rvManagers, ManagerFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.rvCommonUser, UserFragment).commit();

    }

}
