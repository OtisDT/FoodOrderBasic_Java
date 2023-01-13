package com.example.foodorder.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.view.View;
import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.foodorder.R;
import com.example.foodorder.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends BaseActivity {
    public ActivityLoginBinding mActivityLoginBinding;

    EditText edtEmail,edtPassword;
    ImageView imgShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityLoginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(mActivityLoginBinding.getRoot());

        initUI();
        initListener();
    }

    private void initListener() {
        mActivityLoginBinding.registerBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
        mActivityLoginBinding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickLogin();
            }
        });

        imgShow.setOnClickListener(new View.OnClickListener() {
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
    }

    public void initUI(){
        edtEmail=mActivityLoginBinding.loginEmailTxt;
        edtPassword=mActivityLoginBinding.loginPasswordTxt;
        imgShow=mActivityLoginBinding.showPassword;
    }

    public void onClickLogin(){
        String email=edtEmail.getText().toString().trim();
        String password=edtPassword.getText().toString().trim();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        createProgressDialog();
        showProgressDialog(true);
        if(email.isEmpty()){
            dismissProgressDialog();
            Toast.makeText(LoginActivity.this, "Vui lòng nhập email",
                    Toast.LENGTH_SHORT).show();
        }
        else if(password.isEmpty()){
            dismissProgressDialog();
            Toast.makeText(LoginActivity.this, "Vui lòng nhập mật khẩu",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                dismissProgressDialog();
                                Toast.makeText(LoginActivity.this, "Đăng nhập thành công",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                dismissProgressDialog();
                                // If sign in fails, display a message to the user.
                                Toast.makeText(LoginActivity.this, "Đăng nhập thất bại, vui lòng thử lại",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }





}