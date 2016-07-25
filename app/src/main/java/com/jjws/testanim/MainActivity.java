package com.jjws.testanim;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Camera;
import android.graphics.Matrix;


import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zbar.lib.CaptureActivity;

public class MainActivity extends Activity {



    private Button rotateButton = null;

    private Button scaleButton = null;

    private Button alphaButton = null;

    private Button translateButton = null;

    private ImageView image = null;

    private TextView jnistr;
    private JNITest mJNITest = new JNITest();

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        rotateButton = (Button)findViewById(R.id.rotateButton);

        scaleButton = (Button)findViewById(R.id.scaleButton);

        alphaButton = (Button)findViewById(R.id.alphaButton);

        translateButton = (Button)findViewById(R.id.translateButton);

        image = (ImageView)findViewById(R.id.image);
        jnistr = (TextView) findViewById(R.id.jnistr);
        jnistr.setText(mJNITest.getNativeString("from java"));



        rotateButton.setOnClickListener(new RotateButtonListener());

        scaleButton.setOnClickListener(new ScaleButtonListener());

        alphaButton.setOnClickListener(new AlphaButtonListener());

        translateButton.setOnClickListener(

                new TranslateButtonListener());

    }

    class AlphaButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            //创建一个AnimationSet对象，参数为Boolean型，

            //true表示使用Animation的interpolator，false则是使用自己的

//            AnimationSet animationSet = new AnimationSet(true);
//
//            //创建一个AlphaAnimation对象，参数从完全的透明度，到完全的不透明
//
//            AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
//
//            //设置动画执行的时间
//
//            alphaAnimation.setDuration(600);
//
//            //将alphaAnimation对象添加到AnimationSet当中
//
//            animationSet.addAnimation(alphaAnimation);
//            animationSet.setInterpolator(new LinearOutSlowInInterpolator());
//
//            //使用ImageView的startAnimation方法执行动画
//
//
//            image.startAnimation(animationSet);

//            Animation a = AnimationUtils.loadAnimation(MainActivity.this, R.anim.alpha);
//            a.setFillEnabled(true);
//            a.setFillAfter(true);
//            image.startAnimation(a);


//            AnimatorSet set = new AnimatorSet();
//            ObjectAnimator oa = ObjectAnimator.ofFloat(image, "alpha", 0f, 1f);
//            oa.setDuration(200);
//            oa.setInterpolator(new FastOutSlowInInterpolator());
//            set.playTogether(oa);
//            set.start();

//            Animator a = AnimatorInflater.loadAnimator(MainActivity.this, R.anim.animator_alpha);
//
//            a.setTarget(image);
//            a.start();

            startActivity(new Intent(MainActivity.this, CaptureActivity.class));
        }

    }

    class RotateButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

