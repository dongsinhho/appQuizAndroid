package com.example.ailatrieuphuonline;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StartActivity extends AppCompatActivity {

    Button login, register;

    FirebaseUser firebaseUser;
//    FirebaseDatabase users;


    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Loi","loicmmm");

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
//            user(firebaseUser.getUid(),firebaseUser.getUserfirebaseUser.getEmail(),firebaseUser);
// String id, String username, String email, String facebooklink, int score
            Intent intent = new Intent(StartActivity.this, MainActivity.class);
//            String userId = firebaseUser.getUid();
//            Bundle dataSend = new Bundle();
//            dataSend.putString("idUser", userId);
//            intent.putExtras(dataSend);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        if (!isOnline())
        {
            Log.d("Loi","loicmmm");
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(StartActivity.this);
            alertDialogBuilder.setTitle("Internet");
            alertDialogBuilder.setMessage("Bạn hãy kiểm tra lại kết nối mạng")
                    .setCancelable(false)
                    .setPositiveButton("Có",new DialogInterface.OnClickListener()  {
                        public void onClick(DialogInterface dialog,int id) {
                            if (isOnline())
                                Toast.makeText(getApplicationContext(), "Kết nối thành công", Toast.LENGTH_LONG).show();

                            else
                            {
                                Toast.makeText(getApplicationContext(), "Kết nối thất bại, sử dụng dữ liệu cũ", Toast.LENGTH_LONG).show();
                                Common.OnlineNetwork = false;
                                System.exit(0);
                            }
                        }
                    })
                    .setNegativeButton("Không",new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog,int id) {
                            dialog.cancel();
                            System.exit(0);
                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
        login = findViewById(R.id.login);
        //register = findViewById(R.id.register);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this, LoginActivity.class));
            }
        });

//        register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(StartActivity.this, RegisterActivity.class));
//            }
//        });

    }

    //Hàm kiểm tra kết nối internet của thiết bị
    private Boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if(ni != null && ni.isConnected()) {
            return true;
        }
        return false;
    }
}