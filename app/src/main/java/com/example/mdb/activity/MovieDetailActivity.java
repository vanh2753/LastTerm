package com.example.mdb.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mdb.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MovieDetailActivity extends AppCompatActivity {
    ImageView ivMovieDetail;
    TextView txtMovieName, txtMovieTime, txtMovieYear, txtMovieGenre, txtMovieRate, txtMovieActors;
    Button btnDel;

    String id="",imgUrl="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);

        ivMovieDetail=(ImageView) findViewById(R.id.ivMovieDetail);
        txtMovieName =(TextView) findViewById(R.id.txtName);
        txtMovieTime = (TextView) findViewById(R.id.txtTime);
        txtMovieYear = (TextView) findViewById(R.id.txtYear);
        txtMovieGenre=(TextView) findViewById(R.id.txtGenre);
        txtMovieActors =(TextView)  findViewById(R.id.txtActors);
        txtMovieRate =(TextView)  findViewById(R.id.txtRate);
        btnDel=(Button)findViewById(R.id.btnDelete);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            Glide.with(this).load(bundle.getString("Image")).into(ivMovieDetail);
            txtMovieName.setText(bundle.getString("Name"));
            txtMovieTime.setText(bundle.getString("Time"));
            txtMovieYear.setText(bundle.getString("Year"));
            txtMovieGenre.setText(bundle.getString("Genre"));
            txtMovieActors.setText(bundle.getString("Actors"));
            txtMovieRate.setText(bundle.getString("Rate"));

            id=bundle.getString("Id");
            imgUrl=bundle.getString("Image");
        }

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Movies");
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageReference = storage.getReferenceFromUrl(imgUrl);
                Log.d("imgurl", imgUrl);
                storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        reference.child(id).removeValue();
                        Intent intent = new Intent(MovieDetailActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });
    }
}