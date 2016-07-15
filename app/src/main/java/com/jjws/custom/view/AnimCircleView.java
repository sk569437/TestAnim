package com.jjws.custom.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.StringBuilderPrinter;
import android.view.View;

import com.jjws.model.Point;

/**
 * Created by sk on 16-7-8.
 */
public class AnimCircleView extends View {
    private Point startPoint;
    private Point endPoint;

    private int mRadius = 30;
    private Paint mPaint = null;
    private Point mCurrentPoint;

    public AnimCircleView(Context context) {
        super(context);
        init();
    }

    public AnimCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AnimCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(mCurrentPoint == null) {
            mCurrentPoint = new Point(mRadius, mRadius);

            drawCircle(canvas);

            startAnimation();
        }else{
            drawCircle(canvas);
        }

    }

    private void drawCircle(Canvas canvas) {


        canvas.drawCircle(mCurrentPoint.getPx(), mCurrentPoint.getPy(), mRadius, mPaint);
    }

    private void startAnimation() {
        Point startp = new Point(mRadius, mRadius);
        Point endp = new Point(getWidth() - mRadius, getHeight() - mRadius);

        ValueAnimator va = ValueAnimator.ofObject(new PointTypeEvaluator(), startp, endp);
        va.setDuration(4000);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mCurrentPoint = (Point)valueAnimator.getAnimatedValue();
                invalidate();
            }
        });

        //colorAnimator
        ObjectAnimator oa = ObjectAnimator.ofObject(this, "color", new ColorEvaluator(), "#ff00ff", "#00ffff");
        oa.setDuration(4000);
        oa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                String color = (String) animation.getAnimatedValue();
                mPaint.setColor(Color.parseColor(color));
                //invalidate();
            }
        });

        AnimatorSet set = new AnimatorSet();
        set.play(va).with(oa);
        set.setDuration(4000);
        set.start();

    }


    public void reset() {
        mCurrentPoint = null;
        requestLayout();
    }


    class PointTypeEvaluator implements TypeEvaluator<Point> {
        float a = 1f;
        float b = 1f;
        float c = 0f;

        public PointTypeEvaluator() {
        }

        @Override
        public Point evaluate(float v, Point point, Point t1) {

            double tx = t1.getPx() - point.getPx();
            double ty = t1.getPy() - point.getPy();

            double x = a*tx*tx+b*ty+c;
            double y = a*ty*ty+b*ty+c;

            double sx = tx*v;
            double sy = ty*v;

            int curx = (int)(sx) + point.getPx();
            int cury = (int)(sy) + point.getPy();
            Point p = new Point(curx, cury);
            return p;
        }
    }

    class ColorEvaluator implements TypeEvaluator{
        private int mCurrentRed = -1;
        private int mCurrentBlue = -1;
        private int mCurrentGreen = -1;

        @Override
        public Object evaluate(float fraction, Object startValue, Object endValue) {
            String start = (String) startValue;
            String end = (String) endValue;

            int startred = Integer.parseInt(start.substring(1, 3), 16);
            int startgreen = Integer.parseInt(start.substring(3, 5), 16);
            int startblue = Integer.parseInt(start.substring(5, 7), 16);

            int endred = Integer.parseInt(end.substring(1,3), 16);
            int endgreen = Integer.parseInt(end.substring(3,5), 16);
            int endblue = Integer.parseInt(end.substring(5,7), 16);

            if(mCurrentRed == -1) {
                mCurrentRed = startred;
            }
            if(mCurrentGreen == -1) {
                mCurrentGreen = startgreen;
            }
            if(mCurrentBlue == -1) {
                mCurrentBlue = startblue;
            }

            int diffred = Math.abs(endred - startred);
            int diffgreen = Math.abs(endgreen - startgreen);
            int diffblue = Math.abs(endblue - startblue);
            int diff = diffred + diffgreen + diffred;

            if(mCurrentRed != endred) {
                mCurrentRed = getCurrentColor(startred, endred, diff, 0, fraction);
            }
            else if(mCurrentGreen != endgreen) {
                mCurrentGreen = getCurrentColor(startgreen, endgreen, diff, diffred, fraction);
            }
            else if(mCurrentBlue != endblue) {
                mCurrentBlue = getCurrentColor(startblue, endblue, diff, diffred+diffgreen, fraction);
            }


            String color = getColorString(mCurrentRed, mCurrentGreen, mCurrentBlue);

            return color;
        }

        private String getHexString(int c) {

            String hex = Integer.toHexString(c);

            if(hex.length() == 1) {
                hex = "0" + hex;
            }
            return hex;
        }
        private String getColorString(int red, int green, int blue){
            String r =getHexString(red);
            String g = getHexString(green);
            String b = getHexString(blue);

            return "#" + r + g + b;
        }

        private int getCurrentColor(int start, int end, int diff, int offset, float fraction) {
            int current = 0;

            if(start > end) {
                current = (int)(start - (fraction*diff - offset));
                if(current < end) {
                    current = end;
                }
            } else {
                current = (int)(start + (fraction * diff - offset));
                if(current > end) {
                    current = end;
                }
            }

            return current;
        }
    }
}
