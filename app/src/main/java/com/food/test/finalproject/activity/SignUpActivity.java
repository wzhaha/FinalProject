package com.food.test.finalproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.food.test.finalproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity {

    // the views
    @BindView(R.id.etUsername) EditText etUsername;
    @BindView(R.id.etPassword) EditText etPassword;
    @BindView(R.id.etConfirmPassword) EditText etConfirmPassword;
    @BindView(R.id.btnSignUp) Button btnSignUp;
    @BindView(R.id.tvLogin) TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // bind using butterknife
        ButterKnife.bind(this);

    }

    // on click for create account button
    @OnClick(R.id.btnSignUp)
    public void onSignUpClick(){
//        if (ParseUser.getCurrentUser() != null){
//            ParseUser.logOut();
//        }
        // access text in views
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();

        // create user conditions
        if (!TextUtils.isEmpty(username)
                && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(confirmPassword)){
            if (password.equals(confirmPassword)){
                createUser(username, password);
            } else{
                Toast.makeText(SignUpActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            }
        } else{
            Toast.makeText(SignUpActivity.this, "Please fill in all required fields", Toast.LENGTH_LONG).show();

        }
    }

    // on click for login text view
    @OnClick(R.id.tvLogin)
    public void onLoginClick(){
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    // method to create the user on the parse server
    public void createUser(String username, String password){
        Intent intent=new Intent(SignUpActivity.this,LoginActivity.class);
        startActivity(intent);

    }
}
