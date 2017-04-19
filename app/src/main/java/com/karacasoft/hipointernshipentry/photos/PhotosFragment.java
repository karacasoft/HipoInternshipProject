package com.karacasoft.hipointernshipentry.photos;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.karacasoft.hipointernshipentry.R;
import com.karacasoft.hipointernshipentry.data.models.Photo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotosFragment extends Fragment implements PhotosContract.View {

    private PhotosContract.Presenter presenter;

    public PhotosFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.imagesRecyclerView)
    private RecyclerView imagesRecyclerView;
    private EndlessScrollListener endlessScrollListener;
    private PhotoListAdapter imagesAdapter;

    private ArrayList<Photo> photos = new ArrayList<>();

    private Unbinder unbinder;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_photos, container, false);
        unbinder = ButterKnife.bind(this, v);

        imagesAdapter = new PhotoListAdapter(getActivity(), photos);
        imagesRecyclerView.setAdapter(imagesAdapter);
        imagesRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        endlessScrollListener = new EndlessScrollListener(getActivity(), imagesRecyclerView) {
            @Override
            public void onLoadMore(RecyclerView view, int page) {
                presenter.onLoadMore(page);
            }
        };


        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        imagesRecyclerView.addOnScrollListener(endlessScrollListener);

        presenter.start();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        imagesRecyclerView.removeOnScrollListener(endlessScrollListener);
    }

    @Override
    public void setPresenter(PhotosContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showError(String message) {
        //TODO temporary Toast
        Toast.makeText(getActivity(), String.format("Error: %s", message),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(Throwable t) {
        showError(t.getMessage());
    }

    @Override
    public void showPhotos(List<Photo> photos) {
        int count = imagesAdapter.getItemCount();
        this.photos.clear();
        imagesAdapter.notifyItemRangeRemoved(0, count);
        this.photos.addAll(photos);
        count = photos.size();
        imagesAdapter.notifyItemRangeInserted(0, count);
    }

    @Override
    public void addPhotos(List<Photo> photos, int page) {
        int count = photos.size();
        int oldSize = this.photos.size();
        this.photos.addAll(photos);
        this.imagesAdapter.notifyItemRangeInserted(oldSize, count);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public static class PhotoListAdapter extends RecyclerView.Adapter<PhotoListAdapter.ViewHolder> {

        private List<Photo> photoList;
        private Context context;

        public PhotoListAdapter(Context context, List<Photo> photoList) {
            this.context = context;
            this.photoList = photoList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);

            View listItemView = inflater.inflate(R.layout.list_item_photo, parent, false);

            return new ViewHolder(listItemView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Photo p = photoList.get(position);
            holder.txtPhotoTitle.setText(p.getTitle());
            //TODO load image in an image loader thread
        }

        @Override
        public int getItemCount() {
            return photoList.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.txtPhotoTitle)
            public TextView txtPhotoTitle;
            @BindView(R.id.imgPhoto)
            public ImageView imgPhoto;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }

        }
    }
}
