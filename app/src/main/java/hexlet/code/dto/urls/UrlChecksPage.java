package hexlet.code.dto.urls;

import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import lombok.Getter;

import java.util.List;

@Getter
public class UrlChecksPage extends UrlPage {
    private final List<UrlCheck> urlChecks;

    public UrlChecksPage(Url url, List<UrlCheck> urlChecks) {
        super(url);
        this.urlChecks = urlChecks;
    }
}
