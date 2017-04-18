package com.karacasoft.hipointernshipentry.photos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karacasoft.hipointernshipentry.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotosFragment extends Fragment implements PhotosContract.View {

    private PhotosContract.Presenter presenter;

    public PhotosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_photos, container, false);
    }

    @Override
    public void setPresenter(PhotosContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
