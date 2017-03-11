package comics.core.presenter;

/**
 * Created by Renzo D. Santillán Ch. on 11/03/2017.09:14 AM
 * http://rsantillanc.pe.hu/me/
 */

public interface Operation {
    void onDone(Object object);

    void onError(String message);

    void onTimeout();

    void onConnectError();

    void onComplete();
}
