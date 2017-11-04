package com.contbook.spy_d.contbookdemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button ButtonLogin;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignup;
    private TextView textViewFrgtPwd;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth= FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

      /*  if(firebaseAuth.getCurrentUser() != null){
            //if user is already logged in
            //start profile activity directly
            finish();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        } */

        ButtonLogin = (Button) findViewById(R.id.ButtonLogin);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewSignup = (TextView) findViewById(R.id.textViewSignup);
        textViewFrgtPwd = (TextView) findViewById(R.id.textViewFrgtPwd);

        ButtonLogin.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);
        textViewFrgtPwd.setOnClickListener(this);
    }

    private void forgetpass()
    {
        FirebaseAuth.getInstance().sendPasswordResetEmail(editTextEmail.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            //Log.d(TAG, "Email sent.");
                            Toast.makeText(getApplicationContext(), "Email sent", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void UserLogin()
    {
        String email= editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();


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
        progressDialog.setMessage("Login...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();
                        if(task.isSuccessful()){
                            //start profile activity
                           /* finish();
                            startActivity(new Intent(getApplicationContext(),SearchActivity.class));
                            */
                           // Toast.makeText(LoginActivity.this, "login successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),SearchActivity.class));
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this, "Enter valid info", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if(v==ButtonLogin)
        {
        //perform login
            UserLogin();
        }
        if(v==textViewSignup)
        {
            //intent to signup activity
            finish();
            startActivity(new Intent(this, SignupActivity.class));
        }
        if(v==textViewFrgtPwd)
        {
            forgetpass();
        }

    }
}
