package hexlet.code.util;

import hexlet.code.model.Url;
import hexlet.code.repository.UrlsRepository;
import io.javalin.http.Context;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Timestamp;

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

    public static void createUrl(String rawUrl) throws URISyntaxException, MalformedURLException, SQLException {
        String normalizedUrl = normalizeUrl(rawUrl);

        if (UrlsRepository.findByName(normalizedUrl).isPresent()) {
            throw new SQLDataException("URL уже существует: " + normalizedUrl);
        }

        var url = new Url(normalizedUrl, new Timestamp(System.currentTimeMillis()));
        UrlsRepository.save(url);
    }
}
