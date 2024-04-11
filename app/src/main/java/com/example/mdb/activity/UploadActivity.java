package com.example.mdb.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mdb.R;
import com.example.mdb.model.Movie;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class UploadActivity extends AppCompatActivity {

    ImageView ivMovie;
    EditText etMovieName, etMovieTime, etMovieYear,etMovieGenre, etMovieRate,etMovieActors;
    Button btnUpload;
    String imgUrl;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        ivMovie=(ImageView) findViewById(R.id.ivMovie);
        etMovieName =(EditText) findViewById(R.id.etMovieName);
        etMovieTime = (EditText) findViewById(R.id.etMovieTime);
        etMovieYear = (EditText) findViewById(R.id.etMovieYear);
        etMovieGenre=(EditText) findViewById(R.id.etMovieGenre);
        etMovieActors =(EditText)  findViewById(R.id.etMovieActors);
        etMovieRate =(EditText)  findViewById(R.id.etMovieRate);
        btnUpload = (Button) findViewById(R.id.btnUpload);

        ActivityResultLauncher<Intent> activityResultLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode()==Activity.RESULT_OK){
                    Intent data = result.getData();
                    uri=data.getData();
                    ivMovie.setImageURI(uri);
                }
            }
        });

        ivMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photo=new Intent(Intent.ACTION_PICK);
                photo.setType("image/*");
                activityResultLauncher.launch(photo);
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMovie();
            }
        });
    }

    private void saveMovie() {
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Movies").child(uri.getLastPathSegment());

        AlertDialog.Builder builder= new AlertDialog.Builder(UploadActivity.this);
        builder.setView(R.layout.progress_loading);
        AlertDialog dialog=builder.create();
        dialog.show();

        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri urlImage = uriTask.getResult();
                imgUrl = urlImage.toString();
                uploadMovie();
                dialog.dismiss();
            }
        });
    }

    private void uploadMovie() {

        String name=etMovieName.getText().toString();
        String time=etMovieTime.getText().toString();
        String year=etMovieYear.getText().toString();
        String genre=etMovieGenre.getText().toString();
        Float rate=Float.parseFloat(etMovieRate.getText().toString());
        String actors=etMovieActors.getText().toString();

        Movie movie=new Movie(imgUrl,name,time,year,genre,actors,rate);

        FirebaseDatabase.getInstance().getReference("Movies").child(name).setValue(movie).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(UploadActivity.this,"Uploaded",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}


