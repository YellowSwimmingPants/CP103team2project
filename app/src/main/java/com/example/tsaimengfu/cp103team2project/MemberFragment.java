package com.example.tsaimengfu.cp103team2project;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MemberFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handleView();
        recyclerView.setAdapter(new PlayerAdapter(this, getFriend()));


    }
    private class PlayerAdapter extends RecyclerView.Adapter<PlayerlistAdapter.MyViewHolder> {
        Context context;
        List<Playerlist> playerlists;

        public PlayerAdapter(Context context, List<Playerlist> Playerlist) {
            this.context = context;
            this.playerlists = Playerlist;
        }

        @Override
        public int getItemCount() {
            return playerlists.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView tvName;

            public MyViewHolder(View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.imageView);
                tvName = itemView.findViewById(R.id.tvName);
            }
        }
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View itemView = layoutInflater.inflate(R.layout.item_view, viewGroup, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull final MyViewHolder viewHolder, int i) {
            final Playerlist friend = playerlists.get(i);
//          MyViewHolder myViewHolder = (MyViewHolder) viewHolder;
            viewHolder.imageView.setImageResource(playerlists.getImageId());
            viewHolder.tvName.setText(friend.getName());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Animation animation = getShakeAnimation();
                    v.startAnimation(animation);
//                  Toast.makeText(context,friend.getName(),Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(MemberFragment.this, MemberFragment.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("imageView",friend.getImageId());
                    bundle.putString("tvName",friend.getName());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

            });
        }
    }

    private void handleView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    /* 建立搖晃動畫，將位移動畫重複且快速播放數次即可達到搖晃效果 */
    private TranslateAnimation getShakqeAnimation() {
        TranslateAnimation shakeAnimation =
                new TranslateAnimation(0, 10, 0, 0);
        shakeAnimation.setDuration(1000);
        /* 設定CycleInterpolator特效以重複播放7次 */
        CycleInterpolator cycleInterpolator = new CycleInterpolator(7);
        shakeAnimation.setInterpolator(cycleInterpolator);
        return shakeAnimation;
    }

    private List<Playerlist> getFriend(){
        List<Playerlist> playerlists = new ArrayList<>();
        playerlists.add(new Playerlist(R.drawable.boy, "boy"));
        playerlists.add(new Playerlist(R.drawable.girl, "girl"));
        return Playerlist;
    }



}
