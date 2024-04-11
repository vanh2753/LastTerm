package com.example.mdb.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

//import android.widget.SearchView;
import androidx.appcompat.widget.SearchView;

import com.example.mdb.R;
import com.example.mdb.adapter.rvAdapter;
import com.example.mdb.model.Movie;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fabAdd;
    RecyclerView mainRecycleView;
    ArrayList<Movie> movieList;
    DatabaseReference databaseReference;
    ValueEventListener valueEventListener;
    rvAdapter adapter;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView=findViewById(R.id.search);
        searchView.clearFocus();
        fabAdd=findViewById(R.id.fab);
        mainRecycleView=findViewById(R.id.mainRecycleView);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, UploadActivity.class);
                startActivity(intent);
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this,2);
        mainRecycleView.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
        builder.setView(R.layout.progress_loading);
        AlertDialog dialog=builder.create();
        dialog.show();

        movieList=new ArrayList<>();
        adapter=new rvAdapter(MainActivity.this,movieList);
        mainRecycleView.setAdapter(adapter);

        databaseReference= FirebaseDatabase.getInstance().getReference("Movies");
        dialog.show();

        valueEventListener=databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                movieList.clear();
                for(DataSnapshot item: snapshot.getChildren()){
                    Movie movie=item.getValue(Movie.class);
                    movie.setMovieId(item.getKey());
                    movieList.add(movie);
                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });

    }
    public void searchList(String text){
        ArrayList<Movie> searchList = new ArrayList<>();
        for (Movie item: movieList){
            if (item.getMovieName().toLowerCase().contains(text.toLowerCase())){
                searchList.add(item);
            }
        }
        adapter.movieSearchList(searchList);
    }
}