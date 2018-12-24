package com.food.test.finalproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.food.test.finalproject.CameraActivity;
import com.food.test.finalproject.R;
import com.food.test.finalproject.UILImageLoader;
import com.food.test.finalproject.adapter.ViewPagerAdapter;
import com.food.test.finalproject.fragment.HomeFragment;
import com.food.test.finalproject.fragment.PublishFragment;
import com.food.test.finalproject.fragment.UserInfoFragment;
import com.food.test.finalproject.utils.BottomNavigationViewHelper;
import com.lqr.imagepicker.ImagePicker;
import com.lqr.imagepicker.ui.ImageGridActivity;
import com.lqr.imagepicker.view.CropImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MainActivity extends AppCompatActivity {

    //z自定义的弹出框
    SelectPicPopupWindow shareTypeSelect;

    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_dashboard:
//                    viewPager.setCurrentItem(1);
                    shareTypeSelect = new SelectPicPopupWindow(MainActivity.this, shareItemsOnClick);
                    shareTypeSelect.showAtLocation(MainActivity.this.findViewById(R.id.view_pager),Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                    return true;
                case R.id.navigation_notifications:
                    viewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };

    private View.OnClickListener shareItemsOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            shareTypeSelect.dismiss();
            switch (view.getId()){
                case R.id.btn_take_photo:
                    Toast.makeText(getBaseContext(), "take photo", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getBaseContext(), CameraActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_pick_photo:
                    Toast.makeText(getBaseContext(), "pick photo", Toast.LENGTH_LONG).show();
                    Intent pick = new Intent(getBaseContext(), ImageGridActivity.class);
                    startActivityForResult(pick, 100);
                default:
                    break;

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initUniversalImageLoader();
        initImagePicker();
    }

    //初始化界面
    private void initView(){
        viewPager=findViewById(R.id.view_pager);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        adapter=new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment());
        adapter.addFragment(new PublishFragment());
        adapter.addFragment(new UserInfoFragment());

        //设置页面中间的ViewPager的适配器
        viewPager.setAdapter(adapter);
        //一开始加载的界面是运动界面
        viewPager.setCurrentItem(0);
    }
    private void initUniversalImageLoader() {
        //初始化ImageLoader
        ImageLoader.getInstance().init(
                ImageLoaderConfiguration.createDefault(getApplicationContext()));
    }

    /**
     * 初始化仿微信控件ImagePicker
     */
    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new UILImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(9);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
    }
}
