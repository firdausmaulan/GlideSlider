package com.glide.slider.library.SliderTypes;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.glide.slider.library.R;

import java.io.File;

/**
 * When you want to make your own slider view, you must extends from this class.
 * BaseSliderView provides some useful methods.
 * I provide two example: {@link com.glide.slider.library.SliderTypes.DefaultSliderView} and
 * {@link com.glide.slider.library.SliderTypes.TextSliderView}
 */
public abstract class BaseSliderView {

    public Context mContext;

    private Bundle mBundle;

    private String mUrl;
    private File mFile;
    private int mRes;

    public OnSliderClickListener mOnSliderClickListener;

    public ImageLoadListener mLoadListener;

    private String mDescription;

    private RequestOptions mRequestOptions;

    private boolean isProgressBarVisible = false;

    private int mBackgroundColor = Color.BLACK;

    /**
     * Ctor
     *
     * @param context
     */
    public BaseSliderView(Context context) {
        mContext = context;
    }

    /**
     * the description of a slider image.
     *
     * @param description
     * @return
     */
    public BaseSliderView description(String description) {
        mDescription = description;
        return this;
    }

    /**
     * set a url as a image that preparing to load
     *
     * @param url
     * @return
     */
    public BaseSliderView image(String url) {
        if (mFile != null || mRes != 0) {
            throw new IllegalStateException("Call multi image function," +
                    "you only have permission to call it once");
        }
        mUrl = url;
        return this;
    }

    /**
     * set a file as a image that will to load
     *
     * @param file
     * @return
     */
    public BaseSliderView image(File file) {
        if (mUrl != null || mRes != 0) {
            throw new IllegalStateException("Call multi image function," +
                    "you only have permission to call it once");
        }
        mFile = file;
        return this;
    }

    public BaseSliderView image(int res) {
        if (mUrl != null || mFile != null) {
            throw new IllegalStateException("Call multi image function," +
                    "you only have permission to call it once");
        }
        mRes = res;
        return this;
    }

    /**
     * lets users add a bundle of additional information
     *
     * @param bundle
     * @return
     */
    public BaseSliderView bundle(Bundle bundle) {
        mBundle = bundle;
        return this;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getDescription() {
        return mDescription;
    }

    public Context getContext() {
        return mContext;
    }

    /**
     * set a slider image click listener
     *
     * @param l
     * @return
     */
    public BaseSliderView setOnSliderClickListener(OnSliderClickListener l) {
        mOnSliderClickListener = l;
        return this;
    }

    /**
     * set Glide RequestOption
     *
     * @param requestOption
     * @return self
     */
    public BaseSliderView setRequestOption(RequestOptions requestOption) {
        mRequestOptions = requestOption;
        return this;
    }

    /**
     * set Progressbar visible or gone
     *
     * @param isVisible
     */
    public BaseSliderView setProgressBarVisible(boolean isVisible) {
        isProgressBarVisible = isVisible;
        return this;
    }

    /**
     * set Background Color
     *
     * @param color
     */
    public BaseSliderView setBackgroundColor(int color) {
        mBackgroundColor = color;
        return this;
    }

    /**
     * When you want to implement your own slider view, please call this method in the end in `getView()` method
     *
     * @param v               the whole view
     * @param targetImageView where to place image
     */
    protected void bindEventAndShow(final View v, AppCompatImageView targetImageView) {
        final BaseSliderView me = this;

        v.findViewById(R.id.glide_slider_background).setBackgroundColor(mBackgroundColor);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnSliderClickListener != null) {
                    mOnSliderClickListener.onSliderClick(me);
                }
            }
        });

        if (targetImageView == null)
            return;

        if (mLoadListener != null) {
            mLoadListener.onStart(me);
        }

        final ProgressBar mProgressBar = v.findViewById(R.id.loading_bar);
        if (isProgressBarVisible) {
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.GONE);
        }

        Object imageToLoad = null;
        if (mUrl != null) {
            imageToLoad = mUrl;
        } else if (mFile != null) {
            imageToLoad = mFile;
        } else if (mRes != 0) {
            imageToLoad = mRes;
        }

        RequestBuilder<Drawable> requestBuilder = Glide.with(mContext).as(Drawable.class);

        if (imageToLoad != null) {
            if (mRequestOptions != null) {
                requestBuilder.load(imageToLoad)
                        .apply(mRequestOptions)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e,
                                                        Object model,
                                                        Target<Drawable> target,
                                                        boolean isFirstResource) {
                                mProgressBar.setVisibility(View.GONE);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource,
                                                           Object model,
                                                           Target<Drawable> target,
                                                           DataSource dataSource,
                                                           boolean isFirstResource) {
                                mProgressBar.setVisibility(View.GONE);
                                return false;
                            }
                        }).into(targetImageView);
            } else {
                requestBuilder.load(imageToLoad)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e,
                                                        Object model,
                                                        Target<Drawable> target,
                                                        boolean isFirstResource) {
                                mProgressBar.setVisibility(View.GONE);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource,
                                                           Object model,
                                                           Target<Drawable> target,
                                                           DataSource dataSource,
                                                           boolean isFirstResource) {
                                mProgressBar.setVisibility(View.GONE);
                                return false;
                            }
                        }).into(targetImageView);
            }
        }
    }

    /**
     * the extended class have to implement getView(), which is called by the adapter,
     * every extended class response to render their own view.
     *
     * @return
     */
    public abstract View getView();

    /**
     * set a listener to get a message , if load error.
     *
     * @param l
     */
    public void setOnImageLoadListener(ImageLoadListener l) {
        mLoadListener = l;
    }

    public interface OnSliderClickListener {
        public void onSliderClick(BaseSliderView slider);
    }

    /**
     * when you have some extra information, please put it in this bundle.
     *
     * @return
     */
    public Bundle getBundle() {
        return mBundle;
    }

    public interface ImageLoadListener {
        public void onStart(BaseSliderView target);

        public void onEnd(boolean result, BaseSliderView target);
    }
}
