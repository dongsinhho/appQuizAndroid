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


    CardView btKHTN, btKHXH, btKTChung, btDvdg, btThethao;
    //FirebaseDatabase database;
    //DatabaseReference questions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_play);

        //database = FirebaseDatabase.getInstance();
        //questions = database.getReference("Questions");

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

}