package com.epam.androidlab.task10_customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * This is a custom view, which draws a smiley.
 * It has custom attributes, which allow to customize colors of the smile parts’ (eyes, mouth, face).
 * You can change smile from the sad one to the happy one and vice versa by clicking on it.
 * The last smile type is remembered by the view itself.
 *
 * @author Elizabeth Gavina
 * @since 10.04.2018
 */
public class SmileyView extends View {

    private static final String STATE_KEY = "state";
    private static final String SUPER_STATE_KEY = "superState";

    public static final int HAPPY = 0;
    public static final int SAD = 1;

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private int eyesColor;
    private int mouthColor;
    private int faceColor;

    private int state;
    private int faceRadius;
    private int screenWidth;
    private int screenHeight;

    private GestureDetectorCompat gestureDetector;
    private GestureDetector.OnGestureListener gestureListener =
            new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onDown(@Nullable final MotionEvent e) {
                    changeSmileyState();
                    return true;
                }
            };

    /**
     * This constructor is used by the layout to construct SmileView
     * from a set of XML attributes.
     *
     * @param context context
     * @param attrs   an attribute set which contains attributes from view in layout
     */
    public SmileyView(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.SmileyView,
                0, 0);
        try {
            state = a.getInt(R.styleable.SmileyView_state, HAPPY);
            eyesColor = a.getColor(R.styleable.SmileyView_eyesColor, Color.BLACK);
            mouthColor = a.getColor(R.styleable.SmileyView_mouthColor, Color.WHITE);
            faceColor = a.getColor(R.styleable.SmileyView_faceColor, Color.GRAY);
        } finally {
            a.recycle();
        }
        initDetector();
    }

    private void initDetector() {
        gestureDetector = new GestureDetectorCompat(SmileyView.this.getContext(), gestureListener);
    }

    @Override
    protected void onDraw(@NonNull final Canvas canvas) {
        super.onDraw(canvas);
        screenWidth = getWidth();
        screenHeight = getHeight();

        drawFace(canvas);
        drawEyes(canvas);
        drawMouth(canvas);
    }

    /**
     * Defines paint and params of smiley's face and draws it on canvas.
     *
     * @param canvas where to draw
     */
    private void drawFace(@NonNull final Canvas canvas) {
        paint.setColor(faceColor);
        paint.setStyle(Paint.Style.FILL);

        int size = Math.min(screenHeight, screenWidth);
        int facePadding = 50;
        faceRadius = size / 2 - facePadding;
        int faceX = screenWidth / 2;
        int faceY = screenHeight / 2;

        canvas.drawCircle(faceX, faceY, faceRadius, paint);
    }

    /**
     * Defines paint and params of smiley's mouth(depending on smiley's state)
     * and draws it on canvas.
     *
     * @param canvas where to draw
     */
    private void drawMouth(@NonNull final Canvas canvas) {
        paint.setColor(mouthColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(15f);

        float lineX = screenWidth / 2 - faceRadius / 2;
        float mouthLength = faceRadius;
        float mouthHeight = faceRadius / 2;

        float lineY = 0;
        float startAngle = 0;
        switch (state) {
            case HAPPY:
                lineY = screenHeight / 2;
                startAngle = 20;
                break;
            case SAD:
                lineY = screenHeight / 2 + faceRadius / 3;
                startAngle = 200;
                break;
        }
        RectF mouth = new RectF(lineX, lineY, lineX + mouthLength, lineY + mouthHeight);
        canvas.drawArc(mouth, startAngle, 140, false, paint);
    }

    /**
     * Defines paint and params of smiley's mouth(depending on smiley's state)
     * and draws it on canvas.
     *
     * @param canvas where to draw
     */
    private void drawEyes(@NonNull final Canvas canvas) {
        float lineY = screenHeight / 2 - faceRadius / 3;
        float eyeWidth = faceRadius / 3;
        float eyeHeight = 0;
        float startAngle = 0;
        boolean useCenter = false;
        switch (state) {
            case HAPPY:
                paint.setColor(eyesColor);
                paint.setStyle(Paint.Style.FILL);
                startAngle = 180;
                eyeHeight = faceRadius / 3;
                useCenter = true;
                break;
            case SAD:
                paint.setColor(Color.BLACK);
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(15f);
                eyeHeight = faceRadius / 4;
                break;
        }

        float leftLineX = screenWidth / 2 - faceRadius / 2;
        RectF leftEye = new RectF(leftLineX,
                                  lineY,
                                  leftLineX + eyeWidth,
                                  lineY + eyeHeight);
        canvas.drawArc(leftEye, startAngle, 180, useCenter, paint);

        float rightLineX = screenWidth / 2 + faceRadius / 2;
        RectF rightEye = new RectF(rightLineX - eyeWidth,
                                   lineY,
                                   rightLineX,
                                   lineY + eyeHeight);
        canvas.drawArc(rightEye, startAngle, 180, useCenter, paint);
    }
    
    /**
     * Changes smiley's state from the sad one to the happy one
     * and vice versa.
     */
    private void changeSmileyState() {
        switch (state) {
            case SmileyView.SAD:
                state = SmileyView.HAPPY;
                break;
            case SmileyView.HAPPY:
                state = SmileyView.SAD;
                break;
        }
        invalidate();
    }

    @Override
    public boolean onTouchEvent(@Nullable final MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return performClick();
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putInt(STATE_KEY, state);
        bundle.putParcelable(SUPER_STATE_KEY, super.onSaveInstanceState());
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            this.state = bundle.getInt(STATE_KEY);
            state = bundle.getParcelable(SUPER_STATE_KEY);
        }
        super.onRestoreInstanceState(state);
    }
}
