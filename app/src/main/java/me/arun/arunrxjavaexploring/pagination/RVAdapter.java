package me.arun.arunrxjavaexploring.pagination;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.arun.arunrxjavaexploring.R;
import me.arun.arunrxjavaexploring.User;


public class RVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    List<User> items = new ArrayList<>();

    public RVAdapter() {

    }

    void addItems(List<User> items) {
        this.items.addAll(items);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ItemViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ItemViewHolder) holder).bind(items.get(position));
    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }

    private static class ItemViewHolder extends RecyclerView.ViewHolder
    {
        ItemViewHolder(View itemView) {
            super(itemView);
        }

        static ItemViewHolder create(ViewGroup parent) {
            return new ItemViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pagination, parent, false));
        }

        void bind(User content) {
            ((TextView) itemView).setText(content.firstname);
        }
    }
}
