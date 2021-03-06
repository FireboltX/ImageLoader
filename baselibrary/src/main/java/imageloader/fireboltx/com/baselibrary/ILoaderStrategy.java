package imageloader.fireboltx.com.baselibrary;
/**
 * Created by FireBoltX on 2018/6/30.
 */

public interface ILoaderStrategy {

	void loadImage(LoaderOptions options);

	/**
	 * 清理内存缓存
	 */
	void clearMemoryCache();

	/**
	 * 清理磁盘缓存
	 */
	void clearDiskCache();

}
