package comics._utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Renzo D. Santillán Ch. on 11/03/2017.10:17 AM
 * http://rsantillanc.pe.hu/me/
 */

public class NetworkUtility {
    /**
     * Checks if the device has any active internet connection.
     * @return true device with internet connection, otherwise false.
     */
    public static boolean isThereInternetConnection(Context context) {
        boolean isConnected;
        try {
            ConnectivityManager connectivityManager =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        } catch (Exception ex) {
            //May be you require permissions
            isConnected = false;
        }
        return isConnected;
    }

}
