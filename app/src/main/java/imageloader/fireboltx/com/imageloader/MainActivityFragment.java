package imageloader.fireboltx.com.imageloader;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import imageloader.fireboltx.com.baselibrary.ImageLoader;
import imageloader.fireboltx.com.glidelibrary.GlideLoader;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    ImageView image;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        image = view.findViewById(R.id.image);

        try {
            ImageLoader.getInstance()
                    .load("http://g.hiphotos.baidu.com/image/pic/item/0823dd54564e92584fbb491f9082d158cdbf4eb0.jpg")
//                   .load(R.drawable.bg)
                    .into(image);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }
}
