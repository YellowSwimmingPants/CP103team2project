package com.example.tsaimengfu.cp103team2project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

public class BuildGameActivity extends AppCompatActivity {
    private EditText edGameName;
    private EditText edGameDate;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_game);
        initial();
    }

    private void initial() {
        edGameName = findViewById(R.id.edGameName);
        edGameDate = findViewById(R.id.edGameDate);
        String gameName = edGameName.getText().toString();
        String gameData = edGameDate.getText().toString();
        Bundle bundle = new Bundle();
        Game game = new Game(gameName, gameData);
        bundle.putSerializable("game", game);
        GameRecordFragment gameRecordFragment = new GameRecordFragment();
        gameRecordFragment.setArguments(bundle);

    }

    public void onCancelClick(View view) {
        finish();
    }
}
