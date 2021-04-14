package com.example.trainingapp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactDetails extends AppCompatActivity {
    TextView name;
    TextView phone;
    private ImageView contactIV, callIV, messageIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        name=  findViewById(R.id.idTVName);
        phone= findViewById(R.id.idTVPhone);
        contactIV = findViewById(R.id.idIVContact);
        callIV = findViewById(R.id.idIVCall);
        messageIV = findViewById(R.id.idIVMessage);
        String name=getIntent().getStringExtra("name");
        String phone=getIntent().getStringExtra("number");

        Log.d("name",name);
        Log.d("phone",phone);
        callIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //calling a method to make a call.
                makeCall(phone);
            }
        });
        //on below line adding on click listner for our message image view.
        messageIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //calling a method to send message
                sendMessage(phone);
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

    private void makeCall(String contactNumber) {
        //this method is called for making a call.
        //on below line we are calling an intent to make a call.
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        //on below line we are setting data to it.
        callIntent.setData(Uri.parse("tel:" + contactNumber));
        //on below line we are checking if the calling permissions are grantedor not.
        if (ActivityCompat.checkSelfPermission(ContactDetails.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        //at last we are starting activity.
        startActivity(callIntent);
    }
}