//            AnimationSet animationSet = new AnimationSet(true);
//
//            //参数1：从哪个旋转角度开始
//
//            //参数2：转到什么角度
//
//            //后4个参数用于设置围绕着旋转的圆的圆心在哪里
//
//            //参数3：确定x轴坐标的类型，有ABSOLUT绝对坐标、RELATIVE_TO_SELF相对于自身坐标、RELATIVE_TO_PARENT相对于父控件的坐标
//
//            //参数4：x轴的值，0.5f表明是以自身这个控件的一半长度为x轴
//
//            //参数5：确定y轴坐标的类型
//
//            //参数6：y轴的值，0.5f表明是以自身这个控件的一半长度为x轴
//
//            RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
//
//                    Animation.RELATIVE_TO_SELF,0.5f,
//
//                    Animation.RELATIVE_TO_SELF,0.5f);
//
//            rotateAnimation.setDuration(1000);
//
//            animationSet.addAnimation(rotateAnimation);
//
//            image.startAnimation(animationSet);

            AnimatorSet set = new AnimatorSet();
            ObjectAnimator oa = ObjectAnimator.ofFloat(image, "rotationY", -360, 0);
            oa.setInterpolator(new OvershootInterpolator());

            Display display = getWindowManager().getDefaultDisplay();
            DisplayMetrics dm = new DisplayMetrics();
            display.getMetrics(dm);

            float startx = image.getWidth();
            float starty = image.getHeight();
            float tox = dm.widthPixels;
            float toy = dm.heightPixels;
            ObjectAnimator ob = ObjectAnimator.ofFloat(image, "scaleX", 1, 0.3f);
            ObjectAnimator oc = ObjectAnimator.ofFloat(image, "scaleY", 1, 0.3f);
            set.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                }
            });

            set.playTogether(oa, ob, oc);
            set.setDuration(2000);
            set.start();


        }

    }

    class ScaleButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            AnimationSet animationSet = new AnimationSet(true);

            //参数1：x轴的初始值

            //参数2：x轴收缩后的值

            //参数3：y轴的初始值

            //参数4：y轴收缩后的值

            //参数5：确定x轴坐标的类型

            //参数6：x轴的值，0.5f表明是以自身这个控件的一半长度为x轴

            //参数7：确定y轴坐标的类型

            //参数8：y轴的值，0.5f表明是以自身这个控件的一半长度为x轴

            final ScaleAnimation scaleAnimation = new ScaleAnimation(
                    1, 0.3f, 1, 0.3f,
                    Animation.RELATIVE_TO_SELF,0.5f,
                    Animation.RELATIVE_TO_SELF,0.5f);

            AlphaAnimation salphaAnimation = new AlphaAnimation(1, 0);
            salphaAnimation.setStartOffset(500);
            salphaAnimation.setDuration(100);


            scaleAnimation.setDuration(500);
            scaleAnimation.setFillAfter(false);
            scaleAnimation.setFillEnabled(true);
            scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {


                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            animationSet.addAnimation(scaleAnimation);
            //animationSet.addAnimation(salphaAnimation);


            animationSet.setFillEnabled(true);
            animationSet.setFillAfter(true);
            animationSet.setInterpolator(new LinearInterpolator());

            image.startAnimation(animationSet);

        }

    }

    class TranslateButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            AnimationSet animationSet = new AnimationSet(true);

            //参数1～2：x轴的开始位置

            //参数3～4：y轴的开始位置

            //参数5～6：x轴的结束位置

            //参数7～8：x轴的结束位置

//            TranslateAnimation translateAnimation =
//
//                    new TranslateAnimation(
//
//                            Animation.RELATIVE_TO_SELF,0f,
//
//                            Animation.RELATIVE_TO_SELF,0.5f,
//
//                            Animation.RELATIVE_TO_SELF,0f,
//
//                            Animation.RELATIVE_TO_SELF,0.5f);
//
//            translateAnimation.setDuration(1000);
//
//            animationSet.addAnimation(translateAnimation);
//
//            image.startAnimation(animationSet);

//            float centerx = image.getWidth()/2f;
//            float centery = image.getHeight()/2f;
//            Animation3d animation3d3d = new Animation3d(0, 90, centerx, centery, 310, true);
//            animation3d3d.setDuration(500);
//            animation3d3d.setInterpolator(new AccelerateInterpolator());
//            image.setAnimation(animation3d3d);

            startActivity(new Intent(MainActivity.this, SecondActivity.class));
            //overridePendingTransition(R.anim.rotate, 0);

        }

    }

    class Animation3d extends Animation {

        private Camera mCamera = null;

        private float mFromDegree, mToDegree, mCenterX, mCenterY, mDepthz;
        private boolean mReverse;

        public Animation3d(float fromDegree, float toDegree, float centerX, float centerY, float depthz, boolean reverse) {
            this.mFromDegree = fromDegree;
            this.mToDegree = toDegree;
            this.mCenterX = centerX;
            this.mCenterY = centerY;
            this.mDepthz = depthz;
            this.mReverse = reverse;
        }

        public Animation3d(Context context, AttributeSet attrs) {
            super(context, attrs);
        }


        @Override
        public void initialize(int width, int height, int parentWidth, int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
            mCamera = new Camera();
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);

            final Matrix matrix  = t.getMatrix();

            float fromdegree = mFromDegree;
            float degree = mFromDegree + (mToDegree - mFromDegree)* interpolatedTime;
            float centerx = mCenterX;
            float centery = mCenterY;
            final Camera camera = mCamera;

            camera.save();

            if(mReverse) {
                camera.translate(0f, 0f , mDepthz * interpolatedTime);
            }else {
                camera.translate(0,0,mDepthz*(1-interpolatedTime));
            }

            camera.rotateY(degree);

            camera.getMatrix(matrix);

            camera.restore();

            matrix.preTranslate(-centerx, -centery);
            matrix.postTranslate(centerx, centery);
        }


    }


}
