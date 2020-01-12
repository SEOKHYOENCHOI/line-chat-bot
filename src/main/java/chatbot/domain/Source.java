package chatbot.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Source {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Source() {

    }

    public Source(final SourceId sourceId) {
        this.sourceId = sourceId;
    }

    @Embedded
    private SourceId sourceId;
}
