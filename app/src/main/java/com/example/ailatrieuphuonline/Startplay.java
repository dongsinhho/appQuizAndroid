package com.example.ailatrieuphuonline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;

public class Startplay extends AppCompatActivity {

    private CountDownTimer countDownTimer;
    FirebaseDatabase database;
    DatabaseReference questions;
    private TextView tvCount;
    private int count = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startplay);

        database = FirebaseDatabase.getInstance();
        questions = database.getReference("Questions");
        tvCount = findViewById(R.id.tvCount);
        tvCount.setText(count+"");

        Bundle extra = getIntent().getExtras();
        if(extra != null) {
            Common.modeId = extra.getString("numbermodeplay");
        }
//        switch (modeplay) {
//            case 1: { Common.modeId = modeplay; break; }
//            case 2: { Common.questionList = Common.questionListNormal; break; }
//            case 3: { Common.questionList = Common.questionListHard; break; }
//        }
        loadQuestion(Common.modeId);

    }

    private void loadQuestion(String modeId) {

        //clear list if have old question
        if (Common.questionList.size() > 0)
            Common.questionList.clear();
//        Collections.shuffle(Common.questionListEasy);
//        Collections.shuffle(Common.questionListNormal);
//        Collections.shuffle(Common.questionListHard);
        //questions.orderByChild("levelId").equalTo(modeId)
        //questions.child("levelId").orderByValue().equalTo(modeId)
        questions.orderByChild("levelId")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                            Question ques = postSnapshot.getValue(Question.class);
                            //int check = Integer.parseInt(ques.levelId);
                            Common.questionList.add(ques);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        Collections.shuffle(Common.questionList); //random
    }

    @Override
    protected void onResume() {
        super.onResume();

        countDownTimer = new CountDownTimer(4000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                // TODO Auto-generated method stub
                count--;
                tvCount.setText(count + "");
            }

            @Override
            public void onFinish() {
                // TODO Auto-generated method stub
                Intent intent = new Intent(Startplay.this, PlayActivity.class);
                startActivity(intent);
                countDownTimer.cancel();
                //finish();
            }
        }.start();
    }
}