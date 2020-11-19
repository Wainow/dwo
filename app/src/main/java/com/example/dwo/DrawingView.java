package com.example.dwo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class DrawingView extends View{
    private int pathIndex = 0;
    private ArrayList<Path> pathLists = new ArrayList<>();
    private ArrayList<Paint> paintLists = new ArrayList<>();
    private float startX = 0F;
    private float startY = 0F;
    private int BackgroundColor = Color.WHITE;
    private int GeneralColor = Color.parseColor("#000000");
    private float sizePen = 3F;

    public float getSizePen() {
        return sizePen;
    }

    public void setSizePen(float sizePen) {
        this.sizePen = sizePen;
    }

    public int getGeneralColor() {
        return GeneralColor;
    }

    public void setGeneralColor(int generalColor) {
        GeneralColor = generalColor;
    }

    public int getBackgroundColor() {
        return BackgroundColor;
    }

    @Override
    public void setBackgroundColor(int backgroundColor) {
        BackgroundColor = backgroundColor;
    }

    public DrawingView(Context context) {
        super(context);
        pathLists.add(new Path());
        paintLists.add(createPaint());
        pathIndex++;
    }

    public DrawingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private Paint createPaint() {
        Paint paint = new Paint();
        paint.setColor(GeneralColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(sizePen);

        return paint;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(BackgroundColor);

        for (int index = 0; index < pathIndex; index++) {
            Path path = pathLists.get(index);
            Paint paint = paintLists.get(index);

            canvas.drawPath(path, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                updateIndex(createPath(event));
                break;
            case MotionEvent.ACTION_MOVE:
                float x = event.getX();
                float y = event.getY();
                Path path = pathLists.get(pathIndex - 1);;
                path.lineTo(x, y);
                break;
            default:
                break;
        }
        // Invalidate the whole view. If the view is visible.
        invalidate();
        return true;
    }

    public Path createPath(MotionEvent event){
        Path path = new Path();
        startX = event.getX();
        startY = event.getY();

        path.moveTo(startX, startY);
        return path;
    }

    public void updateIndex(Path path){
        pathIndex = pathLists.size();
        pathLists.add(path);
        paintLists.add(createPaint());
        pathIndex++;
    }
}
