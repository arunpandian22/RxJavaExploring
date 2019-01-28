package me.arun.arunrxjavaexploring;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.arun.arunrxjavaexploring.NetWork.RxNetworkActivity;
import me.arun.arunrxjavaexploring.NetWork.SampleRequestActivity;
import me.arun.arunrxjavaexploring.RoomDB.RoomDbMainActivity;
import me.arun.arunrxjavaexploring.RxOperaters.OperatorsActivity;
import me.arun.arunrxjavaexploring.pagination.PaginationActivity;
import me.arun.arunrxjavaexploring.rxBus.RXBusChaeckActivity;

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
        switch (title)
        {
            case FeatureCategory.SAMPLE:
                activity.startActivity(new Intent(activity, RxBasicActivity.class));
                break;

            case FeatureCategory.OPERATOR:
                activity.startActivity(new Intent(activity, OperatorsActivity.class));
                break;


            case FeatureCategory.ROOMDB:
                activity.startActivity(new Intent(activity, RoomDbMainActivity.class));
                break;

            case FeatureCategory.NETWORK:
                activity.startActivity(new Intent(activity, SampleRequestActivity.class));
                break;

            case FeatureCategory.PAGINATION:
                activity.startActivity(new Intent(activity, PaginationActivity.class));
                break;

            case FeatureCategory.RXBUS:
                activity.startActivity(new Intent(activity, RXBusChaeckActivity.class));

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
    public class ViewHolder extends RecyclerView.ViewHolder
    {
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