package com.example.ailatrieuphuonline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
        btDvdg = findViewById(R.id.dovuidangian);
        btThethao = findViewById(R.id.thethao);
        btKHTN = findViewById(R.id.khoahoctunhien);
        btKHXH = findViewById(R.id.khoahocxahoi);
        btKTChung = findViewById(R.id.ktchung);

//        loadQuestion(Common.modeId);
        btDvdg.setOnClickListener(new View.OnClickListener() {
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
        btThethao.setOnClickListener(new View.OnClickListener() {
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
        btKHTN.setOnClickListener(new View.OnClickListener() {
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
        btKHXH.setOnClickListener(new View.OnClickListener() {
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
        btKTChung.setOnClickListener(new View.OnClickListener() {
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