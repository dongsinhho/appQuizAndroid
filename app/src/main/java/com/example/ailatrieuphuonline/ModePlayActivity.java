package com.example.ailatrieuphuonline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ModePlayActivity extends AppCompatActivity {

    CardView cvDvdg, cvThethao, cvKHTN, cvKHXH, cvKTChung;
    FirebaseDatabase database;
    DatabaseReference questions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_play);

        database = FirebaseDatabase.getInstance();
        questions = database.getReference("Questions");

//        loadQuestion(Common.modeId);
        cvDvdg = findViewById(R.id.dovuidangian);
        cvThethao = findViewById(R.id.thethao);
        cvKHTN = findViewById(R.id.khoahoctunhien);
        cvKHXH = findViewById(R.id.khoahocxahoi);
        cvKTChung = findViewById(R.id.ktchung);
        
//        loadQuestion(Common.modeId);
        cvDvdg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Common.modeId = "hard";
                Intent intent = new Intent(ModePlayActivity.this, Startplay.class);
                Bundle dataSend = new Bundle();
                dataSend.putString("numbermodeplay", "1");
                intent.putExtras(dataSend);
                startActivity(intent);
                finish();
            }
        });
        cvThethao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Common.modeId = "normal";
                Intent intent = new Intent(ModePlayActivity.this, Startplay.class);
                Bundle dataSend = new Bundle();
                dataSend.putString("numbermodeplay", "2");
                intent.putExtras(dataSend);
                startActivity(intent);
                finish();
            }
        });
        cvKHTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Common.modeId = "easy";
                Intent intent = new Intent(ModePlayActivity.this, Startplay.class);
                Bundle dataSend = new Bundle();
                dataSend.putString("numbermodeplay", "3");
                intent.putExtras(dataSend);
                startActivity(intent);
                finish();
            }
        });
        cvKHXH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Common.modeId = "easy";
                Intent intent = new Intent(ModePlayActivity.this, Startplay.class);
                Bundle dataSend = new Bundle();
                dataSend.putString("numbermodeplay", "4");
                intent.putExtras(dataSend);
                startActivity(intent);
                finish();
            }
        });
        cvKTChung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Common.modeId = "easy";
                Intent intent = new Intent(ModePlayActivity.this, Startplay.class);
                Bundle dataSend = new Bundle();
                dataSend.putString("numbermodeplay", "5");
                intent.putExtras(dataSend);
                startActivity(intent);
                finish();
            }
        });
    }

//    private void loadQuestion(String modeId) {
//
//        //clear list if have old question
//        if (Common.questionListEasy.size()>0)
//            Common.questionListEasy.clear();
//        if (Common.questionListNormal.size()>0)
//            Common.questionListNormal.clear();
//        if (Common.questionListHard.size()>0)
//            Common.questionListHard.clear();
////        Collections.shuffle(Common.questionListEasy);
////        Collections.shuffle(Common.questionListNormal);
////        Collections.shuffle(Common.questionListHard);
//        questions.orderByChild("modeId").equalTo(modeId)
//                .addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        for(DataSnapshot postSnapshot : snapshot.getChildren())
//                        {
//                            Question ques = postSnapshot.getValue(Question.class);
//                            int check = Integer.parseInt(ques.levelId);
//                            switch (check) {
//                                case 1: {
//                                    Common.questionListEasy.add(ques);
//                                    break;
//                                }
//                                case 2: {
//                                    Common.questionListNormal.add(ques);
//                                    break;
//                                }
//                                case 3: {
//                                    Common.questionListHard.add(ques);
//                                    break;
//                                }
//                            }
//
//                        }
//                    }
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//        Collections.shuffle(Common.questionListEasy); //random
//        Collections.shuffle(Common.questionListHard); //random
//        Collections.shuffle(Common.questionListNormal); //random
//    }
}