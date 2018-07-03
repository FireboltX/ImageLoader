package imageloader.fireboltx.com.glidelibrary;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.util.LruCache;

import java.io.File;
import java.security.MessageDigest;

import imageloader.fireboltx.com.baselibrary.BitmapCallBack;
import imageloader.fireboltx.com.baselibrary.ILoaderStrategy;
import imageloader.fireboltx.com.baselibrary.LoaderOptions;

/**
 * Created by  FireBoltX  on 2018/7/2.
 * PackageName imageloader.fireboltx.com.glidelibrary
 */
public class GlideLoader implements ILoaderStrategy {
    private static String TAG = GlideLoader.class.getSimpleName();
    private volatile static Glide sGlideSingleton;
    private final String GLIDE_CACHE = "glide-cache";
    //    private static LruCache sLruCache = new LruCache(App.gApp);
    private static LruCache sLruCache;// = new LruCache(App.gApp);
    private static Application application;


    public GlideLoader(Application app) {
        application = app;
    }

    private static Glide getGlide() {
        if (sGlideSingleton == null) {
            synchronized (GlideLoader.class) {
                if (sGlideSingleton == null) {
                    sLruCache = new LruCache(16 * 1024);
//                    sGlideSingleton = new GlideBuilder Builder(application).memoryCache(sLruCache).build();
//                    sGlideSingleton= (new GlideBuilder()).setBitmapPool()
                    sGlideSingleton = Glide.get(application);

                }
            }
        }
        return sGlideSingleton;
    }


    @Override
    public void clearMemoryCache() {
        sLruCache.clearMemory();
    }

    @Override
    public void clearDiskCache() {
        File diskFile = new File(application.getCacheDir(), GLIDE_CACHE);
        if (diskFile.exists()) {
            //这边自行写删除代码
//	        FileUtil.deleteFile(diskFile);
        }
    }

