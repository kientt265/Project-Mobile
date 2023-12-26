package com.example.salon.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.salon.R;

public class LookBookActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lookbook);

        Button btnLookBookLong = findViewById(R.id.btn_lookbook);

        btnLookBookLong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(v.getContext(), Lookbook_LongActivity.class);
                startActivity(intent);
            }
        });
    }
}
