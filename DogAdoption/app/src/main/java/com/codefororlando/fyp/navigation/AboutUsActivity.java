package com.codefororlando.fyp.navigation;

import android.os.Bundle;
import android.view.View;

import com.codefororlando.fyp.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutUsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Element adaElement = new Element();

        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.yes)
                .setDescription("This is Demo Version")
                .addItem(new Element().setTitle("Version 1.0"))
                .addItem(adaElement)
                .addGroup("Connect With Me")
                .addEmail("maharjan.anish.yt@gmail.com")
                .addFacebook("anish.maharjan.353")
                .addInstagram("p.otato.head")
                .addTwitter("anish353")
                .create();

        setContentView(aboutPage);


    }
}

