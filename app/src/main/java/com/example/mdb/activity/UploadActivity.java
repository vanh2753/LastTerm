package com.example.mdb.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.mdb.R;

public class UploadActivity extends AppCompatActivity {

    ImageView ivMovie = (ImageView) findViewById(R.id.ivMovie);
    EditText etMovieName,etMovieTime,etMovieYear,etMovieActors,etMovieRate;
    Button btnMovieImg;
    String imgUrl;
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        etMovieName = findViewById(R.id.etMovieName);
        etMovieTime = findViewById(R.id.etMovieTime);
        etMovieYear = findViewById(R.id.etMovieYear);
        etMovieActors = findViewById(R.id.etMovieActors);
        etMovieRate = findViewById(R.id.etMovieRate);


        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult, new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    uri = data.getData();
                    ivMovie.setImageURI(uri);
                }
            }
        });

        ivMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photo = new Intent(Intent.ACTION_PICK);
                photo.setType("image/*");
                activityResultLauncher.launch(photo);
            }
        });
    }
