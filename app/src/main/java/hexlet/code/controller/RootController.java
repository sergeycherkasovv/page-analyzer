package hexlet.code.controller;


import hexlet.code.dto.urls.UrlsPage;
import hexlet.code.repository.UrlChecksRepository;
import hexlet.code.repository.UrlsRepository;
import io.javalin.http.Context;

import java.sql.SQLException;

import static io.javalin.rendering.template.TemplateUtil.model;

public class RootController {
    public static void index(Context ctx) throws SQLException {
        var urls = UrlsRepository.getEntities();
        var urlLastCheck = UrlChecksRepository.findLatestChecks();
        var page = new UrlsPage(urls, urlLastCheck);

        page.setFlash(ctx.consumeSessionAttribute("flash"));
        page.setFlashType(ctx.consumeSessionAttribute("flash-type"));

        ctx.render("index.jte", model("page", page));
    }
}
