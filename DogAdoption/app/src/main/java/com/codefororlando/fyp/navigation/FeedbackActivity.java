package com.codefororlando.fyp.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.codefororlando.fyp.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class FeedbackActivity extends AppCompatActivity {
    EditText edit1, edit2;
    RatingBar ratingBar;
    AppCompatButton btnSend;
    float myRating = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

            edit1 = findViewById(R.id.edit1);
            edit2 = findViewById(R.id.edit2);
            ratingBar = findViewById(R.id.rating_bar);
            btnSend = findViewById(R.id.btnSend);

            ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

                    int rating = (int) v;
                    String message = null;

                   myRating = ratingBar.getRating();

                    switch (rating) {
                        case 1:
                            message = "Sorry to heat that! :(";
                            break;
                        case 2:
                            message = "We always accept suggestions!";
                            break;
                        case 3:
                            message = "Good Enough!";
                            break;
                        case 4:
                            message = "Great! Thank You! :)";
                            break;
                        case 5:
                            message = "Awesome! You are the best! :D";
                            break;

                    }
                    Toast.makeText(FeedbackActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/html");
                intent.putExtra(Intent.EXTRA_EMAIL, new String("maharjan.anish.yt@gmail.com"));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback Form");
                intent.putExtra(Intent.EXTRA_TEXT, "Name:"+edit1.getText()+"\n Message:"+edit2.getText());

                try{
                    startActivity(Intent.createChooser(intent,"Please Select Email"));
                }
                catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(FeedbackActivity.this, "There are no Email Clients", Toast.LENGTH_LONG).show();
                }

            }
        });






    }

}

