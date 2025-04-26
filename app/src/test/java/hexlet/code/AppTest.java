package hexlet.code;

import hexlet.code.model.Url;
import hexlet.code.repository.UrlChecksRepository;
import hexlet.code.repository.UrlsRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.Javalin;

import java.io.IOException;

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
import java.nio.file.Path;
import java.nio.file.Paths;

class AppTest {
    private static Javalin app;
    private static MockWebServer mockWebServer;

    @BeforeEach
    final void setUp() throws Exception {
        app = App.getApp();
    }

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }
    private static String readFixtures(String fileName) throws IOException {
        var filePath = getFixturePath(fileName);
        return Files.readString(filePath).trim();
    }

    @BeforeAll
    static void startMockServer() throws IOException {
        mockWebServer = new MockWebServer();
        var body = readFixtures("index.html");
        mockWebServer.enqueue(new MockResponse().setBody(body).setResponseCode(200));
        mockWebServer.start();
    }

    @AfterAll
    static  void stopMockServer() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void testMainPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get(NamedRoutes.rootPath());
            assertThat(response.code()).isEqualTo(200);
        });
    }

    @Test
    void testIndex() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get(NamedRoutes.urlsPath());
            assertThat(response.code()).isEqualTo(200);
        });
    }

    @Test
    void testShow() {
        JavalinTest.test(app, ((server, client) -> {
            var link = "url=https://ru.hexlet.io/programs/java/projects/72";
            client.post(NamedRoutes.urlsPath(), link);
            var response = client.get(NamedRoutes.urlPath("1"));

            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("https://ru.hexlet.io");
            assertThat(UrlsRepository.findByName("https://ru.hexlet.io").isPresent()).isTrue();
            response.close();
        }));
    }

    @Test
    void testCreateUrl() {
        JavalinTest.test(app, (server, client) -> {
            var link = "url=https://ru.hexlet.io/programs/java/projects/72";
            var response = client.post(NamedRoutes.urlsPath(), link);
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("https://ru.hexlet.io");
            assertThat(UrlsRepository.findByName("https://ru.hexlet.io").isPresent()).isTrue();
        });
    }

    @Test
    void testUrlNotFound() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/urls/500");
            assertThat(response.code()).isEqualTo(500);
        });
    }

    @Test
    void checkTest() throws IOException {
        var testUrl = mockWebServer.url("/").toString();

        JavalinTest.test(app, (server, client) -> {
            var url = new Url(testUrl);
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
}
