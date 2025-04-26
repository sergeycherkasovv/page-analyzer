package hexlet.code.model;

import hexlet.code.model.intefaces.DataFormated;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class Url implements DataFormated {
    private Long id;
    private String name;
    private Timestamp createdAt;

    public Url(String name) {
        this.name = name;
    }

    @Override
    public final String getFormatedTime() {
        return createdAt.toLocalDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
}
