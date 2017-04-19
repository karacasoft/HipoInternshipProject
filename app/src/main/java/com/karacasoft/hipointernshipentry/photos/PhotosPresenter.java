package com.karacasoft.hipointernshipentry.photos;

import com.karacasoft.hipointernshipentry.data.FlickrService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mahmutkaraca on 4/18/17.
 */

public class PhotosPresenter implements PhotosContract.Presenter {

    public static final int RESULTS_PER_PAGE = 10;

    private PhotosContract.View view;
    private FlickrService flickrService;

    public PhotosPresenter(PhotosContract.View view) {
        this.view = view;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.flickr.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        flickrService = retrofit.create(FlickrService.class);
    }

    @Override
    public void start() {
        onRefresh();
    }

    @Override
    public void stop() {

    }

    @Override
    public void onLoadMore(int page) {

    }

    @Override
    public void onRefresh() {

    }
}
