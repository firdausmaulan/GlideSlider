package com.glide.slider.library.SliderTypes;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.RequestOptions;
import com.glide.slider.library.R;

import java.io.File;

import static android.view.View.VISIBLE;

/**
 * When you want to make your own slider view, you must extends from this class.
 * BaseSliderView provides some useful methods.
 * I provide two example: {@link com.glide.slider.library.SliderTypes.DefaultSliderView} and
 * {@link com.glide.slider.library.SliderTypes.TextSliderView}
 * if you want to show progressbar, you just need to set a progressbar id as @+id/loading_bar.
 */
public abstract class BaseSliderView {

    protected Context mContext;

    private Bundle mBundle;

    /**
     * Error place holder image.
     */
    private int mErrorPlaceHolderRes;

    /**
     * Empty imageView placeholder.
     */
    private int mEmptyPlaceHolderRes;

    private String mUrl;
    private File mFile;
    private int mRes;

    protected OnSliderClickListener mOnSliderClickListener;

    private boolean mErrorDisappear;

    private ImageLoadListener mLoadListener;

    private String mDescription;

    private @ColorInt int mBackgroundColor = 0x77000000;

    private int visibility = VISIBLE;

    private BitmapTransformation mBitmapTransformation;

    /**
     * Ctor
     * @param context
     */
    protected BaseSliderView(Context context) {
        mContext = context;
    }

    /**
     * the placeholder image when loading image from url or file.
     *
     * @param resId Image resource id
     * @return
     */
    public BaseSliderView empty(int resId) {
        mEmptyPlaceHolderRes = resId;
        return this;
    }

    /**
     * determine whether remove the image which failed to download or load from file
     *
     * @param disappear
     * @return
     */
    public BaseSliderView errorDisappear(boolean disappear) {
        mErrorDisappear = disappear;
        return this;
    }

