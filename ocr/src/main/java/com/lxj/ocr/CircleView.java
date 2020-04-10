package com.lxj.ocr;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;

public class CircleView extends View {

    private Paint mPaint;
    private Paint mPaint1;
    private Path mPath;

    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(R.color.tra));
        mPaint.setAntiAlias(true);

        mPaint1 = new Paint();
        mPaint1.setColor(getResources().getColor(R.color.white));
        mPaint1.setAntiAlias(true);
        mPaint1.setStrokeWidth(2);
        mPaint1.setStyle(Paint.Style.STROKE);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        int width = getWidth();
        int height = getHeight();

        Rect rect = new Rect(0, 0, width, height);

        canvas.clipRect(rect);

        mPath = new Path();
        mPath.addCircle(width / 2, height / 2, width / 2 - 10, Path.Direction.CCW);
        canvas.clipPath(mPath, Region.Op.DIFFERENCE);

        canvas.drawRect(rect, mPaint);

        canvas.restore();

        canvas.drawCircle(width / 2, height / 2, width / 2 - 10, mPaint1);
    }
}
