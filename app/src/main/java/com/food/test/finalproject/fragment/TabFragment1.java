package com.food.test.finalproject.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.food.test.finalproject.R;
import com.food.test.finalproject.utils.DatasUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TabFragment1 extends Fragment {
    public static final String TITLE = "title";
    private String mTitle = "Defaut Value";
    private RecyclerView mRecyclerView;
    private int itemCount;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab, null, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.id_stickyNavLayout_innerScrollview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(new RecyclerView.Adapter<MyHolder>() {

            @Override
            public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                LinearLayout linearLayout=new LinearLayout(parent.getContext());
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(0, 0, 0, 50);
                linearLayout.setLayoutParams(layoutParams);

                ImageView imageView = new ImageView(parent.getContext());
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.bg2));
                imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                ViewGroup.MarginLayoutParams margin = new ViewGroup.MarginLayoutParams(imageView.getLayoutParams());
                margin.topMargin=5;
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                linearLayout.addView(imageView);

                TextView textView=new TextView(parent.getContext());
                textView.setText(DatasUtil.CONTENTS[DatasUtil.getRandomNum(DatasUtil.CONTENTS.length)]);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
                linearLayout.addView(textView);

                TextView time=new TextView(parent.getContext());
                time.setText(df.format(new Date()));
                time.setGravity(Gravity.RIGHT);
                time.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
                linearLayout.addView(time);

                return new MyHolder(linearLayout);
            }

            @Override
            public void onBindViewHolder(MyHolder holder, int position) {
//                ImageView itemView = (ImageView) holder.itemView;

            }

            @Override
            public int getItemCount() {
                return itemCount;
            }
        });

        return view;

    }

    class MyHolder extends RecyclerView.ViewHolder {

        public MyHolder(View itemView) {
            super(itemView);
        }
    }

    public static TabFragment1 newInstance(String title, int itemCount) {
        TabFragment1 tabFragment = new TabFragment1();
        tabFragment.itemCount=itemCount;
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

}
