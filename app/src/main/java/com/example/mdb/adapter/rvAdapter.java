package com.example.mdb.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mdb.R;
import com.example.mdb.activity.MovieDetailActivity;
import com.example.mdb.model.Movie;

import java.util.ArrayList;

public class rvAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private ArrayList<Movie> dataList;

    public rvAdapter(Context context, ArrayList<Movie> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.movie_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(dataList.get(position).getMovieImage()).into(holder.ivMovieMain);
        holder.txtMainName.setText(dataList.get(position).getMovieName());
        holder.txtMainRate.setText(dataList.get(position).getMovieRate().toString());

        holder.movieLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtra("Id", dataList.get(holder.getAbsoluteAdapterPosition()).getMovieId());
                intent.putExtra("Image", dataList.get(holder.getAbsoluteAdapterPosition()).getMovieImage());
                intent.putExtra("Name", dataList.get(holder.getAbsoluteAdapterPosition()).getMovieName());
                intent.putExtra("Time", dataList.get(holder.getAbsoluteAdapterPosition()).getMovieTime());
                intent.putExtra("Year", dataList.get(holder.getAbsoluteAdapterPosition()).getMovieYear());
                intent.putExtra("Genre", dataList.get(holder.getAbsoluteAdapterPosition()).getMovieGenre());
                intent.putExtra("Actors", dataList.get(holder.getAbsoluteAdapterPosition()).getMovieActors());
                intent.putExtra("Rate", dataList.get(holder.getAbsoluteAdapterPosition()).getMovieRate().toString());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return dataList.size();
    }
    public void movieSearchList(ArrayList<Movie> searchList){
        dataList = searchList;
        notifyDataSetChanged();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{

    LinearLayout movieLayout;
    ImageView ivMovieMain;
    TextView txtMainName,txtMainRate;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        movieLayout=itemView.findViewById(R.id.movieMainLayout);
        ivMovieMain=itemView.findViewById(R.id.ivMainMovie);
        txtMainName=itemView.findViewById(R.id.txtMainName);
        txtMainRate=itemView.findViewById(R.id.txtMainRate);
    }
}
