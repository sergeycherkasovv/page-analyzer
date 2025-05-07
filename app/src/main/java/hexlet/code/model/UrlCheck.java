package hexlet.code.model;

import hexlet.code.model.intefaces.DataFormated;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;


@Getter
@Setter
public class UrlCheck {
    private Long id;
    private Long urlId;
    private int statusCode;
    private String title;
    private String h1;
    private String description;
    private Timestamp createdAt;

    public UrlCheck(Long urlId, int statusCode, String title, String h1, String description) {
        this.urlId = urlId;
        this.statusCode = statusCode;
        this.title = title;
        this.h1 = h1;
        this.description = description;
    }
}
