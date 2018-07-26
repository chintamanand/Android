package com.example.mahe.facerecognition1;

/**
 * Created by mahe on 7/23/2018.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.media.FaceDetector;
import android.media.FaceDetector.Face;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

public class FaceImageView extends android.support.v7.widget.AppCompatImageView{

    private static final int MAX_FACES = 10;
    private RectF[] rects = new RectF[MAX_FACES];
    private Bitmap image;

    public FaceImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public FaceImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FaceImageView(Context context) {
        super(context);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();



        canvas.drawBitmap(image, 0, 0, p);
        Paint rectPaint = new Paint();
        rectPaint.setStrokeWidth(2);
        rectPaint.setColor(Color.BLUE);
        rectPaint.setStyle(Style.STROKE);


        for (int i=0; i < MAX_FACES; i++) {
            RectF r = rects[i];
            if (r != null)
                canvas.drawRect(r, rectPaint);
        }
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public void detectFaces() {
        Log.d("FaceDet", "Detecting faces....");
        // Convert bitmap in 556

        Bitmap tmpBmp = image.copy(Config.RGB_565, true);



        FaceDetector faceDet = new FaceDetector(tmpBmp.getWidth(), tmpBmp.getHeight(), MAX_FACES);

        Face[] faceList = new Face[MAX_FACES];

        faceDet.findFaces(tmpBmp, faceList);

        // Log the result
        for (int i=0; i < faceList.length; i++) {
            Face face = faceList[i];
            Log.d("FaceDet", "Face ["+face+"]");
            if (face != null) {
                Log.d("FaceDet", "Face ["+i+"] - Confidence ["+face.confidence()+"]");
                PointF pf = new PointF();
                face.getMidPoint(pf);
                Log.d("FaceDet", "\t Eyes distance ["+face.eyesDistance()+"] - Face midpoint ["+pf+"]");
                RectF r = new RectF();
                r.left = pf.x - face.eyesDistance() / 2;
                r.right = pf.x + face.eyesDistance() / 2;
                r.top = pf.y - face.eyesDistance() / 2;
                r.bottom = pf.y + face.eyesDistance() / 2;
                rects[i] = r;
            }
        }

        this.invalidate();
    }
}