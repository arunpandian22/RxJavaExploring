package me.arun.arunrxjavaexploring;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A Adapter class to list the categorys in grid
 * Created by Arun Pandian M on 25/December/2018
 * arunsachin222@gmail.com
 * Chennai
 */

public class FeatureCategoryAdapter extends RecyclerView.Adapter<FeatureCategoryAdapter.ViewHolder> {

    private final Activity activity;

    List<ModelFeatureCategory> listModelFeatureList = new ArrayList<>();

    public FeatureCategoryAdapter(Activity activity, List<ModelFeatureCategory> listModelFeatureList) {
        this.activity = activity;
        this.listModelFeatureList = listModelFeatureList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_req_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position)
    {
        if (holder != null)
        {
         final    ModelFeatureCategory modelFeatureCategory = listModelFeatureList.get(position);
            int imageResource = activity.getResources().getIdentifier(modelFeatureCategory.getImageUri(), null, activity.getPackageName());
            Drawable iconDrwable = activity.getResources().getDrawable(imageResource);
            holder.ivCategoryIcon.setImageDrawable(iconDrwable);
            holder.tvCategoryTitle.setText(modelFeatureCategory.getTitle());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    reDirectActivity(modelFeatureCategory.getTitle());
                }
            });
        }
    }


    /**
     * A method to redirect the activity to selected Activity
     * @param title
     */
    public void reDirectActivity(@FeatureCategory String title)
    {
        switch (title) {
            case FeatureCategory.SAMPLE_REQUEST:
//                activity.startActivity(new Intent(activity, SampleRequestActivity.class));
                break;

            case FeatureCategory.FILE_DOWNLOAD:
//                activity.startActivity(new Intent(activity, FileDownloadActivity.class));
                break;

            case FeatureCategory.FILE_UPLOAD:
//                activity.startActivity(new Intent(activity, FileUploadActivity.class));
                break;

            case FeatureCategory.GRAPH_QL:
//                activity.startActivity(new Intent(activity, GraphQlActivity.class));

        }

    }


    @Override
    public int getItemCount() {
        return listModelFeatureList.size();
    }


    /**
     * A viewHolder class for Image items
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivCategoryIcon)
        ImageView ivCategoryIcon;
        @BindView(R.id.tvCateogryTitle)
        TextView tvCategoryTitle;

        public ViewHolder(View itemView)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}