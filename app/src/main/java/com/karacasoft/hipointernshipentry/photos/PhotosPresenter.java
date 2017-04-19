package com.karacasoft.hipointernshipentry.photos;

import com.karacasoft.hipointernshipentry.data.FlickrService;
import com.karacasoft.hipointernshipentry.data.apiresult.PhotosResult;
import com.karacasoft.hipointernshipentry.data.models.Photo;
import com.karacasoft.hipointernshipentry.util.RetrofitUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mahmutkaraca on 4/18/17.
 */

public class PhotosPresenter implements PhotosContract.Presenter {

    public static final int RESULTS_PER_PAGE = 10;

    protected PhotosContract.View view;
    protected FlickrService flickrService;

    protected boolean attached = false;

    public PhotosPresenter(PhotosContract.View view) {
        this.view = view;

        flickrService = RetrofitUtils.getFlickrService();
    }

    @Override
    public void start() {
        attached = true;
        onRefresh();
    }

    @Override
    public void stop() {
        attached = false;
    }

    @Override
    public void onLoadMore(final int page) {
        flickrService.getRecentPhotos(page, RESULTS_PER_PAGE)
                .enqueue(new Callback<PhotosResult>() {
                    @Override
                    public void onResponse(Call<PhotosResult> call, Response<PhotosResult> response) {
                        if(attached) {
                            if (response.isSuccessful()) {
                                PhotosResult result = response.body();
                                List<Photo> photoList = result.getPhotos().getPhoto();
                                view.addPhotos(photoList, page);
                                view.setCurrentPage(page + 1);
                            } else {
                                view.showError("Network error!");
                                //TODO change this message to dynamic error from server
                            }
                            view.setLoading(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<PhotosResult> call, Throwable t) {
                        if(attached) {
                            view.showError(t);
                            view.setLoading(false);
                        }
                    }
                });
    }

    @Override
    public void onRefresh() {
        flickrService.getRecentPhotos(1, RESULTS_PER_PAGE)
                .enqueue(new Callback<PhotosResult>() {
                    @Override
                    public void onResponse(Call<PhotosResult> call, Response<PhotosResult> response) {
                        if(attached) {
                            if (response.isSuccessful()) {
                                PhotosResult result = response.body();
                                List<Photo> photoList = result.getPhotos().getPhoto();
                                view.showPhotos(photoList);
                                view.setCurrentPage(2);
                            } else {
                                view.showError("Network error!");
                                //TODO change this message to dynamic error from server
                            }
                            view.setRefreshing(false);
                            view.setLoading(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<PhotosResult> call, Throwable t) {
                        if(attached) {
                            view.showError(t);
                            view.setRefreshing(false);
                            view.setLoading(false);
                        }
                    }
                });
    }
}
