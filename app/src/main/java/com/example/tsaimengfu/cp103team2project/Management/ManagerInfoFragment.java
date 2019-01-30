package com.example.tsaimengfu.cp103team2project.Management;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tsaimengfu.cp103team2project.R;
import com.example.tsaimengfu.cp103team2project.task.Common;
import com.example.tsaimengfu.cp103team2project.task.CommonTask;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.example.tsaimengfu.cp103team2project.task.Common.networkConnected;
import static com.example.tsaimengfu.cp103team2project.task.Common.showToast;


public class ManagerInfoFragment extends Fragment {
    private Button btFixInfo, btManagement;
    private TextView tvUserAccount, tvAccount, tvUserName, tvName, tvPriority, tvPri;
    private Activity activity;
    private CommonTask userTask;
    String userAccount, userName;
    User user = null;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getActivity() != null) {
            activity = getActivity();
        }
        View view = inflater.inflate(R.layout.activity_manager, container, false);
        show(view);
        getUsers();
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


    private void getUsers() {
        if (networkConnected(activity)) {
            String url = Common.URL + "/UserServlet";
//           List<User> users = null;
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("action", "findById");
            jsonObject.addProperty("id", 4);
            userTask = new CommonTask(url, jsonObject.toString());

            if (networkConnected(activity)) {
                try {
                    String jsonIn = userTask.execute().get();
                    user = new Gson().fromJson(jsonIn, User.class);
                    System.out.println(user);

                } catch (Exception e) {
//                        Log.e(TAG, e.toString());
                }
                if (user == null)
                {
                    Common.showToast(activity, R.string.text_NoReturn);
                } else {
                    tvName.setText(user.getUserName());
                    tvAccount.setText(user.getUserAccount());
                }
            }
        } else {
                Common.showToast(activity, R.string.text_NoNetwork);
        }
    }

}
