package com.glide.slider.library.svg;

//https://github.com/bumptech/glide/blob/master/samples/svg/src/main/java/com/bumptech/glide/samples/svg

import android.graphics.Picture;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.SimpleResource;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import com.caverock.androidsvg.SVG;

/**
 * Convert the {@link SVG}'s internal representation to an Android-compatible one
 * ({@link Picture}).
 */
public class SvgDrawableTranscoder implements ResourceTranscoder<SVG, Drawable> {
    @Override
    public Resource<Drawable> transcode(Resource<SVG> toTranscode, Options options) {
        SVG svg = toTranscode.get();
        Picture picture = svg.renderToPicture();
        Drawable drawable = new PictureDrawable(picture);
        return new SimpleResource<>(drawable);
    }
}
