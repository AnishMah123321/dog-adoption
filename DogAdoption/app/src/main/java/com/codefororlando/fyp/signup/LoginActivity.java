package com.codefororlando.fyp.signup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.codefororlando.fyp.Dashboard;
import com.codefororlando.fyp.R;
import com.codefororlando.fyp.api.ApiClient;
import com.codefororlando.fyp.helper.SharedPrefManager;
import com.codefororlando.fyp.model.ServerResponse;
import com.codefororlando.fyp.model.User;
import com.codefororlando.fyp.navigation.ProfileActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.util.HashMap;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private AppCompatButton btnLogin, btnSignUp;
    private EditText emailTxt, passwordTxt;
    public static final String KEY_SHARED_PREFS = "SharedPrefs";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        bindViews();
        SharedPreferences sharedPreferences = getSharedPreferences(KEY_SHARED_PREFS, MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "");

        if (!email.isEmpty()) {
            Intent intent = new Intent(getApplicationContext(), Dashboard.class);
            startActivity(intent);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();

            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


    }

    private void bindViews() {
        emailTxt = findViewById(R.id.etEmail);
        passwordTxt = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.buttonLogin);
        btnSignUp = findViewById(R.id.buttonSignUp);

    }

    private void login() {
        Log.d(TAG, "Login");
        if (validate() == false) {
            return;
        }
        loginByServer();
    }

    private boolean validate() {
        boolean valid = true;
        String email = emailTxt.getText().toString();
        String password = passwordTxt.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailTxt.setError("enter a valid email address");
            requestFocus(emailTxt);
            valid = false;
        } else {
            emailTxt.setError(null);
        }
        if (password.isEmpty()) {
            passwordTxt.setError("Password is empty");
            requestFocus(passwordTxt);
            valid = false;
        } else {
            passwordTxt.setError(null);
        }
        return valid;

    }

    private void loginByServer() {
        String email = emailTxt.getText().toString();
        String password = passwordTxt.getText().toString();
        final User user = new User(email, password);

        final HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("email", user.getEmail());
        hashMap.put("password", user.getPassword());
        Call<ServerResponse> responseCall = ApiClient.getApiServices().userLogin(hashMap);
        responseCall.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if (response.body().getSuccess()) {
                  /*  SharedPrefManager.getInstance(LoginActivity.this)
                            .saveUser(response.body().getUser());*/
                    Intent intent = new Intent(LoginActivity.this, Dashboard.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    startActivity(intent);

                    SharedPreferences sharedPreferences = getSharedPreferences(KEY_SHARED_PREFS, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("email", user.getEmail());
                    Toast.makeText(LoginActivity.this, user.getEmail() + "", Toast.LENGTH_SHORT).show();
                    editor.apply();

                    finish();

                    Toast.makeText(LoginActivity.this, "Login Successful" + response.body().getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Email or Password Invalid" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                t.printStackTrace();
                Log.d("onFailure", t.toString());
                Toast.makeText(LoginActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();


            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {
                this.finish();
            }
        }
    }


    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

 /*   @Override
    protected void onStart() {
        super.onStart();

        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            Intent intent = new Intent(LoginActivity.this, Dashboard.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        }*/

}
