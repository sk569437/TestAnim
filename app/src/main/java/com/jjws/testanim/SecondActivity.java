package com.jjws.testanim;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ClipDrawable;
import android.media.Image;
import android.os.Handler;
import android.os.Message;

import android.os.Bundle;
import android.text.ClipboardManager;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jjws.custom.view.PorterDuffView;
import com.jjws.custom.view.RoundImageDrawable;

import java.util.Random;

public class SecondActivity extends Activity {


    private PorterDuffView porterduff;
    private int mProgress = 0;
    private int mRandom = 1;
    private Random ram;
    private int mLevel = 0;

    private ClipDrawable cd;

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 1) {
                if(mProgress <= 100){
                    porterduff.setProgress(mProgress * 1.0f / 100);
                    if(mProgress >= 100) {
                        porterduff.setPorterDuffMode(false);
                    }
                    mProgress++;
                }else{

                }
            }else if(msg.what == 2) {

                if(cd != null) {
                    cd.setLevel(mLevel);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        porterduff = (PorterDuffView) findViewById(R.id.porterduff);

        ram = new Random(1000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(mProgress >= 0 && mProgress <= 100) {
                    try {
                        int ret = Math.abs(ram.nextInt()) % 300;
                        mRandom = ret > 0 ? ret : 10;
                        Thread.sleep(mRandom);
                        mHandler.sendEmptyMessage(1);
                    }catch (InterruptedException e) {

                    }
                }
            }
        }).start();

        porterduff.setProgress(0);


        ImageView image = (ImageView) findViewById(R.id.image);
        //String url = "http://img3.imgtn.bdimg.com/it/u=3534513302,27144379&fm=21&gp=0.jpg";
        //Glide.with(this).load(url).centerCrop().placeholder(R.drawable.icon_default).crossFade().into(image);
        Bitmap bp = BitmapFactory.decodeResource(getResources(), R.drawable.icon_default);
        final RoundImageDrawable rd = new RoundImageDrawable(bp, RoundImageDrawable.ROUND_DRAWABLE_TYPE_CIRCLE);
        image.setImageDrawable(rd);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int type = rd.getDrawableType();
                type++;
                if(type > RoundImageDrawable.ROUND_DRAWABLE_TYPE_ARC) {
                    type = RoundImageDrawable.ROUND_DRAWABLE_TYPE_CORNER;
                }

                rd.setDrawableType(type);


            }
        });

        //image.setBackgroundResource(R.drawable.image_clip);
//        cd = (ClipDrawable) image.getDrawable();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while(mLevel < 10000) {
//                    mLevel += 100;
//                    if(mLevel > 10000)
//                        mLevel = 10000;
//
//                    mHandler.sendEmptyMessage(2);
//
//                    try {
//                        Thread.sleep(100);
//                    }catch(InterruptedException e) {
//
//                    }
//                }
//            }
//        }).start();
//        cd.setLevel(cd.getLevel() + 1000);

    }
}
