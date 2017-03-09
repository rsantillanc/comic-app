package comics._utility;

/**
 * Created by Renzo D. Santillán Ch. on 20/02/2017.12:39 AM
 http://rsantillanc.pe.hu/me/
 */

/**
 * Generic callback for network petitions or any other
 * @param <T>
 */
public interface Callback<T> {
    void done(T result);
}
