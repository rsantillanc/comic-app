package comics._utility;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import comics.core.model.entity.Comic;
import comics.ui.main.EmptyActivity;

/**
 * Created by Renzo D. Santillán Chavez on 20/03/2017.
 */

public class Navigator {
    public static void goToDetailActivity(Context context, Comic comic) {
        Intent detail = new Intent(context, EmptyActivity.class);
        detail.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        if (comic != null) {
            Bundle b = new Bundle();
            b.putParcelable(C.Extra.COMIC, comic);
            detail.putExtras(b);
        }
        context.startActivity(detail);
    }
}
