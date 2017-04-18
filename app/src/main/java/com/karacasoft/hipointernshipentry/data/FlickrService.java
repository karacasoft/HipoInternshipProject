package com.karacasoft.hipointernshipentry.data;

import com.karacasoft.hipointernshipentry.data.apiresult.RecentPhotosResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by mahmutkaraca on 4/18/17.
 */

public interface FlickrService {
    //TODO encrypt api key into the application
    @GET("services/rest/?method=flickr.photos.getRecent&api_key=bead015" +
            "3d50ff3255a9367c18e4973a6&extras=url_m%2C+url_o&per_page={per_page}&page={page}&format=json&nojsoncallback=1")
    Call<RecentPhotosResult> getRecentPhotos(@Path("page") int page, @Path("per_page") int results_per_page);
}
