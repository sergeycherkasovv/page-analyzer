package hexlet.code.util;

import io.javalin.http.Context;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class UrlUtils {

    public static String normalizeUrl(String rawUrl) throws URISyntaxException, MalformedURLException,
                                                            IllegalArgumentException {
        var url = new URI(rawUrl).toURL();
        return url.getProtocol() + "://" + url.getAuthority();
    }

    public static void alertFlash(Context ctx, String message, String flashType) {
        ctx.sessionAttribute("flash", message);
        ctx.sessionAttribute("flash-type", flashType);
    }
}
