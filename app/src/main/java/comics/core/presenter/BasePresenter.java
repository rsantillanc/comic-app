package comics.core.presenter;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

/**
 * Created by Renzo D. Santillán Chavez on 09/03/2017.
 */

public abstract class BasePresenter<T> implements Operation {
    protected Handler handler = new Handler(Looper.getMainLooper());
    protected T mvpView;

    public void attachView(@NonNull T view) {
        this.mvpView = view;
    }

    public final void detachView() {
        mvpView = null;
    }

    public abstract void initialize();

    // Activity life cycle
    public abstract void onCreate();
    public abstract void onDestroy();
}
