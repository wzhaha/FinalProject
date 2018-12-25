package com.food.test.finalproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.food.test.finalproject.bean.CircleItem;
import com.food.test.finalproject.bean.CommentItem;
import com.food.test.finalproject.bean.FavortItem;
import com.food.test.finalproject.bean.PhotoInfo;
import com.food.test.finalproject.bean.User;
import com.lqr.imagepicker.bean.ImageItem;
import com.food.test.finalproject.model.MyLocationListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewPhotoActivity extends AppCompatActivity {

    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
    //BDAbstractLocationListener为7.2版本新增的Abstract类型的监听接口
//原有BDLocationListener接口暂时同步保留。具体介绍请参考后文中的说明

    @BindView(R.id.button_submit)
    Button button_submit;

    @BindView(R.id.textField)
    EditText textField;

    @BindView(R.id.imageView)
    ImageView imageView;

    @BindView(R.id.button3)
    Button buttonPosition;


    public void onSubmit(View view){
        Intent res = new Intent();
        final String ava = "https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=3680604140,401532791&fm=179&app=42&f=JPEG?w=121&h=140";
        User user = new User("8", "自己", ava);
        user.setId("8");
        user.setName("自己");
        CircleItem circleItem = new CircleItem();
        circleItem.setContent(textField.getText().toString());
        circleItem.setUser(user);
        circleItem.setCreateTime("12月24日");
        circleItem.setType("2");
        circleItem.setFavorters(new LinkedList<FavortItem>());
        circleItem.setComments(new LinkedList<CommentItem>());
        ArrayList<PhotoInfo> photos = new ArrayList<>();
        if (images != null) {
            for (ImageItem image: images) {
                PhotoInfo photoInfo = new PhotoInfo();
                photoInfo.aPath = image.path;
                photoInfo.path = image.path;
                photoInfo.url = image.path;
                photoInfo.h = image.height;
                photoInfo.w = image.width;
                photos.add(photoInfo);
            }
        } else {
            PhotoInfo photoInfo = new PhotoInfo();
            photoInfo.path = filename;
            photoInfo.url = filename; // 必须填写，否则空指针
            photoInfo.h = 1024;
            photoInfo.w = 768;
            photos.add(photoInfo);
        }

        circleItem.setPhotos(photos);
        res.putExtra("circle", circleItem);
        setResult(RESULT_OK, res);
        finish();
//        res.putExtra("circle", )
    }

    Bitmap bitmap;
    String filename;
    ArrayList<ImageItem> images;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_photo);
        ButterKnife.bind(this);

        //定位
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        //注册监听函数

        LocationClientOption option = new LocationClientOption();

        option.setIsNeedAddress(true);
        //可选，是否需要地址信息，默认为不需要，即参数为false
        //如果开发者需要获得当前点的地址信息，此处必须为true

        mLocationClient.setLocOption(option);
        //mLocationClient为第二步初始化过的LocationClient对象
        //需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        //更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明

        filename = getIntent().getStringExtra("bitmap");
        if (filename == null) {
            images = (ArrayList<ImageItem>) getIntent().getSerializableExtra("pictures");
            filename = images.get(0).path;
//                FileInputStream is = new FileInputStream(new File(filename));
            bitmap = BitmapFactory.decodeFile(filename);
        } else {
            try {
                FileInputStream is = openFileInput(filename);
                bitmap = BitmapFactory.decodeStream(is);
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        imageView.setImageBitmap(bitmap);

        //获取定位
        buttonPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(NewPhotoActivity.this,"开始获取位置",Toast.LENGTH_LONG);
                mLocationClient.start();
//                mLocationClient.stop();
            }
        });
    }
    @Override
    protected void onSaveInstanceState(Bundle oldInstanceState) {
        super.onSaveInstanceState(oldInstanceState);
        oldInstanceState.clear();
    }
}
