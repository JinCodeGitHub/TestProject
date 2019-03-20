package com.example.dell.drawsateview.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * <pre>
 *     author : dell
 *     time   : 2019/03/11
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class SatesViewDraw extends SurfaceView implements SurfaceHolder.Callback {
    public SatesViewDraw(Context context) {
        super(context);
    }

    public SatesViewDraw(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SatesViewDraw(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {


    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
