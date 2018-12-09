package com.food.test.finalproject.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.food.test.finalproject.R;
import com.food.test.finalproject.adapter.ViewPagerAdapter;
import com.food.test.finalproject.fragment.HomeFragment;
import com.food.test.finalproject.fragment.PublishFragment;
import com.food.test.finalproject.fragment.UserInfoFragment;
import com.food.test.finalproject.utils.ActivityUtils;
import com.food.test.finalproject.utils.BottomNavigationViewHelper;

public class MainActivity extends AppCompatActivity {

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
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_notifications:
                    viewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
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

}
