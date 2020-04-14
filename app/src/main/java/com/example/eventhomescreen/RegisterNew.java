package com.example.eventhomescreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterNew extends AppCompatActivity {

    Button autoFill, Register;
    EditText Name, Email, Contact;
    String concertTitle, concertCategory, concertNo, concertTime,concertDate,concertLocation;
    int icon, concert_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new);
        Intent intent = getIntent();
        if (null != intent) { //Null Checking
            Bundle b = intent.getExtras();
            if (b != null) {
                concertNo = b.getString("key");
                concertTitle = b.getString("title");
                concertDate = b.getString("date");
                concertLocation = b.getString("location");
                concertTime = b.getString("time");
                concertCategory = b.getString("category");
                concert_no = Integer.parseInt(concertNo);
            }


            autoFill = (Button) findViewById(R.id.autofill);
            Register = (Button) findViewById(R.id.register);
            Name = (EditText) findViewById(R.id.registerName);
            Email = (EditText) findViewById(R.id.registerEmail);
            Contact = (EditText) findViewById(R.id.registerContact);
            autoFill.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Name.setText(Global.name);
                    Email.setText(Global.email);
                    Contact.setText(Global.contact);
                }
            });
            Register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //do something;
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference().child("Users").child(Global.registration_no).child("Concerts").child(concertNo);

                    myRef.child("Time").setValue(concertTime);
                    myRef.child("Date").setValue(concertDate);
                    myRef.child("Location").setValue(concertLocation);
                    myRef.child("Category").setValue(concertCategory);
                    myRef.child("Title").setValue(concertTitle);
                    myRef = database.getReference().child("Concerts").child("Registrations").child(concertTitle).child(Global.registration_no);
                    myRef.child("Name").setValue(Name.getText().toString());
                    myRef.child("Contact").setValue(Contact.getText().toString());
                    myRef.child("Email").setValue(Email.getText().toString());

                }
            });
        }
    }
}
