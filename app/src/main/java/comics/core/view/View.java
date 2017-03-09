package comics.core.view;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Renzo D. Santillán Ch. on 18/02/2017.12:20 PM
 * http://rsantillanc.pe.hu/me/
 */

public interface View {
    Context context();
    Intent intent();
    void setupUiElements();
    void finishActivity();
}
