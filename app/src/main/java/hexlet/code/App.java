package hexlet.code;

import io.javalin.Javalin;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

    public static void main(String[] args) throws IOException {
        Javalin app = getApp();
        app.start(7070);
    }
    public static Javalin getApp() throws IOException {
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        app.get("/", ctx -> ctx.result("Hello World"));
        return app;
    }
}

