
package me.arun.arunrxjavaexploring.model.placeList;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * A model class to opening Timing values of place items
 */
public class TimingValue implements Parcelable {

    private String item;
    private String key;

    protected TimingValue(Parcel in) {
        item = in.readString();
        key = in.readString();
    }

    public static final Creator<TimingValue> CREATOR = new Creator<TimingValue>() {
        @Override
        public TimingValue createFromParcel(Parcel in) {
            return new TimingValue(in);
        }

        @Override
        public TimingValue[] newArray(int size) {
            return new TimingValue[size];
        }
    };

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(item);
        dest.writeString(key);
    }
}