    /**
     * if you set errorDisappear false, this will set a error placeholder image.
     *
     * @param resId image resource id
     * @return
     */
    public BaseSliderView error(int resId) {
        mErrorPlaceHolderRes = resId;
        return this;
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
     * set background color for description
     *
     * @param backgroundColor
     * @return
     */
    public BaseSliderView descriptionBackground(@ColorInt int backgroundColor) {
        mBackgroundColor = backgroundColor;
        return this;
    }

    /**
     * set visibily for description
     *
     * @param visibility
     * @return
     */
    public BaseSliderView descriptionVisibility(int visibility) {
        this.visibility = visibility;
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

    public boolean isErrorDisappear() {
        return mErrorDisappear;
    }

    public int getEmpty() {
        return mEmptyPlaceHolderRes;
    }

    public int getError() {
        return mErrorPlaceHolderRes;
    }

    public String getDescription() {
        return mDescription;
    }

    public @ColorInt int getDescriptionBackground() {
        return mBackgroundColor;
    }

    public int getDescriptionVisibility() {
        return visibility;
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
     * set Glide BitmapTransformation
     * @param bitmapTransformation transformation to perform
     * @return self
     */
    public BaseSliderView setBitmapTransformation(BitmapTransformation bitmapTransformation) {
        mBitmapTransformation = bitmapTransformation;
        return this;
    }

    /**
     * When you want to implement your own slider view, please call this method in the end in `getView()` method
     *
     * @param v               the whole view
     * @param targetImageView where to place image
     */
    protected void bindEventAndShow(final View v, ImageView targetImageView) {
        final BaseSliderView me = this;

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

        v.findViewById(R.id.loading_bar).setVisibility(View.INVISIBLE);

        if (mBitmapTransformation != null) {
            if (mUrl != null) {
                if (getEmpty() != 0 && getError() != 0) {
                    Glide.with(mContext).load(mUrl).apply(RequestOptions.bitmapTransform(mBitmapTransformation).placeholder(getEmpty()).error(getError())).into(targetImageView);
                } else if (getEmpty() != 0) {
                    Glide.with(mContext).load(mUrl).apply(RequestOptions.bitmapTransform(mBitmapTransformation).placeholder(getEmpty())).into(targetImageView);
                } else if (getError() != 0) {
                    Glide.with(mContext).load(mUrl).apply(RequestOptions.bitmapTransform(mBitmapTransformation).error(getError())).into(targetImageView);
                } else {
                    Glide.with(mContext).load(mUrl).apply(RequestOptions.bitmapTransform(mBitmapTransformation)).into(targetImageView);
                }
            } else if (mFile != null) {
                if (getEmpty() != 0 && getError() != 0) {
                    Glide.with(mContext).load(mFile).apply(RequestOptions.bitmapTransform(mBitmapTransformation).placeholder(getEmpty()).error(getError())).into(targetImageView);
                } else if (getEmpty() != 0) {
                    Glide.with(mContext).load(mFile).apply(RequestOptions.bitmapTransform(mBitmapTransformation).placeholder(getEmpty())).into(targetImageView);
                } else if (getError() != 0) {
                    Glide.with(mContext).load(mFile).apply(RequestOptions.bitmapTransform(mBitmapTransformation).error(getError())).into(targetImageView);
                } else {
                    Glide.with(mContext).load(mFile).apply(RequestOptions.bitmapTransform(mBitmapTransformation)).into(targetImageView);
                }
            } else if (mRes != 0) {
                if (getEmpty() != 0 && getError() != 0) {
                    Glide.with(mContext).load(mRes).apply(RequestOptions.bitmapTransform(mBitmapTransformation).placeholder(getEmpty()).error(getError())).into(targetImageView);
                } else if (getEmpty() != 0) {
                    Glide.with(mContext).load(mRes).apply(RequestOptions.bitmapTransform(mBitmapTransformation).placeholder(getEmpty())).into(targetImageView);
                } else if (getError() != 0) {
                    Glide.with(mContext).load(mRes).apply(RequestOptions.bitmapTransform(mBitmapTransformation).error(getError())).into(targetImageView);
                } else {
                    Glide.with(mContext).load(mRes).apply(RequestOptions.bitmapTransform(mBitmapTransformation)).into(targetImageView);
                }
            }
        } else {
            if (mUrl != null) {
                if (getEmpty() != 0 && getError() != 0) {
                    Glide.with(mContext).load(mUrl).apply(new RequestOptions().placeholder(getEmpty()).error(getError())).into(targetImageView);
                } else if (getEmpty() != 0) {
                    Glide.with(mContext).load(mUrl).apply(new RequestOptions().placeholder(getEmpty())).into(targetImageView);
                } else if (getError() != 0) {
                    Glide.with(mContext).load(mUrl).apply(new RequestOptions().error(getError())).into(targetImageView);
                } else {
                    Glide.with(mContext).load(mUrl).into(targetImageView);
                }
            } else if (mFile != null) {
                if (getEmpty() != 0 && getError() != 0) {
                    Glide.with(mContext).load(mFile).apply(new RequestOptions().placeholder(getEmpty()).error(getError())).into(targetImageView);
                } else if (getEmpty() != 0) {
                    Glide.with(mContext).load(mFile).apply(new RequestOptions().placeholder(getEmpty())).into(targetImageView);
                } else if (getError() != 0) {
                    Glide.with(mContext).load(mFile).apply(new RequestOptions().error(getError())).into(targetImageView);
                } else {
                    Glide.with(mContext).load(mFile).into(targetImageView);
                }
            } else if (mRes != 0) {
                if (getEmpty() != 0 && getError() != 0) {
                    Glide.with(mContext).load(mRes).apply(new RequestOptions().placeholder(getEmpty()).error(getError())).into(targetImageView);
                } else if (getEmpty() != 0) {
                    Glide.with(mContext).load(mRes).apply(new RequestOptions().placeholder(getEmpty())).into(targetImageView);
                } else if (getError() != 0) {
                    Glide.with(mContext).load(mRes).apply(new RequestOptions().error(getError())).into(targetImageView);
                } else {
                    Glide.with(mContext).load(mRes).into(targetImageView);
                }
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
