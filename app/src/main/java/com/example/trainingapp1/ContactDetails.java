package com.example.trainingapp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ContactDetails extends AppCompatActivity {
    TextView name;
    TextView phone;
    private ImageView contactIV, callIV, messageIV;
    private static final int REQUEST_CALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        name=  findViewById(R.id.idTVName);
        phone= findViewById(R.id.idTVPhone);
        contactIV = findViewById(R.id.idIVContact);
        callIV = findViewById(R.id.idIVCall);
        messageIV = findViewById(R.id.idIVMessage);
       String name_from_intent=getIntent().getStringExtra("name");
        String phone_from_intent=getIntent().getStringExtra("number");

        Log.d("name",name_from_intent);
        Log.d("phone",phone_from_intent);
        name.setText(name_from_intent);
        phone.setText(phone_from_intent);

        callIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call(phone_from_intent);
            }
        });
        //on below line adding on click listner for our message image view.
        messageIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //calling a method to send message
                sendMessage(phone_from_intent);
            }
        });



}
    private void sendMessage(String contactNumber) {
        //in this method we are calling an intent to send sms.
        //on below line we are passing our contact number.
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + contactNumber));
        intent.putExtra("sms_body", "Enter your messaage");
        startActivity(intent);
    }


    private void call(String number) {

        if (number.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(ContactDetails.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(ContactDetails.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        } else {
            Toast.makeText(this , "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }
}