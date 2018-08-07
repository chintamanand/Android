package com.example.mahe.facerecognition1;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Button tap;
    ImageView iv;
    private Uri file;
    private FaceImageView fiv;
    private static final int CAMERA_REQUEST = 1888;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tap=(Button)findViewById(R.id.button);
        iv=(ImageView)findViewById(R.id.imageView);
        //fiv=(FaceImageView)findViewById(R.id.imageView);



    }
    public void takeImageFromCamera(View view) {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap mphoto = (Bitmap) data.getExtras().get("data");
            //this Built-in Function
            iv.setImageBitmap(mphoto);
            //to store in External Storage we need to check the External Storage permission(for Writable)
            saveTempBitmap(mphoto);

            Toast.makeText(getApplicationContext(),"Processing Image",Toast.LENGTH_SHORT).show();
            //try
           // BitmapFactory.Options options = new BitmapFactory.Options();
            //options.inMutable=true;
            Paint myRectPaint = new Paint();
            myRectPaint.setStrokeWidth(5);
            myRectPaint.setColor(Color.RED);
            myRectPaint.setStyle(Paint.Style.STROKE);
            Bitmap tempBitmap = Bitmap.createBitmap(mphoto.getWidth(), mphoto.getHeight(), Bitmap.Config.RGB_565);
            Canvas tempCanvas = new Canvas(tempBitmap);
            tempCanvas.drawBitmap(mphoto, 0, 0, null);

             //If we Set Face TrackingEnabled=true ,then face will be Tracked 
             //It will be mostly helpful during the video editing
            FaceDetector faceDetector = new
                    FaceDetector.Builder(getApplicationContext()).setTrackingEnabled(false)
                    .build();
	    //A detector’s isOperational method can be used to check if the required native library is currently available
            if(!faceDetector.isOperational()){
                new AlertDialog.Builder(getApplicationContext()).setMessage("Could not set up the face detector!").show();
                return;
            }
	    //Given a bitmap, we can create Frame instance from the bitmap to supply to the detector
            Frame frame = new Frame.Builder().setBitmap(mphoto).build();
	    //The detector can be called synchronously with a frame to detect faces	
            SparseArray<Face> faces = faceDetector.detect(frame);

            for(int i=0; i<faces.size(); i++) {
                Face thisFace = faces.valueAt(i);
                float x1 = thisFace.getPosition().x;
                float y1 = thisFace.getPosition().y;
                float x2 = x1 + thisFace.getWidth();
                float y2 = y1 + thisFace.getHeight();
                tempCanvas.drawRoundRect(new RectF(x1, y1, x2, y2), 2, 2, myRectPaint);
            }
            iv.setImageDrawable(new BitmapDrawable(getResources(),tempBitmap));
            //try
        }
    }
    public void saveTempBitmap(Bitmap bitmap) {
        if (isExternalStorageWritable()) {
            saveImage(bitmap);
            Toast.makeText(getApplicationContext(),"Image Saved Successfully",Toast.LENGTH_LONG).show();
        }else{
            //prompt the user or do something
            Toast.makeText(getApplicationContext(),"error to Store Image",Toast.LENGTH_LONG).show();
        }
    }


    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    private void saveImage(Bitmap finalBitmap) {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/saved_images");
        myDir.mkdirs();

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fname = "Image_"+ timeStamp +".jpg";

        File file = new File(myDir, fname);
        if (file.exists()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
