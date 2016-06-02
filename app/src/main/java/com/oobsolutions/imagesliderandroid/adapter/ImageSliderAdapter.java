package com.oobsolutions.imagesliderandroid.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.oobsolutions.imagesliderandroid.R;
import com.oobsolutions.imagesliderandroid.utility.AppController;

import java.util.ArrayList;

/**
 * Created by Dell on 6/2/2016.
 */
public class ImageSliderAdapter extends PagerAdapter {

    private ArrayList<String> imagesUrl = new ArrayList<String>();
    private Context ctx;
    private LayoutInflater layoutInflater;
    private ImageLoader imgLoader = AppController.getInstance().getImageLoader();

    public ImageSliderAdapter(Context ctx){
        this.ctx = ctx;
    }

    public ImageSliderAdapter(Context ctx, ArrayList<String> imagesUrl){
        this.ctx = ctx;
        this.imagesUrl = imagesUrl;
    }

    @Override
    public int getCount() {
        return imagesUrl.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View swipe_view = layoutInflater.inflate(R.layout.single_slide_show_image,container,false);

        if (imgLoader == null)
            imgLoader = AppController.getInstance().getImageLoader();

        NetworkImageView imgView = (NetworkImageView) swipe_view.findViewById(R.id.single_slide_img);
        imgView.setImageUrl(imagesUrl.get(position), imgLoader);

        container.addView(swipe_view);
        return swipe_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }

    public void AddImageUrl(String imageUrl){
        this.imagesUrl.add(imageUrl);
    }

}
