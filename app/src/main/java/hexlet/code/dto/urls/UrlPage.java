package hexlet.code.dto.urls;

import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import lombok.Getter;

import java.util.List;

@Getter
public class UrlPage extends UrlChecksPage {
    private final Url url;

    public UrlPage(Url url, List<UrlCheck> urlChecks) {
        super(urlChecks);
        this.url = url;
    }
}
