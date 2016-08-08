package com.jjws.custom.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class TwoBezierView extends View {

    private Paint mPaint;
    private Path mPath;
    private Point startPoint;
    private Point endPoint;

    private Point assistPoint;

    public TwoBezierView(Context context) {
        this(context, null);

    }

    public TwoBezierView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public TwoBezierView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPath = new Path();
        startPoint = new Point(100, 100);
        endPoint = new Point(400, 200);
        assistPoint = new Point(300, 300);

        mPaint.setDither(true);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPath.reset();

        mPath.moveTo(startPoint.x, endPoint.y);
        mPath.quadTo(assistPoint.x, assistPoint.y, endPoint.x, endPoint.y);

        canvas.drawPath(mPath, mPaint);
        canvas.drawPoint(assistPoint.x, assistPoint.y, mPaint);
    }

    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            assistPoint.x = (int) event.getX();
            assistPoint.y = (int)event.getY();
            invalidate();
            break;

        }

        return true;
    }

}
