package com.example.salon.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salon.Adapter.BestProductsAdapter;
import com.example.salon.Adapter.CategoryAdapter;
import com.example.salon.Domain.Category;
import com.example.salon.Helper.NavigationManager;
import com.example.salon.R;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.example.salon.Domain.Products;
import com.example.salon.Domain.Price;

import java.util.ArrayList;

public class ShoppingActivity extends BaseActivity{
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_page);
        bottomNavigationView = findViewById(R.id.nav_shopping_1);
        bottomNavigationView.setSelectedItemId(R.id.action_home);
        initBestProduct();
        initCategory();
        setVariable();
        BottomNavigationView nav_shopping_1 = findViewById(R.id.nav_shopping_1);
        nav_shopping_1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.action_noti) {
                    // Xử lý khi click vào Notifications
                    NavigationManager.navigateToNotifications(ShoppingActivity.this);
                    // Không gọi finish() ở đây nếu bạn không muốn kết thúc Activity hiện tại

                } else if (id == R.id.action_cart) {
                    // Xử lý khi click vào Cart
                    NavigationManager.navigateToCart(ShoppingActivity.this);
                } else if (id == R.id.action_acc) {
                    NavigationManager.navigateToProfile(ShoppingActivity.this);
                    // Xử lý khi click vào Settings
                }
                else if (id == R.id.action_his) {
                    NavigationManager.navigateToHistory(ShoppingActivity.this);
                    // Xử lý khi click vào Settings
                }

                return true;
            }
        });
    }

    private void setVariable() {
        ImageView btnSearch = findViewById(R.id.searchBtn);
        ImageView cartBtn = findViewById(R.id.cartBtn);
        ImageView backBtn = findViewById(R.id.backBtn);

        EditText edtText = findViewById(R.id.searchEdt);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = edtText.getText().toString();
                if (!text.isEmpty()) {
                    Intent intent = new Intent(ShoppingActivity.this, ListProductsActivity.class);
                    intent.putExtra("text", text);
                    intent.putExtra("isSearch", true);
                    startActivity(intent);
                }
            }
        });
        cartBtn.setOnClickListener(v -> startActivity(new Intent(ShoppingActivity.this, CartActivity.class)));

        //backBtn.setOnClickListener(v -> startActivity(new Intent(ShoppingActivity.this, HomeActivity.class)));

    }
        private void initBestProduct() {
            RecyclerView bestProductView = findViewById(R.id.bestProductView);
            ProgressBar progressBarBestProduct =findViewById(R.id.progressBarBestProduct);

            DatabaseReference myRef = database.getReference("Products");
            progressBarBestProduct.setVisibility(View.VISIBLE);
            ArrayList<Products> list = new ArrayList<>();
            Query query = myRef.orderByChild("BestProduct").equalTo(true);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot issue : snapshot.getChildren()) {
                            list.add(issue.getValue(Products.class));
                        }
                        if (list.size() > 0) {
                            bestProductView.setLayoutManager(new LinearLayoutManager(ShoppingActivity.this, LinearLayoutManager.HORIZONTAL, false));
                            RecyclerView.Adapter adapter = new BestProductsAdapter(list);
                            bestProductView.setAdapter(adapter);
                        }
                        progressBarBestProduct.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    private void initCategory() {
        ProgressBar progressBarCategory =findViewById(R.id.progressBarCategory);
        RecyclerView categoryView =findViewById(R.id.categoryView);
        DatabaseReference myRef = database.getReference("Category");
        progressBarCategory.setVisibility(View.VISIBLE);
        ArrayList<Category> list = new ArrayList<>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        list.add(issue.getValue(Category.class));
                    }
                    if (list.size() > 0) {
                        categoryView.setLayoutManager(new GridLayoutManager(ShoppingActivity.this, 4));
                        RecyclerView.Adapter adapter = new CategoryAdapter(list);
                        categoryView.setAdapter(adapter);
                    }
                    progressBarCategory.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
