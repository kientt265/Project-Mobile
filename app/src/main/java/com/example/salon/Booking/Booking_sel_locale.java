package com.example.salon.Booking;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.salon.Activity.ShoppingActivity;
import com.example.salon.Helper.NavigationManager;
import com.example.salon.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.ref.Reference;

public class Booking_sel_locale extends AppCompatActivity {
    RelativeLayout location, location0, location1, location2, location3;
    TextView name0, name1, name2, name3, address0, address1, address2, address3;
    ImageButton back;
    FirebaseUser user = user_class.mAuth.getCurrentUser();
    String userID = user.getUid();
    private void setLocationOnClickListener(final RelativeLayout location, final TextView nameTextView, final TextView addressTextView) {
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Thực hiện hành động khi nhấn vào location ở đây
                String name = nameTextView.getText().toString();
                location.setBackgroundColor(getResources().getColor(R.color.grey));
                String address = addressTextView.getText().toString();


                // Sử dụng thông tin name và address ở đây (ví dụ: hiển thị hoặc xử lý thông tin)
                // Ví dụ:
                Toast.makeText(getApplicationContext(), "Selected Name: " + name + ", Address: " + address, Toast.LENGTH_SHORT).show();
                FirebaseDatabase database = user_class.Database.getInstance();

//                if (userID != null && userID == database.getReference().child("userID").child(userID).get().toString()) {
                if(userID != null){
                    DatabaseReference bookingRef = database.getReference().child("userID").child(userID).child("InfoBooking").child("Location name");
                    bookingRef.setValue(name);

                    bookingRef = database.getReference().child("userID").child(userID).child("InfoBooking").child("Address");
                    bookingRef.setValue(address);
                }
                Intent intent = new Intent(Booking_sel_locale.this, Booking_sel_schedule.class);
//                intent.putExtra("booking_info", user_class);
                startActivity(intent);
                finish(); // Đóng activity hiện tại nếu cần
//                DatabaseReference ref = dataBase.getReference("user_choice");
//                ref.child("booking_sel_employee").setValue("Employee Name");
//                ref.child("booking_sel_loc").setValue("Location Name");
//                ref.child("booking_sel_schedule").setValue("Schedule Time");
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_sel_locate);
        Toast.makeText(this, user_class.mAuth.getUid(), Toast.LENGTH_SHORT).show();


        name0 = findViewById(R.id.tv_locate_name_0);
        name1 = findViewById(R.id.tv_locate_name_1);
        name2 = findViewById(R.id.tv_locate_name_2);
        name3 = findViewById(R.id.tv_locate_name_3);
        address0 = findViewById(R.id.tv_locate_address_0);
        address1 = findViewById(R.id.tv_locate_address_1);
        address2 = findViewById(R.id.tv_locate_address_2);
        address3 = findViewById(R.id.tv_locate_address_3);
        location0 = findViewById(R.id.location_0);
        location1 = findViewById(R.id.location_1);
        location2 = findViewById(R.id.location_2);
        location3 = findViewById(R.id.location_3);

        setLocationOnClickListener(location0, name0, address0);
        setLocationOnClickListener(location1, name1, address1);
        setLocationOnClickListener(location2, name2, address2);
        setLocationOnClickListener(location3, name3, address3);
        back = findViewById(R.id.ibutton_next);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Thực hiện hành động khi nhấn vào imageButton ở đây
                Intent intent = new Intent(Booking_sel_locale.this, ShoppingActivity.class);
                startActivity(intent);
                finish(); // Đóng activity hiện tại nếu cần
            }
        });
        ImageButton imageButton = findViewById(R.id.ibutton_next);

    }
}
