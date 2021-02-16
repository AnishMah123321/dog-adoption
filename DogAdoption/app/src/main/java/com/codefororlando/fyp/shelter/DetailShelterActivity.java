package com.codefororlando.fyp.shelter;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codefororlando.fyp.R;
import com.codefororlando.fyp.adapter.FabRecyclerAdapter;
import com.codefororlando.fyp.adapter.FabShleterAdapter;
import com.codefororlando.fyp.model.ContactList;
import com.codefororlando.fyp.model.EmailList;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DetailShelterActivity extends AppCompatActivity {
    private static final String TAG = "DetailShelterActivity";
    RecyclerView fabRecycler;
    RecyclerView recyclerViewEmail;
    ImageView imageView;
    FloatingActionButton floatingActionEmail;
    FloatingActionButton floatingActionContact;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shelter_detail_activity);

        Log.d(TAG, "onCreate: started.");
        getIncomingIntent();

        floatingActionEmail = findViewById(R.id.fabLabelEmail);
        floatingActionEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEmailDialog();

            }

        });
        floatingActionContact = findViewById(R.id.fabLabelContact);
        floatingActionContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showContactDialog();

            }
        });

    }

    private void showEmailDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(DetailShelterActivity.this);
        final LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.recycler_email, null, false);
        alertDialog.setView(view);

        recyclerViewEmail = view.findViewById(R.id.fab_email);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewEmail.setLayoutManager(layoutManager);

        final List<EmailList> emailLists = new ArrayList<>();
        emailLists.add(new EmailList("Sneha's Care Nepal","info@snehacare.com"));
        emailLists.add(new EmailList("Kathmandu Animal Treatment Centre", "katinfo@katcentre.org.np"));
        emailLists.add(new EmailList("Shree's Animal Rescue Nepal", "srijanathakuri75@gmail.com"));
        emailLists.add(new EmailList("Community Dog Welfare Kopan", "kate.clendon@gmail.com"));
        emailLists.add(new EmailList("Street Dog Care", " info@streetdogcare.org"));

        final FabShleterAdapter adapterEmail =new FabShleterAdapter(this,emailLists);
        recyclerViewEmail.setAdapter(adapterEmail);
        adapterEmail.FabShelterRecycler(new FabShleterAdapter.OnAddClickListener() {
            @Override
            public void onAddClick(int position) {
                String emailShelter = emailLists.get(position).getEmailShelter();
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("Mail to: " +emailShelter));
                emailIntent.putExtra(Intent.EXTRA_EMAIL, emailShelter);
                emailIntent.setType("message/rfc822");
                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    finish();
                    Log.i("Finished sending email...", "");
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(DetailShelterActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        alertDialog.create();
        alertDialog.show();
    }

    private void showContactDialog() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(DetailShelterActivity.this);
        final LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.recycler_fab, null, false);
        alertDialog.setView(view);

        fabRecycler = view.findViewById(R.id.fab_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        fabRecycler.setLayoutManager(layoutManager);

        final List<ContactList> contactLists = new ArrayList<>();
        contactLists.add(new ContactList("Sneha's Care Nepal", "980-8645023"));
        contactLists.add(new ContactList("Kathmandu Animal Treatment Centre", "984-3810363"));
        contactLists.add(new ContactList("Shree's Animal Rescue Nepal", "980-8645023"));
        contactLists.add(new ContactList("Community Dog Welfare Kopan", "980-3976378"));
        contactLists.add(new ContactList("Street Dog Care", "984-1075383"));

        final FabRecyclerAdapter adapter = new FabRecyclerAdapter(contactLists, this);
        fabRecycler.setAdapter(adapter);
        adapter.setonAddClickListener(new FabRecyclerAdapter.OnAddClickListener() {
            @Override
            public void onAddClick(int position) {
                String number = contactLists.get(position).getNumber();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel: " + number));
                startActivity(intent);
                Toast.makeText(DetailShelterActivity.this, "asd"+contactLists.get(position).getNumber(), Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.create();
        alertDialog.show();

    }


    private void getIncomingIntent(){
        Log.d(TAG,"GetIntent: checking for incoming intents");
        if(getIntent().hasExtra("image_url")&& getIntent().hasExtra("image_name") && getIntent().hasExtra("dogShelter")) {
            Log.d(TAG,"getIncomingIntent: found intent extras");
            String imageUrl = getIntent().getStringExtra("image_url");
            String imageName = getIntent().getStringExtra("image_name");
            String dogShelter = getIntent().getStringExtra("dogShelter");
            setImage(imageUrl,imageName,dogShelter);
        }

    }
    private void setImage(String imageUrl, String imageName, String dogShelter) {
        Log.d(TAG,"setImage: setting the image and name to widgets");

        TextView name = findViewById(R.id.shelterDescription);
        name.setText(imageName);

        TextView dogS = findViewById(R.id.shelterD);
        dogS.setText(dogShelter);

       imageView = findViewById(R.id.shelterImage);
        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(imageView);



    }
}
