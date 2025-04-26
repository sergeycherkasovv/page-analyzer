package hexlet.code.controller;

import hexlet.code.model.UrlCheck;
import hexlet.code.repository.UrlChecksRepository;
import hexlet.code.repository.UrlsRepository;
import hexlet.code.util.NamedRoutes;
import hexlet.code.util.UrlUtils;
import io.javalin.http.Context;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.sql.SQLException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class UrlChecksController {
    public static void check(Context ctx) throws SQLException {
        var urlId = Long.valueOf(ctx.pathParam("id"));
        var url = UrlsRepository.find(urlId).orElseThrow();

        HttpResponse<String> response = Unirest.get(url.getName()).asString();

        int status = response.getStatus();

        Document doc = Jsoup.parse(response.getBody());
        String h1 = doc.select("h1").text();
        String title = doc.title();
        String description = doc.select("meta[name=description]").attr("content");

        var check = new UrlCheck(urlId, status, title, h1, description);
        UrlChecksRepository.save(check);
        UrlUtils.alertFlash(ctx, "Страница успешно проверена", "success");
        ctx.redirect(NamedRoutes.urlPath(urlId));
    }
}
