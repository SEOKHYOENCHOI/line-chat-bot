package chatbot.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Source {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Source(final SourceId sourceId) {
        this.sourceId = sourceId;
    }

    @Embedded
    private SourceId sourceId;
}
