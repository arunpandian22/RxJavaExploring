package me.arun.arunrxjavaexploring.WithRxJava.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.arun.arunrxjavaexploring.R;
import me.arun.arunrxjavaexploring.utils.CustomActionBar;
import me.arun.arunrxjavaexploring.utils.FragmentHelper.BackStackFragment;

public class PlaceListFragment extends BackStackFragment
{
    @BindView(R.id.actionBar)
    CustomActionBar actionBar;
    @BindView(R.id.rvListing)
    RecyclerView rvListing;
    @BindView(R.id.emptyFrame)
    FrameLayout emptyFrame;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.flBottomStrip)
    FrameLayout flBottomStrip;
    @BindView(R.id.navigationHome)
    FrameLayout navigationHome;
    Unbinder unbinder;

    public PlaceListFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View collectionView = inflater.inflate(R.layout.fragment_home, container, false);

        unbinder = ButterKnife.bind(this, collectionView);
        return collectionView;
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }

    @Override
    public void onPause()
    {
        super.onPause();
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        unbinder.unbind();

    }

}
