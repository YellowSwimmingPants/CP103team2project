package com.example.tsaimengfu.cp103team2project.BillBoard;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tsaimengfu.cp103team2project.R;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.LinearLayoutManager.*;


public class BoardFragment extends Fragment {
    Activity activity;
    private TextView tvBoard, tvDayoff;
    private CalendarView cvEvent;
    private FloatingActionButton fabNewBoard;
    private RecyclerView rvBoards, rvDayoffs;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getActivity() != null) {
            activity = getActivity();
        }
        View view = inflater.inflate(R.layout.activity_home, container, false);
        show(view);
        showImage(view);
        return view;
    }

    private void show(View view) {
        tvBoard = view.findViewById(R.id.tvBoard);
        tvDayoff = view.findViewById(R.id.tvDayoff);
        cvEvent = view.findViewById(R.id.cvEvent);
        fabNewBoard = view.findViewById(R.id.fabNewBoard);
    }

    private class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.MyViewHolder> {
        Context context;
        List<Board> boards;

        public BoardAdapter(Context context, List<Board> boards) {
            this.context = context;
            this.boards = boards;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View itemView = layoutInflater.inflate(R.layout.boards_card_view, viewGroup, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
            final Board board = boards.get(i);
            myViewHolder.ivBoard.setImageResource(R.drawable.a3);
        }

        @Override
        public int getItemCount() {
            return boards.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView ivBoard;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                ivBoard = itemView.findViewById(R.id.ivBoard);
                ivBoard.setImageResource(R.drawable.a3);
            }
        }
    }

    private void showImage(View view){
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(activity, HORIZONTAL, false);

        RecyclerView rvBoard = view.findViewById(R.id.rvBoards);
        rvBoard.setLayoutManager(layoutManager);
        rvBoard.setAdapter(new BoardAdapter(activity, getBoards()));
    }

    private List<Board> getBoards() {
        List<Board> boards = new ArrayList<>();
        boards.add(new Board(3, "title", "massege"));
        boards.add(new Board(4, "title", "massege"));

        return boards;
    }

}
