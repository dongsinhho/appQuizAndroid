package com.example.ailatrieuphuonline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference users;
    FirebaseUser firebaseUser;
    FirebaseAuth auth;
    public MediaPlayer mediaPlayer;
    Thread thread;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        registerAlarm();
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String idCurrentUser = firebaseUser.getUid();
        users = database.getReference("Users");
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.original);
                    mediaPlayer.setLooping(true);
                    mediaPlayer.start();
                }
            });
            thread.start();

        final Button btPlay = findViewById(R.id.Play);
        Button btRanking = findViewById(R.id.Ranking);
        Button btSignout = findViewById(R.id.signout);


//        Bundle extra = getIntent().getExtras();
//        if(extra != null) {
//            String id = extra.getString("idUser");
//        }

        Query post = database.getInstance().getReference().child("Users").orderByChild("id").equalTo(idCurrentUser);

        post.addListenerForSingleValueEvent(new ValueEventListener()  {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot user : snapshot.getChildren()) {
                        // do something with the individual "user"
                        Common.user = user.getValue(User.class);
//                    Common.user();
//                    User user = Common.user;
                        // String check = Common.user.getFullname();
                        // Log.d("Hồ NGọc đông sinh "+ check, "Hồ Sinh");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //Toast.makeText(MainActivity.this, "Lỗi không thể get được user", Toast.LENGTH_SHORT).show();
                // Log.d("Hồ NGọc đông sinh", "onAuthStateChanged:signed_out");
            }
        });



        btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ModePlayActivity.class));
            }
        });

        btRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RankingActivity.class));
            }
        });

        btSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StartActivity.class);
                startActivity(intent);
                //  auth.signOut();
                //  Common.user.deleteUser();
                //finish();
            }
        });

    }

    private void registerAlarm() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,7);
        calendar.set(Calendar.MINUTE,33);
        calendar.set(Calendar.SECOND,0);

        Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)this.getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.pause();
        thread.interrupt();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (thread.isInterrupted())
        {
            thread.start();
            mediaPlayer.start();
        }
    }
}