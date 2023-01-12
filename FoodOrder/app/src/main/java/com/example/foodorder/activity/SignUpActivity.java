package com.example.foodorder.activity;


import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.foodorder.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends BaseActivity {
    public ActivitySignUpBinding mActivitySignUpBinding;
    private EditText edtEmail,edtPassword,edtCfPassword;
    public ImageView view1,view2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivitySignUpBinding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(mActivitySignUpBinding.getRoot());
        initUI();
        initListener();
    }

    private void initListener() {
        mActivitySignUpBinding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSingUp();
            }
        });
        mActivitySignUpBinding.loginBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        view1.setOnClickListener(new View.OnClickListener() {
            boolean isPasswordVisible = true;
            @Override
            public void onClick(View v) {
                if(isPasswordVisible) {
                    edtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
                    isPasswordVisible = false;
                }
                else
                {
                    edtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    isPasswordVisible = true;
                }

            }
        });

        view2.setOnClickListener(new View.OnClickListener() {
            boolean isPasswordVisible = true;
            @Override
            public void onClick(View v) {
                if(isPasswordVisible) {
                    edtCfPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
                    isPasswordVisible = false;
                }
                else
                {
                    edtCfPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    isPasswordVisible = true;
                }

            }
        });
    }

    private void initUI(){
        edtEmail = mActivitySignUpBinding.registerEmailTxt;
        edtPassword =mActivitySignUpBinding.registerPasswordTxt;
        edtCfPassword=mActivitySignUpBinding.registerCfPasswordTxt;
        view1=mActivitySignUpBinding.showRegisterPassword;
        view2=mActivitySignUpBinding.showRegisterCfpassword;
    }

    private void onClickSingUp(){
       String email=edtEmail.getText().toString().trim();
        String password=edtPassword.getText().toString().trim();
        String cfPassword=edtCfPassword.getText().toString().trim();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        createProgressDialog();
        showProgressDialog(true);
        if(email.isEmpty()){
            dismissProgressDialog();
            Toast.makeText(SignUpActivity.this, "Vui lòng nhập email",
                    Toast.LENGTH_SHORT).show();
        }
        else if(password.isEmpty()){
            dismissProgressDialog();
            Toast.makeText(SignUpActivity.this, "Vui lòng nhập mật khẩu",
                    Toast.LENGTH_SHORT).show();
        }
        else if(password.length()<6){
            dismissProgressDialog();
            Toast.makeText(SignUpActivity.this, "Mật khẩu tối thiểu 6 ký tự",
                    Toast.LENGTH_SHORT).show();
        }
        else if(cfPassword.isEmpty()){
            dismissProgressDialog();
            Toast.makeText(SignUpActivity.this, "Vui lòng nhập lại mật khẩu",
                    Toast.LENGTH_SHORT).show();
        }
        else if(!password.equals(cfPassword)){
            dismissProgressDialog();
            Toast.makeText(SignUpActivity.this, "Mật khẩu xác nhận không khớp",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                dismissProgressDialog();
                                Toast.makeText(SignUpActivity.this, "Đăng ký thành công",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                startActivity(intent);
                                finishAffinity();

                            } else {
                                dismissProgressDialog();
                                // If sign in fails, display a message to the user.
                                Toast.makeText(SignUpActivity.this, "Đăng ký thất bại, vui lòng thử lại",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

}