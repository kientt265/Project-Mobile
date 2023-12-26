package com.example.salon.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import com.example.salon.Activity.CartActivity;
import com.example.salon.R;
import com.example.salon.Helper.ManagmentCart;

public class OrderActivity extends  BaseActivity{
    private CartActivity cartActivity;
    private ManagmentCart managmentCart;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        managmentCart = new ManagmentCart(this);

        TextView detailTxt= findViewById(R.id.detailTxt);
        TextView priceTxt= findViewById(R.id.priceTxt);
        Intent intent = getIntent();

        String intentDetail = intent.getStringExtra("detail");
        double price  = calculateCart();
        String finalresult = String.valueOf(price);

        detailTxt.setText(intentDetail);
        priceTxt.setText(finalresult);

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
}
