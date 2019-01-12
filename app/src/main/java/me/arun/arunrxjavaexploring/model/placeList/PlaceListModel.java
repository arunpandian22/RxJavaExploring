package me.arun.arunrxjavaexploring.model.placeList;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * A model class for the PlaceList listing Api Call
 */
public class PlaceListModel implements Parcelable {
    public static final Creator<PlaceListModel> CREATOR = new Creator<PlaceListModel>() {
        @Override
        public PlaceListModel createFromParcel(Parcel in) {
            return new PlaceListModel(in);
        }

        @Override
        public PlaceListModel[] newArray(int size) {
            return new PlaceListModel[size];
        }
    };
    private Lists lists = new Lists();
    private int total_page;
    private Long viewType;
    private String collection_desc;

    public PlaceListModel(Parcel in) {
        total_page = in.readInt();
        if (in.readByte() == 0) {
            viewType = null;
        } else {
            viewType = in.readLong();
        }
        collection_desc = in.readString();
    }

    public PlaceListModel() {

    }

    public String getCollection_desc() {
        return collection_desc;
    }

    public void setCollection_desc(String collection_desc) {
        this.collection_desc = collection_desc;
    }

    public Lists getLists() {
        return lists;
    }

    public void setLists(Lists lists) {
        this.lists = lists;
    }

    public int getTotalPage() {
        return total_page;
    }

    public void setTotalPage(int totalPage) {
        total_page = totalPage;
    }

    public Long getViewType() {
        return viewType;
    }

    public void setViewType(Long viewType) {
        this.viewType = viewType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(total_page);
        if (viewType == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(viewType);
        }
        dest.writeString(collection_desc);
    }
}
