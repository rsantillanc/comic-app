package comics._utility;

import pe.nextdots.comics.BuildConfig;

/**
 * Created by Renzo D. Santillán Ch. on 11/03/2017.01:15 AM
 * http://rsantillanc.pe.hu/me/
 */

public class SecureUtility {

    public static String MakeMd5Hash(long timestamp) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(createHash(timestamp));
            StringBuilder sb = new StringBuilder();
            for (byte anArray : array) {
                sb.append(Integer.toHexString((anArray & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            return "";
        }
    }

    private static byte[] createHash(long timestamp) {
        return (timestamp + BuildConfig.PRIVATE_KEY + BuildConfig.API_KEY).getBytes();
    }
}
