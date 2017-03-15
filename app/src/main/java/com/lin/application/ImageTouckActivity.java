package com.lin.application;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class ImageTouckActivity extends AppCompatActivity implements View.OnTouchListener {
    private ImageView imageView;
    private Matrix matrix;
    private Matrix savematrix;
    /**
     * 不同状态表示
     */
    private static final int NONE = 0;
    private static final int DRAG = 0;
    private static final int ZOOM = 0;
    private int mode = NONE;
    private PointF startPointF;
    private PointF midPointF;
    private float oridis = 1f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_touck);
        init();
    }

    private void init() {
        imageView = (ImageView) findViewById(R.id.image);
        matrix = new Matrix();
        savematrix = new Matrix();
        startPointF = new PointF();
        midPointF = new PointF();
        imageView.setOnTouchListener(this);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ImageView imageView = (ImageView) v;
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            /****
             * 单指
             */
            case MotionEvent.ACTION_DOWN:
                matrix.set(imageView.getImageMatrix());
                savematrix.set(matrix);
                startPointF.set(event.getX(), event.getY());
                mode = DRAG;
                break;
            /***
             * 双指
             */
            case MotionEvent.ACTION_POINTER_DOWN:
                oridis = PointDistance(event);
                if (oridis > 10f) {
                    savematrix.set(matrix);
                    midPointF = getMiddle(event);
                    mode = ZOOM;
                }
                break;
            /***
             * 手指放开
             */
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                mode = NONE;
                break;
            /***
             * 滑动
             */
            case MotionEvent.ACTION_MOVE:
                if (mode == DRAG) {
                    matrix.set(savematrix);
                    matrix.postTranslate(event.getX() - startPointF.x, event.getY() - startPointF.y);
                } else if (mode == ZOOM) {
                    float newDist = PointDistance(event);
                    matrix.set(savematrix);
                    float scale = newDist / oridis;
                    matrix.postScale(scale, scale, midPointF.x, midPointF.y);
                }
                break;

        }
        imageView.setImageMatrix(matrix);
        return true;
    }

    /***
     * 获取触摸的两个点之间的距离
     * @param event
     * @return
     */
    private float PointDistance(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    /***
     * 获取触摸的两个点之间的距离
     * @param event
     * @return
     */
    private PointF getMiddle(MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        return new PointF(x / 2, y / 2);
    }
}
