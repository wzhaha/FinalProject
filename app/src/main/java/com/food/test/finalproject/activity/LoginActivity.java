package com.food.test.finalproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.food.test.finalproject.R;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    // the views in our login screen
    public @BindView(R.id.etUsername)EditText etUsername;
    public @BindView (R.id.etPassword)EditText etPassword;
    public @BindView(R.id.qqlogin)
    TextView qqlogin;
    Tencent mTencent;

    private boolean login = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // check if user is already logged in
        if (login){
            Intent intent = new Intent(this, MainFriendActivity.class);
            startActivity(intent);
            finish();
        }
        // bind the views using butterknife
        ButterKnife.bind(this);
    }

    // on click for login button using butterknife
    @OnClick(R.id.btnLogin)
    public void onLoginClick(){
        // access username and password input
        final String username = etUsername.getText().toString();
        final String password = etPassword.getText().toString();

        // login use username and password
        login(username, password);
    }

    // on click for signup button using butterknife
    @OnClick(R.id.btnSignUp)
    public void onSignUpClickMain(){
        signUp();
    }

    @OnClick(R.id.qqlogin)
    public void setQqlogin(){
        mTencent = Tencent.createInstance("1108064784",getApplicationContext());//将123123123改为自己的AppID
        mTencent.login(LoginActivity.this,"all",new BaseUiListener());
    }
    // method that logs a user in
    private void login(String username, String password){

        // go to home activity after successful login
        final Intent intent = new Intent(LoginActivity.this, MainFriendActivity.class);
        startActivity(intent);
        finish();

    }

    // method that signs a user up for an account
    private void signUp(){
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            try{
                String openid=((JSONObject)response).getString("openid");
                mTencent.setOpenId(openid);
                mTencent.setAccessToken(((JSONObject) response).getString("access_token"),((JSONObject) response).getString("expires_in"));
//               获取具体的用户信息
                QQToken qqToken = mTencent.getQQToken();
                UserInfo info = new UserInfo(getApplicationContext(), qqToken);
                info.getUserInfo(new IUiListener() {
                    @Override
                    public void onComplete(Object o) {
                        try{
                            Intent i2 = new Intent(LoginActivity.this,MainFriendActivity.class);
                            startActivity(i2);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(UiError uiError) {

                    }

                    @Override
                    public void onCancel() {

                    }
                });
            }catch (Exception e){
                e.printStackTrace();
            }


        }

        protected void doComplete(JSONObject values) {
        }

        @Override
        public void onError(UiError e) {
//            showResult("onError:", "code:" + e.errorCode + ", msg:"
//                    + e.errorMessage + ", detail:" + e.errorDetail);
        }
        @Override
        public void onCancel() {
//            showResult("onCancel", "");
        }
    }
}
