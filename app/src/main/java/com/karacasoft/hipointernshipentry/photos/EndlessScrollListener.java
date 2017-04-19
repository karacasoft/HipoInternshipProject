package com.karacasoft.hipointernshipentry.photos;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by mahmutkaraca on 4/19/17.
 */

public abstract class EndlessScrollListener extends RecyclerView.OnScrollListener {

    private int visibleViewThreshold = 5;

    private int currentPage = 1;
    private boolean loading = false;

    private Context context;

    private RecyclerView view;
    private RecyclerView.Adapter adapter;

    public EndlessScrollListener(Context context, RecyclerView view) {
        this.view = view;
        this.adapter = view.getAdapter();
        this.context = context;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int lastViewPosition = 0;
        if(layoutManager instanceof GridLayoutManager) {
             lastViewPosition = ((GridLayoutManager)recyclerView.getLayoutManager()).findLastVisibleItemPosition();
        } else {
            //TODO log error
            return;
        }


        if(lastViewPosition == RecyclerView.NO_POSITION) {
            // TODO handle this later
        } else {
            if (!loading && adapter.getItemCount() - visibleViewThreshold < lastViewPosition) {
                onLoadMore(view, currentPage);
                loading = true;
            }
        }
    }

    public abstract void onLoadMore(RecyclerView view, int page);

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
