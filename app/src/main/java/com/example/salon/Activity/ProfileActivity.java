package com.example.salon.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.salon.Domain.User;
import com.example.salon.Helper.NavigationManager;
import com.example.salon.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    public static FirebaseAuth mAuth;
    public User user;
    private TextView tvName, tvMo, tvAdd, tvDob;
    private String uid="PQmNbxSby5RIYe85WLHGkQQp4zp2";

    private DatabaseReference mDatabase;
    protected void onCreate(Bundle savedInstanceState) {

        FirebaseUser current = FirebaseAuth.getInstance().getCurrentUser();
        Toast.makeText(this, "" + current.getUid(), Toast.LENGTH_SHORT).show();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        Button btn_Signout= findViewById(R.id.btn_logout);
        BottomNavigationView bottomNav = findViewById(R.id.bnv_profile);

        tvName = findViewById(R.id.tv_name);
        tvMo = findViewById(R.id.tv_mobile);
        tvAdd = findViewById(R.id.tv_address);
        tvDob = findViewById(R.id.tv_dob);


        uid= current.getUid();

        TextView idTxt = findViewById(R.id.tv_id);

        idTxt.setText(current.getUid());

        mDatabase = FirebaseDatabase.getInstance().getReference();
        getUserData(uid);


        btn_Signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.action_noti) {
                    // Xử lý khi click vào Notifications
                    NavigationManager.navigateToNotifications(ProfileActivity.this);
                    // Không gọi finish() ở đây nếu bạn không muốn kết thúc Activity hiện tại
                } else if (id == R.id.action_home) {
                    // Xử lý khi click vào Home
                    NavigationManager.navigateToHome(ProfileActivity.this);
                } else if (id == R.id.action_cart) {
                    // Xử lý khi click vào Cart
                    NavigationManager.navigateToCart(ProfileActivity.this);
                }

                return true;
            }
        });
    }


    private void getUserData(String userID) {
        DatabaseReference userRef = mDatabase.child("userID").child(uid).child("Personal Information");

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // The dataSnapshot now contains the user data
                    User user = dataSnapshot.getValue(User.class);

                    Toast.makeText(ProfileActivity.this, user.toString(), Toast.LENGTH_SHORT).show();

                    // Do something with the user data, e.g., display it in TextViews
                    tvName.setText(user.getName());
                    tvMo.setText(user.getMobile());
                    tvAdd.setText(user.getAddress());
                    tvDob.setText(user.getDob());
                } else {
                    Toast.makeText(ProfileActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle the error, e.g., show an error message
                Toast.makeText(ProfileActivity.this, "Failed to retrieve user data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
