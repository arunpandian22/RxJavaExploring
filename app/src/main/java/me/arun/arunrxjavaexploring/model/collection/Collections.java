package me.arun.arunrxjavaexploring.model.collection;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * A Model has the collection common details
 */
@SuppressWarnings("unused")
public class Collections {

    @SerializedName("action")
    private String mAction;
    @SerializedName("key")
    private String mKey;
    @SerializedName("type")
    private String mType;
    @SerializedName("value")
    private ArrayList<Value> mValue = new ArrayList<>();
    @SerializedName("hashMap")
    private String hashMap;

    public String getHashMap() {
        return hashMap;
    }

    public void setHashMap(String hashMap) {
        this.hashMap = hashMap;
    }

    public String getAction() {
        return mAction;
    }

    public void setAction(String action) {
        mAction = action;
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        mKey = key;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public ArrayList<Value> getValue() {
        return mValue;
    }

    public void setValue(ArrayList<Value> value) {
        mValue = value;
    }

}
