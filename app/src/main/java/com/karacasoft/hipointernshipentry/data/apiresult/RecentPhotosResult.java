package com.karacasoft.hipointernshipentry.data.apiresult;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.karacasoft.hipointernshipentry.data.models.Photos;

public class RecentPhotosResult {

    @SerializedName("photos")
    @Expose
    private Photos photos;
    @SerializedName("stat")
    @Expose
    private String stat;

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

}
