package com.codefororlando.fyp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codefororlando.fyp.adapter.DashboardAdapter;
import com.codefororlando.fyp.api.ApiClient;
import com.codefororlando.fyp.helper.SharedPrefManager;
import com.codefororlando.fyp.model.ServerResponse;
import com.codefororlando.fyp.model.User;
import com.codefororlando.fyp.navigation.AboutUsActivity;
import com.codefororlando.fyp.maps.MapActivity;
import com.codefororlando.fyp.model.ListData;
import com.codefororlando.fyp.navigation.FeedbackActivity;
import com.codefororlando.fyp.navigation.ProfileActivity;
import com.codefororlando.fyp.navigation.SettingsActivity;
import com.codefororlando.fyp.signup.LoginActivity;
import com.codefororlando.fyp.helper.SharedPrefManager;

import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout dl;
    public static final String GOOGLE_ACCOUNT = "google_account";
    private ActionBarDrawerToggle toggle;
    private NavigationView nv;
    Context context;
    RecyclerView recyclerView;
    private DashboardAdapter.OnItemClickListener listener;
    private ImageView addPet, maps;

    Toolbar toolbar;
    public static final String KEY_SHARED_PREFS = "SharedPrefs";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

//        NavigationView navigationView = findViewById(R.id.nav_view);
//        View header = navigationView.getHeaderView(0);
        SharedPreferences sharedPreferences = getSharedPreferences(KEY_SHARED_PREFS,MODE_PRIVATE);
        String email = sharedPreferences.getString("email","");

        NavigationView navigationView = findViewById(R.id.nv);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);

        if(!email.isEmpty()) {
            TextView nameTxt = header.findViewById(R.id.banner_useremail);
            nameTxt.setText(email);
        }



        bindView();
        context = this;

        toggle = new ActionBarDrawerToggle(this, dl, R.string.Navigation_Drawer_Open, R.string.Navigation_Drawer_Close);
        dl.addDrawerListener(toggle);
        toggle.syncState();
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icoo);


        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, MapActivity.class);
                startActivity(intent);
            }
        });

        addPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, AddPetActivity.class);
                startActivity(intent);
            }
        });


        ListData[] myList = new ListData[]{
                new ListData("Dog List Detail", android.R.drawable.ic_menu_info_details),
                new ListData("Dog Shelter Detail", android.R.drawable.ic_menu_info_details),
                new ListData("Adopt a Dog?", android.R.drawable.ic_menu_help),

        };

        DashboardAdapter myListAdapter = new DashboardAdapter(myList, listener);
        recyclerView.hasFixedSize();
        //recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myListAdapter);
    }
    private void bindView() {
        dl = findViewById(R.id.drawer);
        addPet = findViewById(R.id.addSign);
        nv = findViewById(R.id.nv);
        recyclerView = findViewById(R.id.recyclerView);
        toolbar = findViewById(R.id.toolbar);
        maps = findViewById(R.id.maps);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (dl.isDrawerOpen(GravityCompat.START)) {
                dl.closeDrawer(GravityCompat.START);
            } else {
                dl.openDrawer(GravityCompat.START);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        switch (id) {

            case R.id.profile:
                Intent intentProfile = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intentProfile);
                Toast.makeText(Dashboard.this, "Profile", Toast.LENGTH_SHORT).show();
                break;

            case R.id.settings:
                Intent intentSettings = new Intent(this, SettingsActivity.class);
                startActivity(intentSettings);
                Toast.makeText(Dashboard.this, "Settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.aboutUs:
                Intent intentAbout = new Intent(getApplicationContext(), AboutUsActivity.class);
                startActivity(intentAbout);
                Toast.makeText(Dashboard.this, "About Us", Toast.LENGTH_SHORT).show();
                break;
            case R.id.feedback:
                Intent intentFeedback = new Intent(getApplicationContext(), FeedbackActivity.class);
                startActivity(intentFeedback);
                Toast.makeText(Dashboard.this, "Feedback", Toast.LENGTH_SHORT).show();
                break;

            case R.id.share:
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String shareBody = "https://www.facebook.com/anish.maharjan.353";
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Share the App");
                shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);

                startActivity(Intent.createChooser(shareIntent, "Share via"));

                Toast.makeText(Dashboard.this, "Share", Toast.LENGTH_SHORT).show();
                break;

            case R.id.logout:


              

                Intent intent = new Intent(Dashboard.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

                Toast.makeText(Dashboard.this, "Logout", Toast.LENGTH_SHORT).show();
                finish();

                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        super.onBackPressed();
    }


}
