package com.runtai.pullupsuspension.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.runtai.pullupsuspension.R;


/**
 * ImageView创建工厂
 */
public class ViewFactory {
    public static ImageLoader imageloader;
    public static ImageView imageView;
    private static Context context;

    /**
     * 获取ImageView视图的同时加载显示url
     *
     * @param
     * @return
     */
    public static ImageView getImageView(Context context, String url) {
        ViewFactory.context = context;

        if (context != null && url != null) {
            imageloader = ImageLoader.getInstance();
            imageView = (ImageView) LayoutInflater.from(context).inflate(
                    R.layout.view_banner, null);
            if (imageloader != null && imageView != null) {

                imageloader.displayImage(url, imageView, ImageLoadUtil.getBannerOptions(), null);
            }
            
        }
        return imageView;
    }
}
