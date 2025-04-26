package hexlet.code.controller;

import hexlet.code.dto.urls.UrlChecksPage;
import hexlet.code.dto.urls.UrlsPage;
import hexlet.code.model.Url;
import hexlet.code.repository.UrlChecksRepository;
import hexlet.code.repository.UrlsRepository;
import hexlet.code.util.NamedRoutes;
import hexlet.code.util.UrlUtils;
import io.javalin.http.Context;
import lombok.extern.slf4j.Slf4j;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.sql.SQLDataException;
import java.sql.SQLException;

import static io.javalin.rendering.template.TemplateUtil.model;

@Slf4j
public class UrlsController {
    public static void create(Context ctx) throws SQLException {
        var rawUrl = ctx.formParam("url");

        try {

            if (rawUrl == null || rawUrl.isBlank()) {
                UrlUtils.alertFlash(ctx, "URL не может быть пустым", "danger");
                ctx.render(NamedRoutes.rootPath()).status(422);
                return;
            }

            createUrl(rawUrl);

            UrlUtils.alertFlash(ctx, "Страница успешно добавлена", "success");
            ctx.redirect(NamedRoutes.urlsPath());

            log.info("Страница успешно добавлена: {}", rawUrl);
        } catch (URISyntaxException | MalformedURLException |  IllegalArgumentException e) {
            UrlUtils.alertFlash(ctx, "Некорректный URL", "danger");
            ctx.redirect(NamedRoutes.rootPath());

            log.error("Ошибка валидации URL: {}", rawUrl, e);
        } catch (SQLDataException e) {
            UrlUtils.alertFlash(ctx, "Страница уже существует", "danger");
            ctx.redirect(NamedRoutes.urlsPath());

            log.warn("Страница уже существует: {}", rawUrl);
        }
    }

    public static void index(Context ctx) throws SQLException {
        var urls = UrlsRepository.getEntities();
        var urlLastCheck = UrlChecksRepository.findLatestChecks();
        var page = new UrlsPage(urls, urlLastCheck);
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        page.setFlashType(ctx.consumeSessionAttribute("flash-type"));

        ctx.render("urls/index.jte", model("page", page));
    }

    public static void show(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var url = UrlsRepository.find(id)
                .orElseThrow(() -> new RuntimeException("Url not found"));
        var urlChecks = UrlChecksRepository.getEntities();

        var page = new UrlChecksPage(url, urlChecks);
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        page.setFlashType(ctx.consumeSessionAttribute("flash-type"));

        ctx.render("urls/show.jte", model("page", page));
    }

    public static void createUrl(String rawUrl) throws URISyntaxException, MalformedURLException, SQLException {
        String normalizedUrl = UrlUtils.normalizeUrl(rawUrl);

        if (UrlsRepository.findByName(normalizedUrl).isPresent()) {
            throw new SQLDataException("Страница уже существует: " + normalizedUrl);
        }
        var url = new Url(normalizedUrl);
        UrlsRepository.save(url);
    }
}
