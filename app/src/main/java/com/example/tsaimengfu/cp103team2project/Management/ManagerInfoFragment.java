package com.example.tsaimengfu.cp103team2project.Management;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tsaimengfu.cp103team2project.R;


public class ManagerInfoFragment extends Fragment {
    private Button btFixInfo, btManagement;
    private TextView tvUserAccount, tvAccount, tvUserName, tvName, tvPriority, tvPri;
    private Activity activity;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_manager, container, false);
        show();
        return view;


    }

    private void show() {
        btFixInfo = btFixInfo.findViewById(R.id.btFixInfo);
        btManagement = btManagement.findViewById(R.id.btManagement);
        tvUserAccount = tvUserAccount.findViewById(R.id.tvUserAccount);
        tvAccount = tvAccount.findViewById(R.id.tvAccount);
        tvUserName = tvUserName.findViewById(R.id.tvUserName);
        tvName = tvName.findViewById(R.id.tvName);
        tvPriority = tvPriority.findViewById(R.id.tvPriority);
        tvPri = tvPri.findViewById(R.id.tvPri);

        btFixInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, FixInfoActivity.class);
                activity.startActivity(intent);
                activity.finish();
            }
        });
        btManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, UserManagementActivity.class);
                activity.startActivity(intent);
                activity.finish();
            }
        });

    }
}