    @Override
    public void loadImage(LoaderOptions options) {
//        RequestCreator requestCreator = null;
//        RequestOptions requestOptions = null;
        try {
            RequestManager manager = null;
            RequestBuilder builder = null;

            if (options.targetView instanceof ImageView) {
                manager = Glide.with(options.targetView.getContext());
            }

            if (options.url != null) {
                builder = manager.load(options.url);
            } else if (options.file != null) {
                builder = manager.load(options.file);
            } else if (options.drawableResId != 0) {
                builder = manager.load(options.drawableResId);
            } else if (options.uri != null) {
                builder = manager.load(options.uri);
            }

            if (builder == null) {
                throw new NullPointerException("requestCreator must not be null");
            }

            if (options.targetHeight > 0 && options.targetWidth > 0) {
                RequestOptions requestOption = new RequestOptions();
                requestOption.override(options.targetHeight, options.targetWidth);
                builder.apply(requestOption);
            }
            if (options.isCenterInside) {
                RequestOptions requestOption = new RequestOptions();
                requestOption.centerInside();
                builder.apply(requestOption);
            } else if (options.isCenterCrop) {
                RequestOptions requestOption = new RequestOptions();
                requestOption.centerCrop();
                builder.apply(requestOption);
            }
            if (options.config != null) {

            }
            if (options.errorResId != 0) {
                RequestOptions requestOption = new RequestOptions();
                requestOption.error(options.errorResId);
                builder.apply(requestOption);
            }
            if (options.placeholderResId != 0) {
                RequestOptions requestOption = new RequestOptions();
                requestOption.placeholder(options.errorResId);
                builder.apply(requestOption);
            }
            if (options.bitmapAngle != 0) {
                RequestOptions requestOption = new RequestOptions();
                requestOption.transform(new PicassoTransformation(options.bitmapAngle));
                builder.apply(requestOption);
            }
            if (options.skipLocalCache) {
                RequestOptions requestOption = new RequestOptions();
                if (options.skipLocalCache) {
                    requestOption.diskCacheStrategy(DiskCacheStrategy.NONE);
                }
                builder.apply(requestOption);
            }
            if (options.skipNetCache) {
//            requestCreator.networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE);

//            RequestOptions requestOption  =new RequestOptions();
//            requestOption.diskCacheStrategy(DiskCacheStrategy.NONE);
//            builder.apply(requestOption);
            }
            if (options.degrees != 0) {
                RequestOptions requestOption = new RequestOptions();
                requestOption.error(options.errorResId);
                builder.apply(requestOption);
            }

            if (options.targetView instanceof ImageView) {
                builder.into(((ImageView) options.targetView));
            } else if (options.callBack != null) {
                builder.into(new PicassoTarget(options.callBack));
            } else if (options.callBack == null) {
                Log.e(TAG, "instanceof ImageView no");
            } else {
                Log.e(TAG, "instanceof total fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    class PicassoTarget implements Target {
        BitmapCallBack callBack;

        @Override
        public void onLoadStarted(@Nullable Drawable placeholder) {

        }

        @Override
        public void onLoadFailed(@Nullable Drawable errorDrawable) {

        }

        @Override
        public void onResourceReady(@NonNull Object resource, @Nullable Transition transition) {

        }

        @Override
        public void onLoadCleared(@Nullable Drawable placeholder) {

        }

        @Override
        public void getSize(@NonNull SizeReadyCallback cb) {

        }

        @Override
        public void removeCallback(@NonNull SizeReadyCallback cb) {

        }

        @Override
        public void setRequest(@Nullable Request request) {

        }

        @Nullable
        @Override
        public Request getRequest() {
            return null;
        }

        @Override
        public void onStart() {

        }

        @Override
        public void onStop() {

        }

        @Override
        public void onDestroy() {

        }

        protected PicassoTarget(BitmapCallBack callBack) {
            this.callBack = callBack;
        }
//
//        @Override
//        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//            if (this.callBack != null) {
//                this.callBack.onBitmapLoaded(bitmap);
//            }
//        }
//
//        @Override
//        public void onBitmapFailed(Exception e, Drawable errorDrawable) {
//            if (this.callBack != null) {
//                this.callBack.onBitmapFailed(e);
//            }
//        }
//
//        @Override
//        public void onPrepareLoad(Drawable placeHolderDrawable) {
//
//        }
    }

    class PicassoTransformation implements Transformation {
        private float bitmapAngle;

        protected PicassoTransformation(float corner) {
            this.bitmapAngle = corner;
        }

        @NonNull
        @Override
        public Resource transform(@NonNull Context context, @NonNull Resource resource, int outWidth, int outHeight) {
            float roundPx = bitmapAngle;//圆角的横向半径和纵向半径
            Bitmap output = Bitmap.createBitmap(outWidth,
                    outHeight, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(output);
            final int color = 0xff424242;
            final Paint paint = new Paint();
            final Rect rect = new Rect(0, 0, outWidth, outWidth);
            final RectF rectF = new RectF(rect);
            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(color);
            canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(output, rect, rect, paint);
            resource.recycle();
//            return (new Resource()).;
            return resource;
        }

        @Override
        public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

        }

//        @Override
//        public Bitmap transform(Bitmap source) {
//            float roundPx = bitmapAngle;//圆角的横向半径和纵向半径
//            Bitmap output = Bitmap.createBitmap(source.getWidth(),
//                    source.getHeight(), Bitmap.Config.ARGB_8888);
//            Canvas canvas = new Canvas(output);
//            final int color = 0xff424242;
//            final Paint paint = new Paint();
//            final Rect rect = new Rect(0, 0, source.getWidth(), source.getHeight());
//            final RectF rectF = new RectF(rect);
//            paint.setAntiAlias(true);
//            canvas.drawARGB(0, 0, 0, 0);
//            paint.setColor(color);
//            canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
//            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//            canvas.drawBitmap(source, rect, rect, paint);
//            source.recycle();
//            return output;
//        }
//
//        @Override
//        public String key() {
//            return "bitmapAngle()";
//        }
    }
}
