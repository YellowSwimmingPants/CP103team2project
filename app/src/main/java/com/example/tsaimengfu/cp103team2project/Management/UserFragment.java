package com.example.tsaimengfu.cp103team2project.Management;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.example.tsaimengfu.cp103team2project.Management.User;
import com.example.tsaimengfu.cp103team2project.R;

import java.util.ArrayList;
import java.util.List;


public class UserFragment extends Fragment {
    Activity activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        if (getActivity() != null) {
            activity = getActivity();
        }
        View view = inflater.inflate(R.layout.fragment_managers, container, false);
        handleViews(view);
        return view;
    }

    private class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {
        Context context;
        List<User> users;

        public UserAdapter(Context context, List<User> users) {
            this.context = context;
            this.users = users;
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
            final User user = users.get(i);
            myViewHolder.tvUserAccount.setText(user.getUserAccount());
            myViewHolder.tvUserName.setText(user.getUserName());
        }

        @Override
        public int getItemCount() {
            return users.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tvUserAccount, tvUserName;
            Switch swPriority;

            public MyViewHolder(View itemView) {
                super(itemView);
                tvUserAccount = itemView.findViewById(R.id.tvUserAccount);
                tvUserName = itemView.findViewById(R.id.tvUserName);
                swPriority = itemView.findViewById(R.id.swPriority);
            }
        }

    }

    private void handleViews(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(new UserAdapter(activity, getUsers()));
    }

    private List<User> getUsers(){
        List<User> users = new ArrayList<>();
        users.add(new User(2,"apple","Mark",true));
        return users;
    }

}
