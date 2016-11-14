package com.example.ayushvora.potholereporter;

import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.crash.FirebaseCrash;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import static com.google.android.gms.analytics.internal.zzy.ex;

public class PotholePictureLocationActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, ActivityCompat.OnRequestPermissionsResultCallback {
    HashMap<String, String> potholeDataMap;
    ImageView thumbNailView;
    TextView locationTextView;
    Intent takePictureIntent;
    static final int REQUEST_TAKE_PHOTO = 1;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Uri photoURI;
    double latitude;
    double longitude;
    String mCurrentPhotoPath;
    private static final int MY_PERMISSION_ACCESS_COURSE_LOCATION = 0;
    private static final int MY_PERMISSION_ACCESS_FINE_LOCATION = 1;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pothole_picture_location);
        thumbNailView = (ImageView) findViewById(R.id.potholeImage);
        locationTextView = (TextView) findViewById(R.id.locationTextView);
        Intent mainActivityIntent = getIntent();
        takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        potholeDataMap = (HashMap<String, String>) mainActivityIntent.getSerializableExtra("potholeDataMap");

        mGoogleApiClient = new GoogleApiClient.Builder(this).addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        if ( ContextCompat.checkSelfPermission( this, Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {

            ActivityCompat.requestPermissions( this, new String[] {  android.Manifest.permission.ACCESS_FINE_LOCATION  },
                    MY_PERMISSION_ACCESS_FINE_LOCATION );
        }
    }
    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }
    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    public void onCaptureAndGeoTagPothole(View view){

        if (mLastLocation != null) {
            locationTextView.setText("Location Co-ordinates: "+String.valueOf(latitude)+", "+String.valueOf(longitude));
            potholeDataMap.put("latitude",String.valueOf(latitude));
            potholeDataMap.put("longitude",String.valueOf(longitude));
        }
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

//    private String pathFromUri(Uri imageUri) {
//        String[] filePathColumn = { MediaStore.Images.Media.DATA };
//        Cursor cursor = getContentResolver().query(imageUri, filePathColumn,
//                null, null, null);
//        cursor.moveToFirst();
//        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//        String filePath = cursor.getString(columnIndex);
//        return filePath ;
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            try {
                Bitmap imageBitmap = (Bitmap) BitmapFactory.decodeStream(getContentResolver().openInputStream(photoURI));
                thumbNailView.setImageBitmap(imageBitmap);
                thumbNailView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            } catch (FileNotFoundException fe){
                Log.e(STORAGE_SERVICE,"FileNotFound");
            }

        }
    }

    public void onNextClick(View view){
        potholeDataMap.put("latitude",String.valueOf(latitude));
        potholeDataMap.put("longitude",String.valueOf(longitude));
        Intent nextIntent = new Intent(this,DimensionQuestionActivity.class);
        nextIntent.putExtra("potholeDataMap",potholeDataMap);
        startActivity(nextIntent);

    }
    public void onPrevClick(View view){
        Intent prevIntent = new Intent(this,MainActivity.class);
        prevIntent.putExtra("potholeDataMap",potholeDataMap);
        startActivity(prevIntent);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if ( Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {
            return  ;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            latitude = mLastLocation.getLatitude();
            longitude = mLastLocation.getLongitude();
            locationTextView.setText("Location Co-ordinates: "+String.valueOf(latitude)+", "+String.valueOf(longitude));
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_ACCESS_FINE_LOCATION:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    try {
                        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                                mGoogleApiClient);
                    } catch(SecurityException e){
                        FirebaseCrash.log( "Error creating location service: " + e.getMessage() );
                    }
                    if (mLastLocation != null) {
                        latitude = mLastLocation.getLatitude();
                        longitude = mLastLocation.getLongitude();
                    }
                } else {

                }
                return;
        }
    }
}
