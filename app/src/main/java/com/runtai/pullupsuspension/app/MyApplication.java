package com.runtai.pullupsuspension.app;


import android.app.Application;
import android.os.StrictMode;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.runtai.pullupsuspension.utils.FraCommUtil;

public class MyApplication extends Application {

//    public static RefWatcher getRefWatcher(Context context) {
//        MyApplication application = (MyApplication) context.getApplicationContext();
//        return application.refWatcher;
//    }

  // private RefWatcher refWatcher;

    private static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
       // refWatcher = LeakCanary.install(this);
        initImageLoader();

    }

    private void initImageLoader() {

        ImageLoaderConfiguration mImageLoaderConfiguration = new ImageLoaderConfiguration.Builder(this).diskCacheExtraOptions(480, 800, null)
                .memoryCacheExtraOptions(480, 800).threadPoolSize(5).threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)).memoryCacheSize(50 * 1024 * 1024).diskCacheSize(100 * 1024 * 1024)
                .diskCacheFileCount(100).diskCache(new UnlimitedDiskCache(FraCommUtil.getImgPath())).diskCacheFileNameGenerator(new Md5FileNameGenerator())
                // 将保存的时候的URI名称用MD5加密
                .tasksProcessingOrder(QueueProcessingType.LIFO).defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .imageDownloader(new BaseImageDownloader(this, 10 * 1000, 30 * 1000)).build();

        ImageLoader.getInstance().init(mImageLoaderConfiguration);// 全局初始化此配置

    }
}



