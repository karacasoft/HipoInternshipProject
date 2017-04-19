package com.karacasoft.hipointernshipentry;

import com.karacasoft.hipointernshipentry.data.FlickrService;
import com.karacasoft.hipointernshipentry.data.apiresult.PhotosResult;
import com.karacasoft.hipointernshipentry.util.APIKeyProvider;
import com.karacasoft.hipointernshipentry.util.RetrofitUtils;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * Created by mahmutkaraca on 4/18/17.
 */

public class APIEndpointTest {
    @Test
    public void flickrService_searchTest() throws IOException {
        FlickrService service = RetrofitUtils.getFlickrService();

        PhotosResult result = service.search("sunset", 1, 10).execute().body();

        assertTrue(result != null);
        assertTrue(result.getPhotos().getPhoto().size() == 10);
    }

    @Test
    public void flickrService_recentPhotosTest() throws IOException {
        FlickrService service = RetrofitUtils.getFlickrService();

        PhotosResult result = service.getRecentPhotos(1, 10).execute().body();

        assertTrue(result != null);
        assertTrue(result.getPhotos().getPhoto().size() == 10);
    }

    @Test
    public void apiKeyTest() {
        assertTrue(APIKeyProvider.getAPIKey().equals("17b6bf0c241f5a13d1ea7be5bd0462c0"));
    }
}
