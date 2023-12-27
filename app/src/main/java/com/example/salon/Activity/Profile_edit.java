package com.example.salon.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.salon.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Profile_edit extends AppCompatActivity {
    private String UID;
    TextInputEditText ed_name, ed_phone,ed_mail, ed_birthday, ed_address;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_edit);

        FirebaseUser current = FirebaseAuth.getInstance().getCurrentUser();

        ed_name=findViewById(R.id.edt_name);
        ed_birthday=findViewById(R.id.edt_dob);
        ed_address=findViewById(R.id.edt_address);
        ed_mail=findViewById(R.id.edt_email);
        ed_phone=findViewById(R.id.edt_mobile);


        Button btn_cancel = findViewById(R.id.btn_cancel);
        Button btn_save =findViewById(R.id.btn_save);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile_edit.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed_name.getText().toString().isEmpty()) {
                    Toast.makeText(Profile_edit.this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (ed_address.getText().toString().isEmpty()) {
                    Toast.makeText(Profile_edit.this, "address cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (ed_phone.getText().toString().isEmpty()) {
                    Toast.makeText(Profile_edit.this, " Phone cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (ed_birthday.getText().toString().isEmpty()) {
                    Toast.makeText(Profile_edit.this, "birthday cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (ed_mail.getText().toString().isEmpty()) {
                    Toast.makeText(Profile_edit.this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
                } else {
                    if(current != null){
                        String name = ed_name.getText().toString();
                        String address = ed_address.getText().toString();
                        String phone = ed_phone.getText().toString();
                        String birthday = ed_birthday.getText().toString();
                        String mail = ed_mail.getText().toString();
//                    Profile.id_profile.setText(id);
                        Toast.makeText(Profile_edit.this, "Profile info updated!", Toast.LENGTH_SHORT).show();
                        Intent Result_intent = new Intent();
//                    Result_intent.putExtra("newId", id);
                        Result_intent.putExtra("newName", name);
                        Result_intent.putExtra("newAddress", address);
                        Result_intent.putExtra("newPhone", phone);
                        Result_intent.putExtra("newBirthday", birthday);
                        Result_intent.putExtra("newMail",mail);
                        setResult(RESULT_OK, Result_intent);
                        finish();
                    } else Toast.makeText(Profile_edit.this, "Attempt to invoke virtual method .getUid() on a null object reference", Toast.LENGTH_SHORT).show();
                    }
            }
        });


    }
}
