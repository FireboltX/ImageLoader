package imageloader.fireboltx.com.baselibrary;

import android.graphics.Bitmap;

/**
 * Created by FireBoltX on 2018/6/30.
 */

public interface BitmapCallBack {

	void onBitmapLoaded(Bitmap bitmap);

	void onBitmapFailed(Exception e);

	public static class EmptyCallback implements BitmapCallBack {


		@Override
		public void onBitmapLoaded(Bitmap bitmap) {

		}

		@Override
		public void onBitmapFailed(Exception e) {

		}
	}
}
