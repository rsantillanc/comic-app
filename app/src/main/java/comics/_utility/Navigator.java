package comics._utility;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import comics.core.model.entity.Comic;
import comics.ui.detail.DetailActivity;
import comics.ui.detail.PictureActivity;
import comics.ui.login.LoginActivity;
import comics.ui.main.MainActivity;

/**
 * Created by Renzo D. Santillán Chavez on 20/03/2017.
 */

public class Navigator {
    public static void goToDetailActivity(Context context, Comic comic) {
        Intent detail = new Intent(context, DetailActivity.class);
        detail.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        if (comic != null) {
            detail.putExtra(C.Key.IS_FAVOURITE, comic.isFavourite());
            Bundle b = new Bundle();
            b.putParcelable(C.Extra.COMIC, comic);
            detail.putExtras(b);
        } else
            return;
        context.startActivity(detail);
    }

    public static void goToPictureActivity(Context context, String completeUrl) {
        Intent picture = new Intent(context, PictureActivity.class);
        picture.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        picture.putExtra(C.Extra.URL, completeUrl);
        context.startActivity(picture);
    }

    public static void goToMainActivity(Context context) {
        Intent main = new Intent(context, MainActivity.class);
        main.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(main);
    }

    public static void goToLoginActivity(Context context) {
        Intent login = new Intent(context, LoginActivity.class);
        login.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(login);
    }
}
