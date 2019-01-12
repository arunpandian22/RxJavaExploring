package me.arun.arunrxjavaexploring;

/**
 * Created by Arun Pandian M on 27/December/2018
 * arunsachin222@gmail.com
 * Chennai
 */
public class ModelFeatureCategory {

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
@FeatureCategory
    String imageUri,title;


    public ModelFeatureCategory( String imageUri, @FeatureCategory String title) {
        this.imageUri = imageUri;
        this.title = title;
    }
}