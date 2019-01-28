package me.arun.arunrxjavaexploring.NetWork;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.arun.arunrxjavaexploring.R;
import me.arun.arunrxjavaexploring.RoomDB.models.response.nowPlaying.Result;
import me.arun.arunrxjavaexploring.utils.AppStrings;
import me.arun.arunrxjavaexploring.utils.colourPalleteUtil.ColourPalleteUtil;
import me.arun.arunrxjavaexploring.utils.colourPalleteUtil.PalleteColourListener;
/**
 * A Adapter class to list the categorys in grid
 * Created by Arun Pandian M on 25/December/2018
 * arunsachin222@gmail.com
 * Chennai
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    private final Activity activity;

    List<Result> moviesList = new ArrayList<>();
   ColourPalleteUtil colourPalleteUtil = new ColourPalleteUtil();


    public MovieListAdapter(Activity activity, List<Result> listMovies) {
        this.activity = activity;
        this.moviesList = listMovies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (holder != null)
        {
            Result movie = moviesList.get(position);
            String imagePosterUrl=AppStrings.BASE_POSTER_PATH+movie.getPosterPath();
            ImageView ivPoset=holder.ivMoviePoster;

            holder.tvMovieName.setText(movie.getOriginalTitle());
            Glide.with(activity).load(imagePosterUrl).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                    //if you want to convert the drawable to ImageView
                    Bitmap bitmapImage  = ((BitmapDrawable) resource).getBitmap();

                   colourPalleteUtil.getImageColorData(bitmapImage, new PalleteColourListener() {
                       @Override
                       public void getColurPallete(Palette pallete) {
                          holder.viewTitleBaground.setBackgroundColor(pallete.getVibrantColor(pallete.getLightVibrantColor(0)));
                       }
                   });

                    return false;
                }
            }).into(ivPoset);




            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
        }
    }




    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public void setListMovies(List<Result> moviesList){
        this.moviesList=moviesList;
        notifyDataSetChanged();
    }

    public void addMovies(List<Result> moviesList){
        this.moviesList.addAll(moviesList);
        notifyDataSetChanged();
    }

    /**
     * A viewHolder class for Image items
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivMoviePoster)
        ImageView ivMoviePoster;
        @BindView(R.id.tvMovieName)
        TextView tvMovieName;
        @BindView(R.id.viewTitleBackground)
        View viewTitleBaground;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }


}
