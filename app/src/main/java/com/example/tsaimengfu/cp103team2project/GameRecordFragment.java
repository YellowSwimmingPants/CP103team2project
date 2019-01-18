package com.example.tsaimengfu.cp103team2project;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;


public class GameRecordFragment extends Fragment {
    private RecyclerView recyclerView;

    public GameRecordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_record, container, false);
        initial(view);
//        recyclerView.setAdapter(new GameAdapter(getActivity(), getGames()));
        return view;
    }

    private class GameAdapter extends RecyclerView.Adapter<GameAdapter.MyViewHolder> {
        Context context;
        List<Game> games;

        public GameAdapter(Context context, List<Game> games) {
            this.context = context;
            this.games = games;
        }

        @NonNull
        @Override
        public GameAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View itemView = layoutInflater.inflate(R.layout.game_player_item, viewGroup, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull GameAdapter.MyViewHolder myViewHolder, int position) {
            final Game game = games.get(position);
            myViewHolder.tvGameName.setText(game.getGameName());
            myViewHolder.tvGameDate.setText(game.getGameDate());
        }

        @Override
        public int getItemCount() {
            return games.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tvGameName;
            TextView tvGameDate;

            public MyViewHolder(View itemView) {
                super(itemView);
                tvGameName = itemView.findViewById(R.id.tvGameName);
                tvGameDate = itemView.findViewById(R.id.tvGameDate);
            }
        }
    }

    private void initial(View view) {
        Button btnNew = view.findViewById(R.id.btnNew);
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BuildGameActivity.class);
                startActivity(intent);
//                Fragment GamePlayerFragment = new Fragment();
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction fragmentTransaction =
//                        fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.content, GamePlayerFragment);
//                fragmentTransaction.commit();
            }
        });
    }

//    private List<Game> getGames() {
//        List<Game> games = null;
//        Bundle bundle = getArguments();
//        if (bundle != null) {
//            Object game = bundle.getSerializable("game");
//            if (game != null) {
//                games.add(new Game(game.toString(), game.toString()));
//            }
//        }
//        return games;
//    }

}
