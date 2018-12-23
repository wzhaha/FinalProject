package com.food.test.finalproject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.food.test.finalproject.R;
import com.food.test.finalproject.activity.LoginActivity;

public class UserInfoFragment extends Fragment {

    private Button logout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_userinfo, container, false);
        logout=view.findViewById(R.id.logout);
        initView();;
        return view;
    }

    public void initView(){
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
        }
        });
    }

}
