package com.codefororlando.fyp;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.icu.util.Calendar;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.codefororlando.fyp.api.ApiClient;
import com.codefororlando.fyp.dog.DogListActivity;
import com.codefororlando.fyp.model.ServerResponse;
import com.codefororlando.fyp.model.UserPet;
import com.codefororlando.fyp.model.UserResponse;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.app.ActivityCompat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPetActivity extends AppCompatActivity implements OnMapReadyCallback {
    private static final String IMAGE_DIRECTORY = "/Images";
    public static final int REQUEST_LOCATION_CODE = 6;
    AppCompatButton postBtn;
    AppCompatEditText contactName, dogName, breed, description, contactNumber,contactEmail;
    private ImageView imageView1;
    private GoogleMap mMap;
    private int GALLERY = 1, CAMERA = 2;
    private static final String TAG = "AddPetActivity";

    private FusedLocationProviderClient fusedLocationProviderClient;

    protected final LatLng mDefaultLocation = new LatLng(27.700769, 85.300140);
    private static final int DEFAULT_ZOOM = 15;
    private boolean mLocationPermissionGranted;

    protected Location mLastLocation;

    private FloatingSearchView floatingSearchView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_pet_layout);

        requestMultiplePermissions();
        bindViews();
        setMapDetails();

        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();

            }
        });
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog();

            }
        });

    }
    private void bindViews() {
        floatingSearchView = findViewById(R.id.floatingSearchView);
        postBtn = findViewById(R.id.postBtn);
        imageView1 = findViewById(R.id.imgView1);
        contactName = findViewById(R.id.cName);
        dogName = findViewById(R.id.dName);
        breed = findViewById(R.id.breed);
        description = findViewById(R.id.description);
        contactEmail = findViewById(R.id.cEmail);
        contactNumber = findViewById(R.id.cPhone);


    }

    private void submit() {
                Log.d(TAG, "DogListActivity");

                if (validate() == false) {
                    return;
        }
        Log.d(TAG, "Pet Added");
        saveToRecycler();

    }

    private boolean validate() {
        boolean valid = true;
        String name = contactName.getText().toString();
        String dog = dogName.getText().toString();
        String dBreed = breed.getText().toString();
        String dDesc = description.getText().toString();
        String cNumber = contactNumber.getText().toString();
        String email = contactEmail.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            contactName.setError("Atleast 3 characters");
            valid = false;
        } else {
            contactName.setError(null);
        }
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            contactEmail.setError("Enter a valid email address");
            valid = false;

        } else {
            contactEmail.setError(null);
        }
        if (cNumber.isEmpty() || cNumber.length() < 10) {
            contactNumber.setError("enter a valid phone number");
            valid = false;

        } else {
            contactNumber.setError(null);
        }
        if (dog.isEmpty() || dog.length() < 3) {
            dogName.setError("Atleast 3 characters");
            valid = false;
        } else {
            dogName.setError(null);
        }
        if (dBreed.isEmpty() || dBreed.length() < 2) {
            breed.setError("Atleast 3 characters");
            valid = false;
        } else {
            breed.setError(null);
        }
        if (dDesc.isEmpty() || dDesc.length() < 3) {
            description.setError("Atleast 3 characters");
            valid = false;
        } else {
            description.setError(null);
        }
        return valid;
    }

    private void saveToRecycler() {
        String name = contactName.getText().toString();
        String dog = dogName.getText().toString();
        String dBreed = breed.getText().toString();
        String dDesc = description.getText().toString();
        String cNumber = contactNumber.getText().toString();
        String email = contactEmail.getText().toString();

      final UserPet userPet = new UserPet(name,dog,dBreed,dDesc,cNumber,email);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("name", userPet.getName());
        hashMap.put("dog", userPet.getDogname());
        hashMap.put("breed", userPet.getBreed());
        hashMap.put("number",userPet.getNumber());
        hashMap.put("description", userPet.getDescription());
        hashMap.put("email", userPet.getEmail());
        Call<UserResponse> call = ApiClient.getApiServices().userPet(hashMap);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                if (response.body().getSuccess()) {
                    Intent intent = new Intent(AddPetActivity.this, DogListActivity.class);
                    startActivity(intent);
                    finish();

                    Toast.makeText(getApplicationContext(), "Listed Successful", Toast.LENGTH_LONG).show();
              } else {
                   Toast.makeText(getApplicationContext(), "Listing fail", Toast.LENGTH_LONG).show();
                      }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Listing fail", Toast.LENGTH_LONG).show();
            }

        });
    }

    private void requestMultiplePermissions() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {  // check if all permissions are granted
                            Toast.makeText(getApplicationContext(), "All permissions are granted by user!", Toast.LENGTH_SHORT).show();
                        }

                        if (report.isAnyPermissionPermanentlyDenied()) { // check for permanent denial of any permission

                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();

                    }
                }).withErrorListener(new PermissionRequestErrorListener() {
            @Override
            public void onError(DexterError error) {
                Toast.makeText(getApplicationContext(), "Some Error! ", Toast.LENGTH_SHORT).show();
            }
        }).onSameThread()
                .check();
    }

    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {"Select photo from gallery", "Capture photo from camera"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();

    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }
    private void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    String path = saveImage(bitmap);
                    Toast.makeText(getApplicationContext(), "Image Saved!", Toast.LENGTH_SHORT).show();
                    imageView1.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            imageView1.setImageBitmap(thumbnail);
            saveImage(thumbnail);
            Toast.makeText(getApplicationContext(), "Image Saved!", Toast.LENGTH_SHORT).show();
        }
    }

    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        if (!wallpaperDirectory.exists()) {  // have the object build the directory structure, if needed.
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance().getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::---&gt;" + f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    private void setMapDetails() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        floatingSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {

            }

            @Override
            public void onSearchAction(String currentQuery) {
                searchForPlace(currentQuery);
            }
        });
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    private void searchForPlace(String searchedPlace) {
        Geocoder geocoder = new Geocoder(this);
        try {
            List<Address> addresses = geocoder.getFromLocationName(searchedPlace, 1);
            if (addresses.size() > 0) {
                double lat = (addresses.get(0).getLatitude());
                double lon = (addresses.get(0).getLongitude());

                Log.d("lat-long", "" + lat + "......." + lon);
                final LatLng user = new LatLng(lat, lon);
                Marker marker = mMap.addMarker(new MarkerOptions()
                        .position(user)
                        .title(searchedPlace.toUpperCase()));
                marker.showInfoWindow();
                mMap.moveCamera(CameraUpdateFactory
                        .newLatLngZoom(user, 12));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMinZoomPreference(14);

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                //Clear all markers pointed in map
                mMap.clear();

                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(new LatLng(latLng.latitude, latLng.longitude));
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                mMap.addMarker(markerOptions);
            }
        });
        getLocationPermission();
        updateLocationUI();
        getDeviceLocation();
    }

    private void getLocationPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        if (requestCode == REQUEST_LOCATION_CODE) {
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionGranted = true;
                updateLocationUI();
            } else {
                mMap.moveCamera(CameraUpdateFactory
                        .newLatLngZoom(mDefaultLocation, 12));
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
            }
        }
    }

    private void getDeviceLocation() {
        try {
            if (mLocationPermissionGranted) {
                Task<Location> locationResult = fusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful()) {
                            mLastLocation = task.getResult();

                            double latitude;
                            double longitude;

                            latitude = mLastLocation != null ? mLastLocation.getLatitude() : 27.700769;
                            longitude = mLastLocation != null ? mLastLocation.getLongitude() : 85.300140;

                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(latitude, longitude), 12));
                            //AppController.getmInstance().setCurrentLocation(new LatLng(latitude, longitude));
                            Log.d("location::::", "LatLng: " + latitude + " " + longitude);
                            setMarker(latitude, longitude);
                        } else {
                            Log.d("location:::", "Current location is null. Using defaults.");
                            Log.e("location:::", "Exception: %s", task.getException());
                            mMap.moveCamera(CameraUpdateFactory
                                    .newLatLngZoom(mDefaultLocation, 12));
                            mMap.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    public void setMarker(Double latitude, Double longitude) {
        mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("Current Location")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

    }

    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }
        try {
            if (mLocationPermissionGranted) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
                getDeviceLocation();
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mLastLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }
}