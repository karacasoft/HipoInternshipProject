package com.karacasoft.hipointernshipentry.search;

import com.karacasoft.hipointernshipentry.data.apiresult.PhotosResult;
import com.karacasoft.hipointernshipentry.data.models.Photo;
import com.karacasoft.hipointernshipentry.photos.PhotosContract;
import com.karacasoft.hipointernshipentry.photos.PhotosPresenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mahmutkaraca on 4/20/17.
 */

public class SearchPresenter extends PhotosPresenter implements SearchViewInterface {

    private String query = "";
    /* onLoadMore should only work when we are searching */
    private boolean searching = false;

    public SearchPresenter(PhotosContract.View view) {
        super(view);
    }

    /* We do not want to refresh on start, when searching...*/
    @Override
    public void start() {
        /* But set the attached status of the fragment */
        attached = true;
    }


    @Override
    public void onLoadMore(final int page) {
        /* Do not trigger loading if not searching */
        if(!searching || query.trim().isEmpty()) {
            view.setLoading(false);
        } else {
            flickrService.search(query, page, RESULTS_PER_PAGE)
                    .enqueue(new Callback<PhotosResult>() {
                        @Override
                        public void onResponse(Call<PhotosResult> call, Response<PhotosResult> response) {
                            if(attached) {
                                if(response.isSuccessful()) {
                                    List<Photo> photos = response.body().getPhotos().getPhoto();
                                    view.addPhotos(photos, page);
                                    view.setCurrentPage(page + 1);
                                } else {
                                    view.showError("Network Error!");
                                    //TODO change this
                                }
                                view.setLoading(false);
                            }
                        }

                        @Override
                        public void onFailure(Call<PhotosResult> call, Throwable t) {
                            if(attached) {
                                view.showError("Network Error!");
                                //TODO change this
                                view.setLoading(false);
                            }
                        }
                    });

        }
    }

    @Override
    public void onRefresh() {
        if(!searching || query.trim().isEmpty()) {
            view.setLoading(false);
            view.setRefreshing(false);
        } else {
            flickrService.search(query, 1, RESULTS_PER_PAGE)
                    .enqueue(new Callback<PhotosResult>() {
                        @Override
                        public void onResponse(Call<PhotosResult> call, Response<PhotosResult> response) {
                            if(attached) {
                                if(response.isSuccessful()) {
                                    List<Photo> photos = response.body().getPhotos().getPhoto();
                                    view.showPhotos(photos);
                                    view.setCurrentPage(2);
                                } else {
                                    view.showError("Network Error!");
                                    //TODO change this
                                }
                                view.setLoading(false);
                            }
                        }

                        @Override
                        public void onFailure(Call<PhotosResult> call, Throwable t) {
                            if(attached) {
                                view.showError("Network Error!");
                                //TODO change this
                                view.setLoading(false);
                            }
                        }
                    });
        }
    }

    @Override
    public void onClose() {

    }

    @Override
    public boolean onSearch(String text) {
        if(!text.trim().isEmpty()) {
            query = text.trim();
            searching = true;
            onRefresh();
        }
        return true;
    }

    @Override
    public boolean onTextChanged(String newText) {
        return false;
    }
}
