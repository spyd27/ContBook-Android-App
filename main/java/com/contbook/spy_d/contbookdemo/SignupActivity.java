package com.contbook.spy_d.contbookdemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView TextViewSignin;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuth= FirebaseAuth.getInstance();

        progressDialog= new ProgressDialog(this);

       /* if(firebaseAuth.getCurrentUser() != null){
            //if user is already logged in
            //start profile activity directly
            finish();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        } */

        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword =  (EditText) findViewById(R.id.editTextPassword);
        TextViewSignin = (TextView) findViewById(R.id.TextViewSignin);

        buttonRegister.setOnClickListener(this);
        TextViewSignin.setOnClickListener(this);
    }

    private void RegisterUser()
    {
        final String email =  editTextEmail.getText().toString().trim();
        String password =  editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email))
        {
            //email is empty
            Toast.makeText(this, " Please enter Email id", Toast.LENGTH_SHORT).show();
            //return stops function
            return;
        }

        if(TextUtils.isEmpty(password))
        {
            //password is empty
            Toast.makeText(this, " Please enter password", Toast.LENGTH_SHORT).show();
            //return stops function
            return;
        }

        //if validation successful
        //progress dialog will come up
        progressDialog.setMessage("Registering user...");
        progressDialog.show();

        //creating user with fireAuth
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){
                            //display some message here
                            Toast.makeText(SignupActivity.this,"Successfully registered",Toast.LENGTH_LONG).show();
                            Intent i=new Intent(getApplicationContext(),InfoActivity.class);
                            i.putExtra("email",email);
                            startActivity(i);
                        }else{
                            //display some message here
                            Toast.makeText(SignupActivity.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }
    @Override
    public void onClick(View v) {
        if(v==buttonRegister) {
            RegisterUser();
        }
        if(v==TextViewSignin)
        {
            //intent sign in activity
            startActivity(new Intent(this,LoginActivity.class));
        }
    }
}
