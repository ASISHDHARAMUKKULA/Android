package com.example.asish.textscan;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    SurfaceView cameraview;
    TextView t;
    CameraSource c;
    final int RequestCameraPermissionID = 1001;


        public void onRequestPermissionResult(int reqcode, @NonNull String[] permissions,@NonNull int[] Requestgrant){
        //super.onRequestPermissionsResult(reqcode,permissions,Requestgrant);
            switch (reqcode){
                case RequestCameraPermissionID:
                {
                    if(Requestgrant[0]==PackageManager.PERMISSION_GRANTED){
                        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        try {
                                c.start(cameraview.getHolder());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cameraview = (SurfaceView) findViewById(R.id.surface);
        t = (TextView) findViewById(R.id.text);
        TextRecognizer tr = new TextRecognizer.Builder(getApplicationContext()).build();
        if (!tr.isOperational()) {
            Log.w("MainActivity", "Nothing");
        } else {
            c = new CameraSource.Builder(getApplicationContext(),tr)
                    .setFacing(CameraSource.CAMERA_FACING_BACK)
                    .setRequestedPreviewSize(1280, 1024)
                    .setRequestedFps(2.0f)
                    .setAutoFocusEnabled(true)
                    .build();
            cameraview.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    try {
                        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new  String[]{Manifest.permission.CAMERA},
                                    RequestCameraPermissionID);
                            return;
                        }
                        c.start(cameraview.getHolder());
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {
                    c.stop();

                }
            });
            tr.setProcessor(new Detector.Processor<TextBlock>() {
                @Override
                public void release() {

                }

                @Override
                public void receiveDetections(Detector.Detections<TextBlock> detections) {
                    final SparseArray<TextBlock> items=detections.getDetectedItems();
                    if(items.size()!=0){
                        t.post(new Runnable() {
                            @Override
                            public void run() {
                                StringBuilder st=new StringBuilder();
                                for(int i=0;i<items.size();i++)
                                {
                                    TextBlock item=items.valueAt(i);
                                    st.append(item.getValue());
                                    st.append("\n");
                                }
                                t.setText(st.toString());
                            }

                        });
                    }
                }
            });

        }

    }
}
