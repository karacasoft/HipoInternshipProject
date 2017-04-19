package com.karacasoft.hipointernshipentry.data;

import com.karacasoft.hipointernshipentry.data.apiresult.RecentPhotosResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mahmutkaraca on 4/18/17.
 */

public interface FlickrService {
    //TODO encrypt api key into the application
    @GET("services/rest/?method=flickr.photos.getRecent&extras=url_m%2C+url_o&format=json&nojsoncallback=1")
    Call<RecentPhotosResult> getRecentPhotos(@Query("page") int page, @Query("per_page") int resultsPerPage);

    @GET("services/rest/?method=flickr.photos.search&extras=url_m%2C+url_o&format=json&nojsoncallback=1")
    Call<RecentPhotosResult> search(@Query("text") String text, @Query("page") int page, @Query("per_page") int resultsPerPage);
}
