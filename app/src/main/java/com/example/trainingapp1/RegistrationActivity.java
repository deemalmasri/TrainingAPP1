package com.example.trainingapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {
     EditText Name ,Email, Password, Phone ;
     Button btn_login_from_register ,btn_Register;
      FirebaseAuth firebaseAuth ;
      FirebaseFirestore firestore ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        firebaseAuth=FirebaseAuth.getInstance();
        firestore =FirebaseFirestore.getInstance();

        Name=findViewById(R.id.registerName);
        Email=findViewById(R.id.registerEmail);
        Password=findViewById(R.id.registerPassword);
        Phone=findViewById(R.id.registerPhone);
        btn_login_from_register=findViewById(R.id.btn_gotoLogin);
        btn_Register=findViewById(R.id.btn_register);

        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkField(Email.getText().toString()) && checkField(Password.getText().toString())){
                    firebaseAuth.createUserWithEmailAndPassword(Email.getText().toString(), Password.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    Toast.makeText(getApplicationContext(), "Account Created", Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = firebaseAuth.getCurrentUser();
                                    DocumentReference df = firestore.collection("Users").document(user.getUid());
                                    Map<String, Object> user_info = new HashMap<>();
                                    user_info.put("Name", Name.getText().toString());
                                    user_info.put("Email", Email.getText().toString());
                                    user_info.put("Phone", Phone.getText().toString());
                                    df.set(user_info);
                                    startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(RegistrationActivity.this, "Failled to creat account ", Toast.LENGTH_SHORT).show();

                        }
                    });
            }
            }
        }); //end register button on click method

        btn_login_from_register.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View v){
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));

            }

        });




    }//end of onCreate() method
    Boolean checkField(String field) {
        if (field != null) {
            return true;
        }
        return false;
    }
} //end of Registration class