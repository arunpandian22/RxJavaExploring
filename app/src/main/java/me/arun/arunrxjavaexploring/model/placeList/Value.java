package me.arun.arunrxjavaexploring.model.placeList;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


/**
 * A Model has the place list card item details
 */
@SuppressWarnings("unused")
public class Value implements Parcelable {

    @SerializedName("address_line1")
    private String mAddressLine1;
    @SerializedName("address_line2")
    private String mAddressLine2;
    @SerializedName("byline")
    private String mByline;
    @SerializedName("cost_categorization")
    private String mCostCategorization;
    @SerializedName("display_name")
    private String mDisplayName;
    @SerializedName("id")
    private int mId;
    @SerializedName("lang")
    private String mLang;
    @SerializedName("timing")
    private ArrayList<TimingValue> timing;
    @SerializedName("lat")
    private String mLat;
    @SerializedName("name")
    private String mName;
    @SerializedName("phone_no")
    private String mPhoneNo;
    @SerializedName("thumb_image")
    private String mThumbImage;
    @SerializedName("zip_code")
    private String mZipCode;

    public Value() {

    }

    protected Value(Parcel in) {
        mAddressLine1 = in.readString();
        mAddressLine2 = in.readString();
        mByline = in.readString();
        mCostCategorization = in.readString();
        mDisplayName = in.readString();
        mId = in.readInt();
        mLang = in.readString();
        timing = in.createTypedArrayList(TimingValue.CREATOR);
        mLat = in.readString();
        mName = in.readString();
        mPhoneNo = in.readString();
        mThumbImage = in.readString();
        mZipCode = in.readString();
    }

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

    public ArrayList<TimingValue> getTiming() {
        return timing;
    }

    public void setTiming(ArrayList<TimingValue> timing) {
        this.timing = timing;
    }

    public String getAddressLine1() {
        return mAddressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        mAddressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return mAddressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        mAddressLine2 = addressLine2;
    }

    public String getByline() {
        return mByline;
    }

    public void setByline(String byline) {
        mByline = byline;
    }

    public String getCostCategorization() {
        return mCostCategorization;
    }

    public void setCostCategorization(String costCategorization) {
        mCostCategorization = costCategorization;
    }

    public String getDisplayName() {
        return mDisplayName;
    }

    public void setDisplayName(String displayName) {
        mDisplayName = displayName;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getLang() {
        return mLang;
    }

    public void setLang(String lang) {
        mLang = lang;
    }

    public String getLat() {
        return mLat;
    }

    public void setLat(String lat) {
        mLat = lat;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPhoneNo() {
        return mPhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        mPhoneNo = phoneNo;
    }

    public String getThumbImage() {
        return mThumbImage;
    }

    public void setThumbImage(String thumbImage) {
        mThumbImage = thumbImage;
    }

    public String getZipCode() {
        return mZipCode;
    }

    public void setZipCode(String zipCode) {
        mZipCode = zipCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mAddressLine1);
        dest.writeString(mAddressLine2);
        dest.writeString(mByline);
        dest.writeString(mCostCategorization);
        dest.writeString(mDisplayName);
        dest.writeInt(mId);
        dest.writeString(mLang);
        dest.writeTypedList(timing);
        dest.writeString(mLat);
        dest.writeString(mName);
        dest.writeString(mPhoneNo);
        dest.writeString(mThumbImage);
        dest.writeString(mZipCode);
    }
}
