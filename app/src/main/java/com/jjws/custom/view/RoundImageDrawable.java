package com.jjws.custom.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.jjws.testanim.R;

/**
 * TODO: document your custom view class.
 */
public class RoundImageDrawable extends Drawable {

    public final static int ROUND_DRAWABLE_TYPE_CORNER = 0;
    public final static int ROUND_DRAWABLE_TYPE_CIRCLE = 1;
    public final static int ROUND_DRAWABLE_TYPE_OVAL = 2;
    public final static int ROUND_DRAWABLE_TYPE_ELLIPSE = 3;
    public final static int ROUND_DRAWABLE_TYPE_ARC = 4;

    private int mDrawableType = ROUND_DRAWABLE_TYPE_CORNER;              // TODO: use a default from R.string...
    private int mCornerRadius = 10;                                       // TODO: use a default from R.color...


    private Paint mPaint;
    private Bitmap mBitmap;
    private RectF mRectF;

    public RoundImageDrawable(Bitmap bp, int type) {

        mBitmap = bp;
        mDrawableType = type;

        Shader mShader = new BitmapShader(bp, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setShader(mShader);



    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);

        mRectF = new RectF(left, top, right, bottom);
    }

    @Override
    public void draw(Canvas canvas) {
        if(mDrawableType == ROUND_DRAWABLE_TYPE_CIRCLE) {
            float cx = getIntrinsicWidth()/2;
            float cy = getIntrinsicHeight()/2;

            float radius = Math.min(cx, cy);
            canvas.drawCircle(cx, cy, radius, mPaint);
        }else if(mDrawableType == ROUND_DRAWABLE_TYPE_OVAL) {
            RectF rf = new RectF(mRectF.left, mRectF.top, mRectF.right, mRectF.bottom);
            Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
            p.setColor(Color.BLUE);
            canvas.drawArc(rf, 0, 360, true, p);

            int offset = 5;
            rf = new RectF(mRectF.left + offset, mRectF.top + offset, mRectF.right - offset, mRectF.bottom - offset);
            canvas.drawArc(rf, 0, 360, true, mPaint);
        }else if(mDrawableType == ROUND_DRAWABLE_TYPE_ELLIPSE) {
            canvas.drawOval(mRectF, mPaint);
        }else if(mDrawableType == ROUND_DRAWABLE_TYPE_ARC) {
            Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
            int strike = 20;
            p.setStrokeWidth(strike);
            p.setColor(0x66a0a0a0);
            canvas.drawRoundRect(mRectF, 30, 30, p);

            int offset = strike;
            RectF rf = new RectF(mRectF.left + offset, mRectF.top + offset, mRectF.right - offset, mRectF.bottom - offset);
            canvas.drawRoundRect(rf, 25, 25, mPaint);
        }
        else {
            canvas.drawRoundRect(mRectF, 30, 30, mPaint);
        }

    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public int getIntrinsicHeight() {
        if(mBitmap != null && mBitmap.getHeight() > 0)
            return mBitmap.getHeight();

        return super.getIntrinsicHeight();
    }

    @Override
    public int getIntrinsicWidth() {
        if(mBitmap != null && mBitmap.getWidth() > 0) {
            return mBitmap.getWidth();
        }

        return super.getIntrinsicWidth();
    }

    private void init(AttributeSet attrs, int defStyle) {



    }

    public int getDrawableType() {
        return mDrawableType;
    }

    public void setDrawableType(int mDrawableType) {
        this.mDrawableType = mDrawableType;

        invalidateSelf();
    }
}
