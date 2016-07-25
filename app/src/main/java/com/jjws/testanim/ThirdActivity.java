package com.jjws.testanim;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jjws.custom.view.AnimCircleView;

public class ThirdActivity extends Activity {


    private Button resetBtn;
    private AnimCircleView animCircleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        animCircleView = (AnimCircleView) findViewById(R.id.animview);

        resetBtn = (Button)findViewById(R.id.reset);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animCircleView.reset();
            }
        });
    }
}
