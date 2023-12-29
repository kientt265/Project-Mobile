package com.example.salon.Activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;

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

public class HistoryActivity extends BaseActivity{
    private DatabaseReference mDatabase;

    BottomNavigationView bottomNavigationView;
    String uid;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_his);

        FirebaseUser current = FirebaseAuth.getInstance().getCurrentUser();
        uid= current.getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        getUserData(uid);

        bottomNavigationView  = findViewById(R.id.bnv_his);
        bottomNavigationView.setSelectedItemId(R.id.action_his);
        BottomNavigationView bottomNav  = findViewById(R.id.bnv_his);



        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.action_noti) {
                    // Xử lý khi click vào Notifications
                    NavigationManager.navigateToNotifications(HistoryActivity.this);
                    // Không gọi finish() ở đây nếu bạn không muốn kết thúc Activity hiện tại
                } else if (id == R.id.action_home) {
                    // Xử lý khi click vào Home
                    NavigationManager.navigateToHome(HistoryActivity.this);
                } else if (id == R.id.action_cart) {
                    // Xử lý khi click vào Cart
                    NavigationManager.navigateToCart(HistoryActivity.this);
                }
                else if (id == R.id.action_acc) {
                    // Xử lý khi click vào Cart
                    NavigationManager.navigateToProfile(HistoryActivity.this);
                }

                return true;
            }
        });
    }

    private void getUserData(String userID) {
        DatabaseReference userRef = mDatabase.child("Orders").child(userID);
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Toast.makeText(HistoryActivity.this, dataSnapshot.toString(), Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle the error, e.g., show an error message
                Toast.makeText(HistoryActivity.this, "Failed to retrieve data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
