package comics.ui.custom.loader;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Renzo D. Santillán Ch. on 22/09/2016.11:12 PM
 * http://rsantillanc.pe.hu/me/
 */

public class GlideLoader implements ImageLoader {

    private Context context;
    private int defaultResIdImage;


    public GlideLoader(Context context, @DrawableRes int defaultErrorDrawableRes) {
        this.context = context;
        this.defaultResIdImage = defaultErrorDrawableRes;
    }

    private GlideLoader(Context c) {
        this.context = c;
    }


    public static GlideLoader newInstance(Context c, @DrawableRes int defaultErrorDrawableRes) {
        return new GlideLoader(c, defaultErrorDrawableRes);
    }

    @Override
    public void load(String url, ImageView iv) {
        Glide.with(context)
                .load(url)
                .placeholder(defaultResIdImage)
                .error(defaultResIdImage)
                .into(iv);
    }

    @Override
    public void load(Bitmap photoBitmap, ImageView iv) {
        Glide.with(context)
                .load(photoBitmap)
                .placeholder(defaultResIdImage)
                .error(defaultResIdImage)
                .into(iv);
    }

    @Override
    public void setDefaultImage(@DrawableRes int resId) {
        this.defaultResIdImage = resId;
    }

}
