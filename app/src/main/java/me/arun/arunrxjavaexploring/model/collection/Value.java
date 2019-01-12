package me.arun.arunrxjavaexploring.model.collection;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


/**
 * A Model has the collection item details
 */
@SuppressWarnings("unused")
public class Value implements Parcelable {

    public static final Creator<Value> CREATOR = new Creator<Value>() {
        @Override
        public Value createFromParcel(Parcel in) {
            return new Value(in);
        }

        @Override
        public Value[] newArray(int size) {
            return new Value[size];
        }
    };
    @SerializedName("appstore_key")
    private Object mAppstoreKey;
    @SerializedName("id")
    private int mId;
    @SerializedName("image")
    private String mImage;
    @SerializedName("is_purchase")

    private Boolean is_purchase;
    @SerializedName("is_free")
    private Boolean is_free;
    @SerializedName("list_count")
    private Long mListCount;
    @SerializedName("name")
    private String mName;
    @SerializedName("playstore_key")
    private String mPlaystoreKey;
    @SerializedName("updatedAt")
    private String updatedAt;

    public Value(Parcel in) {
        mId = in.readInt();
        mImage = in.readString();
        byte tmpIs_purchase = in.readByte();
        is_purchase = tmpIs_purchase == 0 ? null : tmpIs_purchase == 1;
        byte tmpIs_free = in.readByte();
        is_free = tmpIs_free == 0 ? null : tmpIs_free == 1;
        if (in.readByte() == 0) {
            mListCount = null;
        } else {
            mListCount = in.readLong();
        }
        mName = in.readString();
        mPlaystoreKey = in.readString();
        updatedAt = in.readString();
    }

    public Value() {

    }

    public Boolean getIs_free() {
        return is_free;
    }

    public void setIs_free(Boolean is_free) {
        this.is_free = is_free;
    }


    public String getUpdatedAt() {
        return updatedAt;
    }


    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Object getAppstoreKey() {
        return mAppstoreKey;
    }

    public void setAppstoreKey(Object appstoreKey) {
        mAppstoreKey = appstoreKey;
    }


    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public Boolean getIs_purchase() {
        return is_purchase;
    }

    public void setIs_purchase(Boolean is_purchase) {
        this.is_purchase = is_purchase;
    }

    public Long getListCount() {
        return mListCount;
    }

    public void setListCount(Long listCount) {
        mListCount = listCount;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPlaystoreKey() {
        return mPlaystoreKey;
    }

    public void setPlaystoreKey(String playstoreKey) {
        mPlaystoreKey = playstoreKey;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mImage);
        dest.writeByte((byte) (is_purchase == null ? 0 : is_purchase ? 1 : 2));
        dest.writeByte((byte) (is_free == null ? 0 : is_free ? 1 : 2));
        if (mListCount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mListCount);
        }
        dest.writeString(mName);
        dest.writeString(mPlaystoreKey);
        dest.writeString(updatedAt);
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }
}
