package com.jjws.custom.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.jjws.testanim.R;

/**
 * Created by sk on 16-7-13.
 */
public class PorterDuffModeView extends View{

    private Paint mPaint;
    private PorterDuff.Mode mMode = PorterDuff.Mode.DST_ATOP;

    private Bitmap b1, b2;

    public PorterDuffModeView(Context context) {
        this(context, null);
    }

    public PorterDuffModeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PorterDuffModeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }


    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setAntiAlias(true);

        b1 = BitmapFactory.decodeResource(getResources(), R.drawable.blue);
        b2 = BitmapFactory.decodeResource(getResources(), R.drawable.green);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Rect rect1 = new Rect(10, 10, 160, 160);
        RectF rectf1 = new RectF(rect1);
        Rect rect2 = new Rect(100, 100, 210, 210);
        RectF rectf2 = new RectF(rect2);



        Bitmap bp = getDesBitmap();

        canvas.drawBitmap(bp, rect1.left, rect1.top, mPaint);


    }

    private Bitmap getDesBitmap() {
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        Bitmap bp = Bitmap.createBitmap(b1, 0, 0, 400, 300);
        p.setColor(Color.GREEN);
        Canvas c = new Canvas(bp);
        c.drawRect(0, 0, 200, 150, p);


        Bitmap bp2 = Bitmap.createBitmap(b2, 0, 0, 400, 300);
        p.setColor(Color.RED);
        p.setXfermode(new PorterDuffXfermode(mMode));
        c.drawBitmap(bp2, 100, 100, p);

//        c.save();
//        c.translate(200, 150);
//        Bitmap bp2 = Bitmap.createBitmap(b2, 0, 0, 200, 100);
//        p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
//        c.drawBitmap(bp2, 0, 0, p);
//        c.restore();

        return bp;
    }
}
