package com.food.test.finalproject;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;
import pub.devrel.easypermissions.EasyPermissions;

public class NewPhotoActivity extends AppCompatActivity {

    public LocationClient mLocationClient = null;
    public MyLocationListener myListener = new MyLocationListener();
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

    @BindView(R.id.position)
    TextView positionText;

    public void onSubmit(View view){
        Intent res = new Intent();
        final String ava = "https://img2.woyaogexing.com/2018/12/22/9607fc6633c54399a58ad17310efdfb0!400x400.jpeg";
        User user = new User("8", "自己", ava);
        user.setId("8");
        user.setName("自己");
        CircleItem circleItem = new CircleItem();
        if (dizhi.isEmpty()) {
            circleItem.setContent(textField.getText().toString());
        }
        else {
            circleItem.setContent("#" + dizhi + "\n" + textField.getText().toString());
        }
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
    String dizhi = "";
    ArrayList<ImageItem> images;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initPermission();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_photo);
        ButterKnife.bind(this);

        //定位
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        //注册监听函数

        LocationClientOption option = new LocationClientOption();

        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("bd09ll");
        option.setScanSpan(1000);
        option.setOpenGps(true);
        option.setLocationNotify(true);
        option.setIgnoreKillProcess(false);
        option.SetIgnoreCacheException(false);
//可选，设置是否收集Crash信息，默认收集，即参数为false
        option.setWifiCacheTimeOut(5*60*1000);
//可选，V7.2版本新增能力
//如果设置了该接口，首次启动定位时，会先判断当前Wi-Fi是否超出有效期，若超出有效期，会先重新扫描Wi-Fi，然后定位
        option.setEnableSimulateGps(false);
//可选，设置是否需要过滤GPS仿真结果，默认需要，即参数为false
        mLocationClient.setLocOption(option);
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
        buttonPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLocationClient.start();
//                if (!mLocationClient.isStarted()){
//                    mLocationClient.stop();
//                }
            }
        });
        imageView.setImageBitmap(bitmap);
    }
    @Override
    protected void onSaveInstanceState(Bundle oldInstanceState) {
        super.onSaveInstanceState(oldInstanceState);
        oldInstanceState.clear();
    }

    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location){
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取经纬度相关（常用）的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

        double latitude = location.getLatitude();    //获取纬度信息
        double longitude = location.getLongitude();    //获取经度信息
        float radius = location.getRadius();    //获取定位精度，默认值为0.0f

        String coorType = location.getCoorType();
        //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准
//
            int errorCode = location.getLocType();
//        //获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明

            String addr = location.getAddrStr();    //获取详细地址信息
//            String country = location.getCountry();    //获取国家
//            String province = location.getProvince();    //获取省份
//            String city = location.getCity();    //获取城市
//            String district = location.getDistrict();    //获取区县
//            String street = location.getStreet();    //获取街道信息
            dizhi = addr;
            positionText.setText(dizhi);
            System.out.println("position: "+dizhi);
        }
    }

    private void initPermission() {
        String[] perms = {Manifest.permission.CALL_PHONE
                , Manifest.permission.WRITE_EXTERNAL_STORAGE
                , Manifest.permission.READ_EXTERNAL_STORAGE
                , Manifest.permission.CAMERA
                , Manifest.permission.ACCESS_COARSE_LOCATION
                , Manifest.permission.ACCEPT_HANDOVER
                , Manifest.permission.ACCESS_FINE_LOCATION
                , Manifest.permission.CHANGE_WIFI_STATE
                , Manifest.permission.ACCESS_WIFI_STATE
                , Manifest.permission.ACCESS_NETWORK_STATE
                , Manifest.permission.READ_PHONE_STATE
                , Manifest.permission.INTERNET
                , Manifest.permission.CALL_PHONE};

        if (EasyPermissions.hasPermissions(this, perms)) {
            // Already have permission, do the thing
            // ...
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, "因为功能需要，需要使用相关权限，请允许",
                    100, perms);
        }
    }
}
