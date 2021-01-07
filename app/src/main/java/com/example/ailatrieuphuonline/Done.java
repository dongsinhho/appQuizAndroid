package com.example.ailatrieuphuonline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class Done extends AppCompatActivity {

    Button btTryAgain, btOut;
    TextView tvScorce,tvCorrect;

    FirebaseDatabase database;
    DatabaseReference question_score;
    DatabaseReference Users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);
        final MediaPlayer player = MediaPlayer.create(this, R.raw.score);
        final MediaPlayer player2 = MediaPlayer.create(this, R.raw.click);
        player.start();

        database = FirebaseDatabase.getInstance();
        question_score = database.getReference("Question_Scorce");
        Users = database.getReference("Users");

        tvCorrect = findViewById(R.id.tvCorrect);
        tvScorce = findViewById(R.id.tvScore);
        btTryAgain = findViewById(R.id.btTryAgain);
        btOut = findViewById(R.id.btOut);

        btTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.stop();
                player.release();
                player2.start();
                Intent intent = new Intent(Done.this, ModePlayActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player2.start();
                Intent intent = new Intent(Done.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //Get data from bundle
        Bundle extra = getIntent().getExtras();
        if(extra != null) {
            int score = extra.getInt("SCORE");
            int totalQuestion = extra.getInt("TOTAL");
            int correctAnswer = extra.getInt("CORRECT");

            tvScorce.setText(String.format("SCORE : %d", score));
            tvCorrect.setText(String.format("PASSED : %d/%d", correctAnswer, totalQuestion));

            //Query post = question_score.child(String.format("%s_%s",Common.user.getUsername(),Common.modeId));
            //upload score to database
            question_score.child(String.format("%s_%s",Common.user.getUsername(),Common.modeId))
                    .setValue(new QuestionScore(Common.modeId, Common.user.getUsername(),
                            String.valueOf(score)));

            //FirebaseDatabase.getInstance().getReference("Users").updateChildren("scores", Common.user.getScores()+score);
            Users.child(Common.user.getId()).child("scores").setValue(Common.user.getScores()+score);

        }
    }
}