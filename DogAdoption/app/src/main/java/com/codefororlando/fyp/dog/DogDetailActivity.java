package com.codefororlando.fyp.dog;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.codefororlando.fyp.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DogDetailActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private static final String TAG = "DogDetailActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.dog_detail_activity);

        Log.d(TAG,"onCreate: started.");
        getIncomingIntent();
    }

    private void getIncomingIntent() {
        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        String dog = bundle.getString("dog");
        String name = bundle.getString("name");
        String number = bundle.getString("number");
        String email = bundle.getString("email");
        String breed = bundle.getString("breed");
        String description = bundle.getString("description");

        setString(dog, name, number, email, breed,description);
    }

    private void setString(String dog, String name, String number, String email, String breed, String description) {
        TextView dogName = findViewById(R.id.dogName);
        dogName.setText(dog);

        TextView cName = findViewById(R.id.contactName);
        cName.setText(name);

        TextView cNumber = findViewById(R.id.contactNumber);
        cNumber.setText(number);

        TextView cEmail = findViewById(R.id.contactEmail);
        cEmail.setText(email);

        TextView dBreed = findViewById(R.id.Dbreed);
        dBreed.setText(breed);

        TextView dDesc = findViewById(R.id.DogDescription);
        dDesc.setText(description);
    }
}
