
package me.arun.arunrxjavaexploring.model.collection;


import com.google.gson.annotations.SerializedName;

/**
 * A model class for the collection listing Api Call
 */
@SuppressWarnings("unused")
public class CollectionListModel {

    @SerializedName("collections")
    private Collections mCollections;
    @SerializedName("viewType")
    private long mViewType;

    public Collections getCollections() {
        return mCollections;
    }

    public void setCollections(Collections collections) {
        mCollections = collections;
    }

    public long getViewType() {
        return mViewType;
    }

    public void setViewType(long viewType) {
        mViewType = viewType;
    }

}
