package imageloader.fireboltx.com.imageloader;

import android.app.Application;

import imageloader.fireboltx.com.baselibrary.ImageLoader;
import imageloader.fireboltx.com.glidelibrary.GlideLoader;
import imageloader.fireboltx.com.picassolibrary.PicassoLoader;

//import imageloader.fireboltx.com.baselibrary.ImageLoader;
//import imageloader.fireboltx.com.glidelibrary.GlideLoader;
//import imageloader.fireboltx.com.picassolibrary.PicassoLoader;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化图片库
        ImageLoader.getInstance().setGlobalImageLoader(new GlideLoader(this));
//        ImageLoader.getInstance().setGlobalImageLoader(new PicassoLoader(this));
    }
}
