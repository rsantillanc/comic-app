package comics.core.presenter;

import android.support.annotation.NonNull;

/**
 * Created by Renzo D. Santillán Chavez on 09/03/2017.
 */

public abstract class BasePresenter<T> implements Operation {
    protected T mvpView;

    public void attachView(@NonNull T view) {
        this.mvpView = view;
    }

    public final void detachView() {
        mvpView = null;
    }

}
