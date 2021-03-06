package com.example.android.login_check;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myref;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authstateListener;

    private EditText emailText;
    private EditText passText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailText= (EditText) findViewById(R.id.userEmail);
        passText= (EditText) findViewById(R.id.userPass);

        mAuth=FirebaseAuth.getInstance();

        authstateListener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser()!=null){
                    Intent intent = new Intent(MainActivity.this,NewActivity.class);
                    startActivity(intent);
                }
            }
        };
    }


    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(authstateListener);
    }

    public void loginButtonClicked(View view) {
        String email= emailText.getText().toString();
        String pass =passText.getText().toString();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Enter both Values",Toast.LENGTH_LONG).show();
        }

        else{
            mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(!task.isSuccessful()){
                        Toast.makeText(MainActivity.this,"Incorrect Username or password",Toast.LENGTH_LONG).show();

                    }
                }
            });
        }
    }
}
