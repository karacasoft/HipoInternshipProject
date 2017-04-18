package com.karacasoft.hipointernshipentry;

import com.karacasoft.hipointernshipentry.data.FlickrService;
import com.karacasoft.hipointernshipentry.data.apiresult.RecentPhotosResult;

import org.junit.Test;

import java.io.IOException;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.assertTrue;

/**
 * Created by mahmutkaraca on 4/18/17.
 */

public class APIEndpointTest {
    @Test
    public void flickrService_searchTest() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.flickr.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FlickrService service = retrofit.create(FlickrService.class);

        RecentPhotosResult result = service.search("sunset", 1, 10).execute().body();

        assertTrue(result != null);
        assertTrue(result.getPhotos().getPhoto().size() == 10);
    }

    @Test
    public void flickrService_recentPhotosTest() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.flickr.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FlickrService service = retrofit.create(FlickrService.class);

        RecentPhotosResult result = service.getRecentPhotos(1, 10).execute().body();

        assertTrue(result != null);
        assertTrue(result.getPhotos().getPhoto().size() == 10);
    }
}
