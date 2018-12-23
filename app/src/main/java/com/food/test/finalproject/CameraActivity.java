package com.food.test.finalproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.cjt2325.cameralibrary.JCameraView;
import com.cjt2325.cameralibrary.listener.ClickListener;

import java.io.File;

public class CameraActivity extends AppCompatActivity {
    JCameraView jCameraView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        //1.1.1
        jCameraView = (JCameraView) findViewById(R.id.jcameraview);

//设置视频保存路径
        jCameraView.setSaveVideoPath(Environment.getExternalStorageDirectory().getPath() + File.separator + "JCamera");

//设置只能录像或只能拍照或两种都可以（默认两种都可以）
        jCameraView.setFeatures(JCameraView.BUTTON_STATE_BOTH);

//设置视频质量
        jCameraView.setMediaQuality(JCameraView.MEDIA_QUALITY_MIDDLE);


        jCameraView.setJCameraLisenter(new JCameraLisenter() {
            @Override
            public void captureSuccess(Bitmap bitmap) {
                //获取图片bitmap
                Log.i("JCameraView cc", "bitmap = " + bitmap.getWidth());
                Intent newPhoto = new Intent(getBaseContext(), NewPhotoActivity.class);
                startActivity(newPhoto);
                finish();
            }
            @Override
            public void recordSuccess(String url,Bitmap firstFrame) {
                //获取视频路径
                Log.i("CJT", "url = " + url);
            }
            //@Override
            //public void quit() {
            //    (1.1.9+后用左边按钮的点击事件替换)
            //}
        });
//左边按钮点击事件
        jCameraView.setLeftClickListener(new ClickListener() {
            @Override
            public void onClick() {
                CameraActivity.this.finish();
            }
        });
    }
}
