package hexlet.code.controller;

import hexlet.code.dto.urls.UrlPage;
import hexlet.code.dto.urls.UrlsPage;
import hexlet.code.model.Url;
import hexlet.code.repository.UrlsRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.http.Context;
import lombok.extern.slf4j.Slf4j;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Timestamp;

import static io.javalin.rendering.template.TemplateUtil.model;

@Slf4j
public class UrlsController {
    public static void create(Context ctx) throws SQLException {

        try {
            var name = normalizeUrl(ctx.formParam("url"));

            if (UrlsRepository.findByName(name).isPresent()) {
                throw new SQLDataException("Страница уже существует");
            }

            var createdAt = new Timestamp(System.currentTimeMillis());
            var url = new Url(name, createdAt);
            UrlsRepository.save(url);

            alertFlash(ctx, "Страница успешно добавлена", "success");
            ctx.redirect(NamedRoutes.urlsPath());

            log.info("Страница успешно добавлена");
        } catch (URISyntaxException | MalformedURLException |  IllegalArgumentException e) {
            alertFlash(ctx, "Некорректный URL", "danger");
            ctx.redirect(NamedRoutes.rootPath());

            log.error("Некорректный URL", e);
        } catch (SQLDataException e) {
            alertFlash(ctx, "Страница уже существует", "danger");
            ctx.redirect(NamedRoutes.urlsPath());

            log.error("Страница уже существует", e);
        }
    }

    public static void index(Context ctx) throws SQLException {
        var urls = UrlsRepository.getEntities();
        var page = new UrlsPage(urls);
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        page.setFlashType(ctx.consumeSessionAttribute("flash-type"));
        ctx.render("urls/index.jte", model("page", page));
    }

    public static void show(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var url = UrlsRepository.find(id)
                .orElseThrow(() -> new RuntimeException("Url not found"));

        var page = new UrlPage(url);
        ctx.render("urls/show.jte", model("page", page));
    }


    private static String normalizeUrl(String rawUrl) throws URISyntaxException,
                                                            MalformedURLException,
                                                            IllegalArgumentException {
    var url = new URI(rawUrl).toURL();
    return url.getProtocol() + "://" + url.getAuthority();
    }

    private static void alertFlash(Context ctx, String message, String flashType) {
        ctx.sessionAttribute("flash", message);
        ctx.sessionAttribute("flash-type", flashType);
    }
}
