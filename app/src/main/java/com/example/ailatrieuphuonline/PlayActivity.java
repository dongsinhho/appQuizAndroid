package com.example.ailatrieuphuonline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener {

    final static long INTERVAL = 1000; // 1 sec
    final static long TIMEOUT = 15000; // 15 sec
    int timeValue = 0;
    MediaPlayer player1;

    CountDownTimer mCountDown;
    int index = 0, score = 0, thisQuestion = 0, totalQuestion, correctAnswer;
    int modeplay = 1;

    Button btA, btB, btC, btD;
    TextView tvScorce, tvCountDown, tvQuestion, tvthisQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        player1 = MediaPlayer.create(this,R.raw.onstart1);
        player1.start();
        player1.setLooping(true);

        tvScorce = findViewById(R.id.score);
        tvCountDown = findViewById(R.id.countdown);
        tvQuestion = findViewById(R.id.question);
        tvthisQuestion = findViewById(R.id.thisQuestion);
        Collections.shuffle(Common.questionList);

        btA = findViewById(R.id.answerA);
        btB = findViewById(R.id.answerB);
        btC = findViewById(R.id.answerC);
        btD = findViewById(R.id.answerD);


        btA.setOnClickListener(this);
        btB.setOnClickListener(this);
        btC.setOnClickListener(this);
        btD.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mCountDown.cancel();
        timeValue = 0;
        tvCountDown.setText(timeValue + "");
        if (index < totalQuestion) {  //vẫn còn câu hỏi trong danh sách
            Button clickedButton = (Button) v;
            if (clickedButton.getText().equals(Common.questionList.get(index).correctAnswer)) {
                score += 10;
                correctAnswer++;
                showQuestion(++index);
            } else {
                //sai
                player1.stop();
                player1.release();
                Intent intent = new Intent(PlayActivity.this, Done.class);
                Bundle dataSend = new Bundle();
                dataSend.putInt("SCORE", score);
                dataSend.putInt("TOTAL", totalQuestion);
                dataSend.putInt("CORRECT", correctAnswer);
                intent.putExtras(dataSend);
                startActivity(intent);
                finish();
            }
            tvScorce.setText(String.format("%d", score));
        }
    }

    private void showQuestion(int index) {
        if (index < totalQuestion) {
            thisQuestion++;
            tvthisQuestion.setText(String.format("%d/%d", thisQuestion, totalQuestion));
            tvCountDown.setText("0");

            tvQuestion.setText(Common.questionList.get(index).getTextQuestion());
            btA.setText(Common.questionList.get(index).getAnswerA());
            btB.setText(Common.questionList.get(index).getAnswerB());
            btC.setText(Common.questionList.get(index).getAnswerC());
            btD.setText(Common.questionList.get(index).getAnswerD());
            mCountDown.start();
        } else {
            player1.stop();
            player1.release();
            Intent intent = new Intent(PlayActivity.this, Done.class);
            Bundle dataSend = new Bundle();
            dataSend.putInt("SCORE", score);
            dataSend.putInt("TOTAL", totalQuestion);
            dataSend.putInt("CORRECT", correctAnswer);
            intent.putExtras(dataSend);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        totalQuestion = Common.questionList.size();
        mCountDown = new CountDownTimer(TIMEOUT, INTERVAL) {
            @Override
            public void onTick(long minis) {
                timeValue++;
                tvCountDown.setText(timeValue + "");
                Log.e("check", "savedInstanceState is null");
                if (timeValue == 16) {
                    Intent intent = new Intent(PlayActivity.this, Done.class);
                    Bundle dataSend = new Bundle();
                    dataSend.putInt("SCORE", score);
                    dataSend.putInt("TOTAL", totalQuestion);
                    dataSend.putInt("CORRECT", correctAnswer);
                    intent.putExtras(dataSend);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFinish() {
                mCountDown.cancel();
                //  showQuestion(++index);
                player1.stop();
                player1.release();
                Intent intent = new Intent(PlayActivity.this, Done.class);
                Bundle dataSend = new Bundle();
                dataSend.putInt("SCORE", score);
                dataSend.putInt("TOTAL", totalQuestion);
                dataSend.putInt("CORRECT", correctAnswer);
                intent.putExtras(dataSend);
                startActivity(intent);
                finish();
            }
        };
        showQuestion(index);
    }

}