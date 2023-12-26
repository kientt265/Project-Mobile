package com.example.salon.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salon.Domain.Order;
import com.example.salon.R;
import com.example.salon.Helper.ManagmentCart;
import com.google.firebase.database.FirebaseDatabase;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OrderActivity extends  BaseActivity{

    private DatabaseReference mDatabase;
    private ManagmentCart managmentCart;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        managmentCart = new ManagmentCart(this);

        TextView detailTxt= findViewById(R.id.detailTxt);
        TextView priceTxt= findViewById(R.id.priceTxt);

        EditText nameEdt= findViewById(R.id.edt_fullname);
        EditText emailEdt= findViewById(R.id.edt_email);
        EditText phoneEdt= findViewById(R.id.edt_phone);
        EditText addressEdt= findViewById(R.id.edt_address);

        Button btnOrder =findViewById(R.id.btn_order);
        Button btnCancel = findViewById(R.id.btn_cancel);

        Intent intent = getIntent();

        String intentDetail = intent.getStringExtra("detail");
        double price  = calculateCart();
        String finalresult = String.valueOf(price);

        detailTxt.setText(intentDetail);
        priceTxt.setText(finalresult);


        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEdt.getText().toString();
                String email = emailEdt.getText().toString();
                String phone = phoneEdt.getText().toString();
                String address = addressEdt.getText().toString();
                String detail = detailTxt.getText().toString();
                String price = priceTxt.getText().toString();


                // Check if fields are not empty before proceeding
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(address) ) {
                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    writeNewOrder(name, email,phone, address, detail, price );
                } else {
                    Toast.makeText(OrderActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private double calculateCart() {
        double tax;
        double percentTax = 0.02; //percent 2% tax
        double delivery = 10; // 10 Dollar

        tax = Math.round(managmentCart.getTotalFee() * percentTax * 100.0) / 100;

        double total = Math.round((managmentCart.getTotalFee() + tax + delivery) * 100) / 100;
        double itemTotal = Math.round(managmentCart.getTotalFee() * 100) / 100;

        return total;
    }
    public void writeNewOrder(String name, String email, String phone, String address, String detail, String price) {
        // Check if userID is not null before proceeding
            Order order = new Order(name, phone, email, address, price, detail);

            mDatabase.child("Orders").child(Order.getName()).setValue(order);
            Toast.makeText(OrderActivity.this, "Order added successfully", Toast.LENGTH_SHORT).show();

    }
}

