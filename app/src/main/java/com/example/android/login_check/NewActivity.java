package com.example.android.login_check;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class NewActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
    }

    public void signOut(View view){
        mAuth= FirebaseAuth.getInstance();
        mAuth.signOut();
        startActivity(new Intent(NewActivity.this,MainActivity.class));
    }
}
