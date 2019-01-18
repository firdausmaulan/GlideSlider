package com.glide.slider.library;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.glide.slider.library.SliderTypes.BaseSliderView;

import java.util.ArrayList;

/**
 * A slider adapter
 */
public class SliderAdapter extends PagerAdapter implements BaseSliderView.ImageLoadListener {

    private ArrayList<BaseSliderView> mImageContents;

    SliderAdapter(Context context) {
        mImageContents = new ArrayList<BaseSliderView>();
    }

    <T extends BaseSliderView> void addSlider(T slider) {
        slider.setOnImageLoadListener(this);
        mImageContents.add(slider);
        notifyDataSetChanged();
    }

    BaseSliderView getSliderView(int position) {
        if (position < 0 || position >= mImageContents.size()) {
            return null;
        } else {
            return mImageContents.get(position);
        }
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    private <T extends BaseSliderView> void removeSlider(T slider) {
        if (mImageContents.contains(slider)) {
            mImageContents.remove(slider);
            notifyDataSetChanged();
        }
    }

    void removeSliderAt(int position) {
        if (mImageContents.size() > position) {
            mImageContents.remove(position);
            notifyDataSetChanged();
        }
    }

    void removeAllSliders() {
        mImageContents.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mImageContents.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        BaseSliderView b = mImageContents.get(position);
        View v = b.getView();
        container.addView(v);
        return v;
    }

    @Override
    public void onStart(BaseSliderView target) {

    }

    /**
     * When image download error, then remove.
     *
     * @param result
     * @param target
     */
    @Override
    public void onEnd(boolean result, BaseSliderView target) {
        if (result) return;

        for (BaseSliderView slider : mImageContents) {
            if (slider.equals(target)) {
                removeSlider(target);
                break;
            }
        }
    }

    @Override
    public void onDrawableLoaded(Drawable drawable) {

    }
}