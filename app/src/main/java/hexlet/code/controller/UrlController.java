package hexlet.code.controller;

import hexlet.code.dto.urls.UrlPage;
import hexlet.code.dto.urls.UrlsPage;
import hexlet.code.model.Url;
import hexlet.code.repository.UrlRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.http.Context;
import lombok.extern.slf4j.Slf4j;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Timestamp;

import static io.javalin.rendering.template.TemplateUtil.model;

@Slf4j
public class UrlController {
    public static void create(Context ctx) throws SQLException {

        try {
            var link = new URI(ctx.formParam("url")).toURL();
            var name = link.getProtocol() + "://" + link.getAuthority();

            if (UrlRepository.findByName(name).isPresent()) {
                throw new SQLDataException("Страница уже существует");
            }
            var createdAt = new Timestamp(System.currentTimeMillis());
            var url = new Url(name, createdAt);
            UrlRepository.save(url);

            ctx.sessionAttribute("flash", "Страница успешно добавлена");
            ctx.sessionAttribute("flash-type", "succes");
            ctx.redirect(NamedRoutes.urlsPath());
            log.info("Страница успешно добавлена");

        } catch (URISyntaxException | MalformedURLException e) {
            ctx.sessionAttribute("flash", "Некорректный URL");
            ctx.sessionAttribute("flash-type", "danger");
            ctx.render(NamedRoutes.rootPath());
            log.error("Некорректный URL");

        } catch (SQLDataException e) {
            ctx.sessionAttribute("flash", "Страница уже существует");
            ctx.sessionAttribute("flash-type", "danger");
            ctx.render(NamedRoutes.rootPath());
            log.error("Страница уже существует");
        }
    }

    public static void index(Context ctx) throws SQLException {
        var url = UrlRepository.getEntites();
        var page = new UrlsPage(url);
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        page.setFlashType(ctx.consumeSessionAttribute("flash-type"));
        ctx.render("urls/index.jte", model(page, page));
    }

    public static void show(Context ctx) throws SQLException {
        var id = ctx.formParamAsClass("id", Long.class).get();
        var url = UrlRepository.find(id)
                .orElseThrow(() -> new RuntimeException("Utl not found"));

        var page = new UrlPage(url);
        ctx.render("urls/show.jte", model("page", page));
    }
}
