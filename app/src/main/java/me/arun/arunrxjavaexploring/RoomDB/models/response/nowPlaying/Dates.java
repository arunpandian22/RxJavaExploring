
package me.arun.arunrxjavaexploring.RoomDB.models.response.nowPlaying;

import com.google.gson.annotations.Expose;

//@SuppressWarnings("unused")
public class Dates {

    @Expose
    private String maximum;
    @Expose
    private String minimum;

    public String getMaximum() {
        return maximum;
    }

    public void setMaximum(String maximum) {
        this.maximum = maximum;
    }

    public String getMinimum() {
        return minimum;
    }

    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }

}
