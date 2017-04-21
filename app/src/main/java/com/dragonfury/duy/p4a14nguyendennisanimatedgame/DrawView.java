package com.dragonfury.duy.p4a14nguyendennisanimatedgame;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.content.Context;
import android.graphics.Bitmap;

/**
 * Created by 1383504 on 4/19/2017.
 */
public class DrawView extends SurfaceView {

    private Bitmap heroBmp; // Declare space fir Bitmap called heroBmp, global scope
    private SurfaceHolder holder; //Declares space for a SurfaceHolder called holder
    private LoopThread loopThread; //Declares space for a LoopThread called loopThread
    private int x = 0;

    public DrawView(Context context) { // Constructor because it has the same name as the class
        super(context); //Calls View(context), Parent's constructor

        heroBmp = BitmapFactory.decodeResource(getResources(), R.drawable.bluejeans); //Instantiate heroBmp - assign to heroBmp for the first time
        holder = getHolder();
        loopThread = new LoopThread(this);

        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                loopThread.setRunning(true);
                loopThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                boolean retry = true;
                loopThread.setRunning(false);
                while (retry) {
                    try {
                        loopThread.join();
                    } catch (InterruptedException e) {

                    }
                }
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.BLACK);
        if (x < getWidth() - heroBmp.getWidth()) {
            x++;
        }
        canvas.drawBitmap(heroBmp, x, 10 * 2560 / getHeight(), null); // Draw heroBmp at (10, 10)
    }
}
