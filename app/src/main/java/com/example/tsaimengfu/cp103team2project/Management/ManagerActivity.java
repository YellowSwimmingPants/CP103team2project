package com.example.tsaimengfu.cp103team2project.Management;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tsaimengfu.cp103team2project.task.MyTask;
import com.example.tsaimengfu.cp103team2project.R;
import com.example.tsaimengfu.cp103team2project.task.Common;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import static com.example.tsaimengfu.cp103team2project.task.Common.networkConnected;


public class ManagerActivity extends AppCompatActivity {
    private Button btFixInfo, btManagement;
    private TextView tvUserAccount, tvAccount, tvUserName, tvName, tvPriority, tvPri;
    private static MyTask userTask;
    private Activity activity;
    List<User> users = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
//        getUsers();
//        showUser();
        show();
    }

    private void show() {
        btFixInfo = findViewById(R.id.btFixInfo);
        btManagement = findViewById(R.id.btManagement);
        tvUserAccount = findViewById(R.id.tvUserAccount);
        tvAccount = findViewById(R.id.tvAccount);
        tvUserName = findViewById(R.id.tvUserName);
        tvName = findViewById(R.id.tvName);
        tvPriority = findViewById(R.id.tvPriority);
        tvPri = findViewById(R.id.tvPri);

        btFixInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ManagerActivity.this, FixInfoActivity.class);
                startActivity(intent);
            }
        });
        btManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ManagerActivity.this, UserManagementActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showUser(){
        User user = null;
        tvName.setText(user.getUserName());
        tvAccount.setText(user.getUserAccount());
//        tvPri.setText(user.getPriority());
    }

    private List<User> getUsers() {
        if (networkConnected(activity)) {
            String url = Common.URL + "/UserServlet";
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("action","findById");
            userTask = new MyTask(url, jsonObject.toString());
            if (networkConnected(activity)) {
                try {
                    String jsonIn = userTask.execute().get();
                    Type listType = new TypeToken<List<User>>() {
                    }
                            .getType();
                    users = new Gson().fromJson(jsonIn, listType);
                } catch (Exception e) {
//                        Log.e(TAG, e.toString());
                }
                if (users == null || users.isEmpty()) {

                } else {

                }
            }
        } else {
//                showToast(this, R.string.text_NoNetwork);
        }
        return users;
    }





}
