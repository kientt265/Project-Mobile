package com.example.salon.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.salon.R;

import java.util.ArrayList;
import java.util.List;

import app.num.numandroidpagecurleffect.PageCurlView;

public class Lookbook_LongActivity extends AppCompatActivity {
    PageCurlView pageCurlView;
    List<Integer> images;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lookbook_long);
        //pagecurl
        pageCurlView =findViewById(R.id.pagecurlView);
        images=new ArrayList<>();
        images.add(R.drawable.lookbook_long_1);
        images.add(R.drawable.lookbook_long_2);
        images.add(R.drawable.lookbook_long_3);
        images.add(R.drawable.lookbook_long_4);
        pageCurlView.setCurlView(images);
        pageCurlView.setCurlSpeed(600);



    }

}
