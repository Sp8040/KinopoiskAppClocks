package ru.startandroid.kinopoiskappclocks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends  RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    private final static String PHOTO_URL = "http://cinema.areas.su/up/images/";

    private List<Movies> mMovies;
    private Context mContext;

    public MovieAdapter(List<Movies> movies) {
        this.mMovies = movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Movies movie = mMovies.get(position);
        holder.nameTextView.setText(movie.getName());
        holder.descriptionTextView.setText(movie.getDescription());

        Picasso.with(mContext)
                .load(PHOTO_URL + movie.getPoster())
                .resize(500,700)
                .into(holder.posterImageView);
    }

    @Override
    public int getItemCount() {
        if (mMovies == null) {
            return 0;
        }
        return mMovies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView descriptionTextView;
        ImageView posterImageView;

        ViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.name);
            descriptionTextView = (TextView) itemView.findViewById(R.id.description);
            posterImageView = (ImageView) itemView.findViewById(R.id.poster);
        }
    }


}
