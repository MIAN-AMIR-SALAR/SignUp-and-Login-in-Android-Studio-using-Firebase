package com.example.theoryassignment1usingfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    EditText userName,email,password;
    Button signupBtn;
    TextView forLogin;
    FirebaseAuth fbAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        userName=findViewById(R.id.userName);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        forLogin=findViewById(R.id.createText);
        signupBtn=findViewById(R.id.signupBtn);
        fbAuth= FirebaseAuth.getInstance();
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email=email.getText().toString().trim();
                String Password=password.getText().toString().trim();
                if(TextUtils.isEmpty(Email)){
                    email.setError("EMAIL IS MISSING");
                    return;
                }
                if(TextUtils.isEmpty(Password)){
                    password.setError("PASSWORD IS MISSING");
                    return;
                }
                fbAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignUp.this, "ACCOUNT CREATED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),AfterSignUp.class));
                        }
                        else{
                            Toast.makeText(SignUp.this, "FAILED TO SIGNED UP", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
        forLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });


    }
}
