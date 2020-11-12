package com.example.ailatrieuphuonline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;

public class ModePlayActivity extends AppCompatActivity {

    Button btHard;
    Button btNormal;
    Button btEasy;
    FirebaseDatabase database;
    DatabaseReference questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_play);

        database = FirebaseDatabase.getInstance();
        questions = database.getReference("Questions");

        loadQuestion(Common.modeId);
        btHard = findViewById(R.id.hard);
        btNormal = findViewById(R.id.normal);
        btEasy = findViewById(R.id.easy);

        loadQuestion(Common.modeId);
        btHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModePlayActivity.this, PlayActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void loadQuestion(String modeId) {

        //clear list if have old question
        if (Common.questionList.size()>0)
            Common.questionList.clear();
        Collections.shuffle(Common.questionList);
        questions.orderByChild("modeId").equalTo(modeId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot postSnapshot : snapshot.getChildren())
                        {
                            Question ques = postSnapshot.getValue(Question.class);
                            Common.questionList.add(ques);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        Collections.shuffle(Common.questionList); //random
    }
}