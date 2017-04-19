package com.karacasoft.hipointernshipentry.photos;

import android.content.Context;
import android.support.annotation.AnimRes;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by mahmutkaraca on 4/19/17.
 */

public class PhotosItemAnimator extends SimpleItemAnimator {

    private int animAdd;
    private int animRemove;
    private Context context;

    private AtomicInteger runningAnimCount = new AtomicInteger(0);

    public PhotosItemAnimator(Context context, @AnimRes int animAdd, @AnimRes int animRemove) {
        this.animAdd = animAdd;
        this.animRemove = animRemove;

        this.context = context;
    }

    @Override
    public boolean animateRemove(final RecyclerView.ViewHolder holder) {
        Animation removeAnim = AnimationUtils.loadAnimation(context, animRemove);
        removeAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                dispatchRemoveFinished(holder);
                runningAnimCount.addAndGet(-1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        runningAnimCount.addAndGet(1);
        holder.itemView.startAnimation(removeAnim);
        return true;
    }

    @Override
    public boolean animateAdd(final RecyclerView.ViewHolder holder) {
        Animation addAnim = AnimationUtils.loadAnimation(context, animAdd);
        addAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                dispatchRemoveFinished(holder);
                runningAnimCount.addAndGet(-1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        runningAnimCount.addAndGet(1);
        holder.itemView.startAnimation(addAnim);
        return true;
    }

    @Override
    public boolean animateMove(RecyclerView.ViewHolder holder, int fromX, int fromY, int toX, int toY) {
        dispatchMoveFinished(holder);
        return false;
    }

    @Override
    public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromLeft, int fromTop, int toLeft, int toTop) {
        dispatchChangeFinished(newHolder, false);
        return false;
    }

    @Override
    public void runPendingAnimations() {

    }

    @Override
    public void endAnimation(RecyclerView.ViewHolder item) {
        item.itemView.clearAnimation();
        //TODO check if this method calls onAnimationEnd()
    }

    @Override
    public void endAnimations() {

    }

    @Override
    public boolean isRunning() {
        return runningAnimCount.get() > 0;
    }
}
