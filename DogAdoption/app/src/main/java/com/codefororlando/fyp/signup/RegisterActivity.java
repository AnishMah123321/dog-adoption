package com.codefororlando.fyp.signup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.codefororlando.fyp.Dashboard;
import com.codefororlando.fyp.R;
import com.codefororlando.fyp.api.ApiClient;
import com.codefororlando.fyp.helper.SharedPrefManager;
import com.codefororlando.fyp.model.ServerResponse;
import com.codefororlando.fyp.model.User;
import com.codefororlando.fyp.navigation.ProfileActivity;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    private AppCompatEditText editTextName, editTextEmail, editTextMobile, cnPassword,
            editTextPassword;
    private AppCompatButton buttonRegister;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextMobile = findViewById(R.id.editTextMobile);
        cnPassword = findViewById(R.id.cnfPassword);

        buttonRegister = findViewById(R.id.btnSignUp);
        

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });
    }

    private void signUp() {
        Log.d(TAG, "Register");
        if (validate() == false) {
            return;
        }
        savetoServerDB();
    }


    private boolean validate() {
        boolean valid = true;
        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        String reEnterPassword = cnPassword.getText().toString();
        String mobile = editTextMobile.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            editTextName.setError("Atleast three characters");
            valid = false;

        } else {
            editTextName.setError(null);
        }
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Enter a valid email address");
            valid = false;

        } else {
            editTextEmail.setError(null);
        }
        if (password.isEmpty() || password.length() < 5) {
            editTextPassword.setError("Password should be atleast 5 characters");

            valid = false;

        } else {
            editTextPassword.setError(null);

        }
        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 5 || reEnterPassword.length()>10 || !(reEnterPassword.equals(password))) {
            cnPassword.setError("password do not match");
            valid = false;

        } else {
            cnPassword.setError(null);


        }
        if (mobile.isEmpty() || mobile.length() < 10) {
            editTextMobile.setError("enter a valid phone number");
            valid = false;

        } else {
            editTextMobile.setError(null);

        }
        return valid;
    }
    private void savetoServerDB() {
        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        String reEnterPassword = cnPassword.getText().toString();
        String mobile = editTextMobile.getText().toString();

        User user = new User(name,password, email,mobile,reEnterPassword);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("username", user.getUsername());
        hashMap.put("password", user.getPassword());
        hashMap.put("email", user.getEmail());
        hashMap.put("mobile", user.getMobile());
        hashMap.put("repassword", user.getRepassword());

        Call<ServerResponse> call = ApiClient.getApiServices().userSignUp(hashMap);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if(response.body().getSuccess()) {
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Register Successful", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Register fail", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }

}
