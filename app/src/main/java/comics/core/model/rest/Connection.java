package comics.core.model.rest;

/**
 * Created by Renzo D. Santillán Ch. on 23/02/2017.12:42 AM
 * http://rsantillanc.pe.hu/me/
 */

public final class Connection {
    private static final String PROTOCOL = "https://";
    private static final String HOST = "gateway.marvel.com";
    private static final String PORT = ":80";
    private static final String VERSION = "/v1";
    private static final String SERVICE = "/public/";
    private static final String DOMAIN = PROTOCOL + HOST + PORT;
    public static final String URL_BASE = DOMAIN + VERSION + SERVICE;
}

