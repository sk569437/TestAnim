package com.jjws.testanim;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.jjws.custom.view.BezierView;
import com.jjws.custom.view.TwoBezierView;
import com.jjws.custom.view.ThreeBezierView;

public class FiveActivity extends ActionBarActivity {


    private TwoBezierView twobezier;
    private ThreeBezierView threeBezier;

    private BezierView bezier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);


        twobezier = (TwoBezierView) findViewById(R.id.twobezier);
        threeBezier = (ThreeBezierView) findViewById(R.id.threeBezier);
        bezier = (BezierView) findViewById(R.id.bezierView);
    }
}
