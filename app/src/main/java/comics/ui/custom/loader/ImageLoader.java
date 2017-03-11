package comics.ui.custom.loader;

import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

/**
 * Created by Renzo D. Santillán Ch. on 22/09/2016.11:10 PM
 * http://rsantillanc.pe.hu/me/
 */

public interface ImageLoader {
    void load(String url, ImageView iv);
    void load(Bitmap url, ImageView iv);
    void setDefaultImage(@DrawableRes int resId);
}
