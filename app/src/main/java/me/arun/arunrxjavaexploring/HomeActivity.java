package me.arun.arunrxjavaexploring;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toolbar;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
public class HomeActivity extends AppCompatActivity
{
    List<ModelFeatureCategory> modelFeatureCategories = new ArrayList<>();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    FeatureCategoryAdapter featureCategoryAdapter;
    @BindView(R.id.rvFeatureCategory)
    RecyclerView rvFeatureCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        toolbarSetup();
        setFeatureCategoryList();
        featureCategoryAdapter = new FeatureCategoryAdapter(this, modelFeatureCategories);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(this,2);
        rvFeatureCategory.setLayoutManager(layoutManager);
        rvFeatureCategory.setAdapter(featureCategoryAdapter);
    }

    public void toolbarSetup()
    {
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimary));
        setActionBar(toolbar);
    }


    public void setFeatureCategoryList()
    {
        ModelFeatureCategory sample = new ModelFeatureCategory("@drawable/ic_network_request", FeatureCategory.SAMPLE);
        modelFeatureCategories.add(sample);
        ModelFeatureCategory sampleOperator = new ModelFeatureCategory("@drawable/ic_network_request", FeatureCategory.OPERATOR);
        modelFeatureCategories.add(sampleOperator);
        ModelFeatureCategory roomDb= new ModelFeatureCategory("@drawable/ic_network_request", FeatureCategory.ROOMDB);
        modelFeatureCategories.add(roomDb);
        ModelFeatureCategory rxBus= new ModelFeatureCategory("@drawable/ic_network_request",FeatureCategory.RXBUS);
        modelFeatureCategories.add(rxBus);
        ModelFeatureCategory pagination = new ModelFeatureCategory("@drawable/ic_network_request",FeatureCategory.PAGINATION);
        modelFeatureCategories.add(pagination);
        ModelFeatureCategory network = new ModelFeatureCategory("@drawable/ic_network_request",FeatureCategory.NETWORK);
        modelFeatureCategories.add(network);
    }
}
