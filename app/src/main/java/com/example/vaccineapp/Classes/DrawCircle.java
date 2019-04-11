package com.example.vaccineapp.Classes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class DrawCircle extends View {

        private Paint paint;

        public DrawCircle(Context context, int color) {
            super(context);
            // create the Paint and set its color
            paint = new Paint();
            paint.setColor(color);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            //canvas.drawColor(Color.BLUE);
            canvas.drawCircle(50, 100, 40, paint);
        }

}
