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
        if (getActivity() != null) {
            activity = getActivity();
        }
        View view = inflater.inflate(R.layout.activity_manager, container, false);
        show(view);
        return view;


    }

    private void show(View view) {
        btFixInfo = view.findViewById(R.id.btFixInfo);
        btManagement = view.findViewById(R.id.btManagement);
        tvUserAccount = view.findViewById(R.id.tvUserAccount);
        tvAccount = view.findViewById(R.id.tvAccount);
        tvUserName = view.findViewById(R.id.tvUserName);
        tvName = view.findViewById(R.id.tvName);
        tvPriority = view.findViewById(R.id.tvPriority);
        tvPri = view.findViewById(R.id.tvPri);

        btFixInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FixInfoActivity.class);
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });
        btManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserManagementActivity.class);
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });

    }
}
