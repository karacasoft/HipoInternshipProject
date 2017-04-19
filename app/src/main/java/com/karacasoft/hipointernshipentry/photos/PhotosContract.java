package com.karacasoft.hipointernshipentry.photos;

import com.karacasoft.hipointernshipentry.BasePresenter;
import com.karacasoft.hipointernshipentry.BaseView;
import com.karacasoft.hipointernshipentry.data.models.Photo;

import java.util.List;

/**
 * Created by mahmutkaraca on 4/18/17.
 */

public interface PhotosContract {

    interface View extends BaseView<Presenter> {
        void showPhotos(List<Photo> photos);
        void addPhotos(List<Photo> photos, int page);
    }

    interface Presenter extends BasePresenter {
        void onLoadMore(int page);
        void onRefresh();
    }

}
