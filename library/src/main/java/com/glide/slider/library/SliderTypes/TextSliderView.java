package com.glide.slider.library.SliderTypes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.glide.slider.library.R;

/**
 * This is a slider with a description TextView.
 */
public class TextSliderView extends BaseSliderView{
    public TextSliderView(Context context) {
        super(context);
    }

    @Override
    public View getView() {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.render_type_text,null);
        ImageView target = (ImageView)v.findViewById(R.id.glide_slider_image);
        TextView description = (TextView)v.findViewById(R.id.description);
        LinearLayout description_layout = (LinearLayout)v.findViewById(R.id.description_layout);

        description.setText(getDescription());
        description_layout.setBackgroundColor(getDescriptionBackground());
        description_layout.setVisibility(getDescriptionVisibility());
        bindEventAndShow(v, target);
        return v;
    }
}
