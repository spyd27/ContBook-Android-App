package com.contbook.spy_d.contbookdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView TextViewLogout;
    private TextView textViewUserEmail;

    private EditText editTextSearch;
    private TextView textViewName;
    private TextView textViewCont;
    private TextView textViewFb;
    private TextView textViewLn;
    private TextView textViewGit;
    private TextView tName;
    private TextView tCont;
    private TextView tFb;
    private TextView tLn;
    private TextView tGit;
    private TextView tEmail;



    private Button buttonSearch;
    private FirebaseAuth firebaseAuth;

    private DatabaseReference databaseReference;
    //private ListView mDataList;


    public void searchUSer()
    {
        final String SearchText =  editTextSearch.getText().toString().trim();
        databaseReference = FirebaseDatabase.getInstance().getReference().child(SearchText);
        ValueEventListener postListener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI

                UpdateInfo post = dataSnapshot.getValue(UpdateInfo.class);

                textViewName.setText(post.getName());
                textViewUserEmail.setText(post.getEmail());
                textViewCont.setText(post.getCont());
                textViewFb.setText("www.facebook.com/"+post.getFb());
                textViewLn.setText("www.linkedin.com/"+post.getLn());
                textViewGit.setText("www.github.com/"+post.getGit());

                Linkify.addLinks(textViewUserEmail, Linkify.ALL);
                Linkify.addLinks(textViewCont, Linkify.ALL);
                Linkify.addLinks(textViewFb, Linkify.ALL);
                Linkify.addLinks(textViewLn, Linkify.ALL);
                Linkify.addLinks(textViewGit, Linkify.ALL);

                tName.setText("Username:");
                tEmail.setText("Email:");
                tCont.setText("Contact:");
                tFb.setText("Facebook:");
                tLn.setText("LinkedIn:");
                tGit.setText("GitHub:");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        databaseReference.addValueEventListener(postListener);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //DataSnapshot dataSnapshot;

        firebaseAuth= FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()== null)
        {
            startActivity(new Intent(this, LoginActivity.class));
        }
        FirebaseUser user = firebaseAuth.getCurrentUser();

        editTextSearch= (EditText) findViewById(R.id.editTextSearch);
        TextViewLogout= (TextView) findViewById(R.id.TextViewLogout);
        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewCont = (TextView) findViewById(R.id.textViewCont);
        textViewFb = (TextView) findViewById(R.id.textViewFb);
        textViewLn = (TextView) findViewById(R.id.textViewLn);
        textViewGit = (TextView) findViewById(R.id.textViewGit);
        buttonSearch = (Button) findViewById(R.id.buttonSearch);



        tName = (TextView) findViewById(R.id.tName);
        tCont = (TextView) findViewById(R.id.tCont);
        tFb = (TextView) findViewById(R.id.tFb);
        tLn = (TextView) findViewById(R.id.tLn);
        tGit = (TextView) findViewById(R.id.tGit);
        tEmail = (TextView) findViewById(R.id.tEmail);


       // textViewUserEmail.setText("WELCOME TO CONTBOOK "+user.getEmail());
        buttonSearch.setOnClickListener(this);
        TextViewLogout.setOnClickListener(this);
        textViewGit.setOnClickListener(this);
        textViewFb.setOnClickListener(this);
        textViewLn.setOnClickListener(this);

       // String value= dataSnapshot.getValue();
    }

    @Override
    public void onClick(View v) {

        if(v==TextViewLogout)
        {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }
        if(v==buttonSearch)
        {
            //final String temp =  editTextSearch.getText().toString().trim();

            try
            {
                searchUSer();
            }
            catch (DatabaseException e)
            {
                Toast.makeText(this, "user doesn't exist", Toast.LENGTH_SHORT).show();
            }
            catch (Exception ee)
            {
                Toast.makeText(this, "user doesn't exist", Toast.LENGTH_SHORT).show();
            }


        }

    }
}
