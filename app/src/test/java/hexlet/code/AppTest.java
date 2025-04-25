package hexlet.code;

import hexlet.code.model.Url;
import hexlet.code.repository.UrlChecksRepository;
import hexlet.code.repository.UrlsRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.Javalin;

import java.io.IOException;
import java.sql.Timestamp;

import io.javalin.testtools.JavalinTest;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Paths;

class AppTest {
    private static Javalin app;
    private static MockWebServer mockWebServer;

    @BeforeEach
    final void setUp() throws Exception {
        app = App.getApp();
    }
    final Timestamp setupCreatedAt() {
        return new Timestamp(System.currentTimeMillis());
    }
    final String readsFiles(String fileName) throws IOException {
        return Files.readString(Paths.get(fileName));
    }

    @BeforeAll
    static void startMockServer() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @Test
    void testMainPage(){
        JavalinTest.test(app, (server, client) -> {
            var response = client.get(NamedRoutes.rootPath());
            assertThat(response.code()).isEqualTo(200);
        });
    }

    @Test
    void testUrlsPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get(NamedRoutes.urlsPath());
            assertThat(response.code()).isEqualTo(200);
        });
    }

    @Test
    void testUrlPage() throws Exception {
        var link = "https://www.example.com";
        var url = new Url(link, setupCreatedAt());
        UrlsRepository.save(url);
        JavalinTest.test(app, (server, client) -> {
            var response = client.get(NamedRoutes.urlPath(url.getId()));
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("www.example.com");
        });
    }

    @Test
    void testUrlNotFound() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/users/404");
            assertThat(response.code()).isEqualTo(404);
        });
    }

    @Test
    void testCreateUrl() {
        JavalinTest.test(app, (server, client) -> {
            var requestBody = "url=https://ru.hexlet.io/programs/java/projects/72";
            var response = client.post(NamedRoutes.urlsPath(), requestBody);
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("https://ru.hexlet.io");
        });
    }

    @Test
    void testWebServer() throws IOException {
        var body = readsFiles("src/test/resources/testIndex.html");
        mockWebServer.enqueue(new MockResponse().setBody(body).setResponseCode(200));
        var testUrl = mockWebServer.url("/").toString();

        JavalinTest.test(app, (server, client) -> {
            var url = new Url(testUrl, setupCreatedAt());
            UrlsRepository.save(url);
            var urlId = url.getId();
            client.post(NamedRoutes.urlPathChecks(urlId));
            var check = UrlChecksRepository.findLatestChecks();

            assertEquals(check.get(urlId).getStatusCode(), 200);
            assertEquals(check.get(urlId).getTitle(), "TestTitle");
            assertEquals(check.get(urlId).getH1(), "TestH1");
            assertEquals(check.get(urlId).getDescription(), "TestDescription");
        });
    }

    @AfterAll
    static  void stopMockServer() throws IOException {
        mockWebServer.shutdown();
    }

}
