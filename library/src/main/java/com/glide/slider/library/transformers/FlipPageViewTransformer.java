package com.glide.slider.library.transformers;

import android.view.View;

import com.glide.slider.library.tricks.ViewPagerEx;

public class FlipPageViewTransformer extends BaseTransformer {

    @Override
    protected void onTransform(View view, float position) {
        float percentage = 1 - Math.abs(position);
        view.setCameraDistance(12000);
        setVisibility(view, position);
        setTranslation(view);
        setSize(view, position, percentage);
        setRotation(view, position, percentage);
    }

    private void setVisibility(View page, float position) {
        if (position < 0.5 && position > -0.5) {
            page.setVisibility(View.VISIBLE);
        } else {
            page.setVisibility(View.INVISIBLE);
        }
    }

    private void setTranslation(View view) {
        ViewPagerEx viewPager = (ViewPagerEx) view.getParent();
        int scroll = viewPager.getScrollX() - view.getLeft();
        view.setTranslationX(scroll);
    }

    private void setSize(View view, float position, float percentage) {
        view.setScaleX((position != 0 && position != 1) ? percentage : 1);
        view.setScaleY((position != 0 && position != 1) ? percentage : 1);
    }

    private void setRotation(View view, float position, float percentage) {
        if (position > 0) {
            view.setRotationY(-180 * (percentage + 1));
        } else {
            view.setRotationY(180 * (percentage + 1));
        }
    }
}