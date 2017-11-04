package com.contbook.spy_d.contbookdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InfoActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextName;
    private EditText editTextCont;
    private EditText editTextFb;
    private EditText editTextLn;
    private EditText editTextGit;
    private EditText editTextEmail;

    private Button ButtonUpdate;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        firebaseAuth= FirebaseAuth.getInstance();

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextCont = (EditText) findViewById(R.id.editTextCont);
        editTextFb = (EditText) findViewById(R.id.editTextFb);
        editTextLn = (EditText) findViewById(R.id.editTextLn);
        editTextGit = (EditText) findViewById(R.id.editTextGit);

        ButtonUpdate = (Button) findViewById(R.id.ButtonUpdate);

        editTextEmail.setText(getIntent().getExtras().getString("email"));
        ButtonUpdate.setOnClickListener(this);
        editTextEmail.setTag(editTextEmail.getKeyListener());
        editTextEmail.setKeyListener(null);
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public void SaveInfo()
    {
        String email = editTextEmail.getText().toString().trim();

        String name= editTextName.getText().toString().trim();
        String cont= editTextCont.getText().toString().trim();
        String fb= editTextFb.getText().toString().trim();
        String ln= editTextLn.getText().toString().trim();
        String git= editTextGit.getText().toString().trim();

        UpdateInfo updateInfo = new UpdateInfo(email,name,cont,fb,ln,git);


       // databaseReference.setValue(updateInfo);
        databaseReference.child(name).setValue(updateInfo);
        Toast.makeText(this, "Information Updated...",Toast.LENGTH_LONG).show();

    }
    @Override
    public void onClick(View v) {
        if(v==ButtonUpdate)
        {
            SaveInfo();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }
    }
}
