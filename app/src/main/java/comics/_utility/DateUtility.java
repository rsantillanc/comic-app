package comics._utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Renzo D. Santillán Ch. on 24/02/2017.12:40 AM
 http://rsantillanc.pe.hu/me/
 */

public class DateUtility {
 private static final String DEFAULT_DATE_FORMAT = "dd/MMM yyyy";

    public static final SimpleDateFormat F1 = new SimpleDateFormat(DEFAULT_DATE_FORMAT, Locale.getDefault());

    /**
     * Simple method to get current year.
     * @return long year, e.g. 2017
     */
    public static long getYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }


    /**
     * Simple method to get current month.
     * @return long year, e.g. 2 //february
     */
    public static int getMonth() {
        return Calendar.getInstance().get(Calendar.MONTH);
    }
}
