package com.example.tsaimengfu.cp103team2project.Management;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.example.tsaimengfu.cp103team2project.Management.Manager;
import com.example.tsaimengfu.cp103team2project.R;
import com.example.tsaimengfu.cp103team2project.task.Common;
import com.example.tsaimengfu.cp103team2project.task.CommonTask;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.example.tsaimengfu.cp103team2project.task.Common.networkConnected;


public class ManagerFragment extends Fragment {
    Activity activity;
    private CommonTask userTask;
    private RecyclerView rvManagers;
    private SwipeRefreshLayout srUsers;
    List<Manager> managers = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getActivity() != null) {
            activity = getActivity();
        }
        View view = inflater.inflate(R.layout.fragment_managers, container, false);
        handleViews(view);
        srUsers = view.findViewById(R.id.srUsers);
        srUsers.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srUsers.setRefreshing(true);
                getManagers();
                srUsers.setRefreshing(false);
            }
        });

        return view;
    }

    private class ManagerAdapter extends RecyclerView.Adapter<ManagerAdapter.MyViewHolder> {
        Context context;
        List<Manager> managers;

        public ManagerAdapter(Context context, List<Manager> managers) {
            this.context = context;
            this.managers = managers;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View itemView = layoutInflater.inflate(R.layout.managers_list_view, viewGroup, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
            final Manager manager = managers.get(i);
            myViewHolder.tvUserAccount.setText(manager.getUserAccount());
            myViewHolder.tvUserName.setText(manager.getUserName());
            myViewHolder.swPriority.setChecked(true);
        }

        @Override
        public int getItemCount() {
            return managers.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tvUserAccount, tvUserName;
            Switch swPriority;

            public MyViewHolder(View itemView) {
                super(itemView);
                tvUserAccount = itemView.findViewById(R.id.tvUserAccount);
                tvUserName = itemView.findViewById(R.id.tvUserName);
                swPriority = itemView.findViewById(R.id.swPriority);
                swPriority.setChecked(true);
            }
        }

    }

    private void handleViews(View view) {
        rvManagers = view.findViewById(R.id.recycleView);
        rvManagers.setLayoutManager(new LinearLayoutManager(activity));
        getManagers();
    }

    // 假資料
    private List<Manager> getManagers() {
//        List<Manager> managers = new ArrayList<>();
//        managers.add(new Manager(1, "psy", "John"));
//        return managers;
        String url = Common.URL + "/UserServlet";
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("action", "getAll");
//        jsonObject.addProperty("id", 2);
        userTask = new CommonTask(url, jsonObject.toString());
        if (networkConnected(activity)) {
            try {
                String jsonIn = userTask.execute().get();
                Type listType = new TypeToken<List<User>>() {
                }
                .getType();
                managers = new Gson().fromJson(jsonIn, listType);
            } catch (Exception e) {
//                        Log.e(TAG, e.toString());
            }
            if (managers == null || managers.isEmpty()) {

            } else {
                rvManagers.setAdapter(new ManagerAdapter(activity, managers));
            }
        } else {
//                showToast(this, R.string.text_NoNetwork);
        }
        return managers;
    }
}
