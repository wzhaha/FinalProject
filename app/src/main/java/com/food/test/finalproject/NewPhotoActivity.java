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

import com.food.test.finalproject.bean.CircleItem;
import com.food.test.finalproject.bean.CommentItem;
import com.food.test.finalproject.bean.FavortItem;
import com.food.test.finalproject.bean.PhotoInfo;
import com.food.test.finalproject.bean.User;
import com.lqr.imagepicker.bean.ImageItem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewPhotoActivity extends AppCompatActivity {

    @BindView(R.id.button_submit)
    Button button_submit;

    @BindView(R.id.textField)
    EditText textField;

    @BindView(R.id.imageView)
    ImageView imageView;

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
    }
    @Override
    protected void onSaveInstanceState(Bundle oldInstanceState) {
        super.onSaveInstanceState(oldInstanceState);
        oldInstanceState.clear();
    }
}
