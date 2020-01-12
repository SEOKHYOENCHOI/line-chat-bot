package chatbot.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@EqualsAndHashCode
@Embeddable
@NoArgsConstructor
public class SourceId {
    @Column(nullable = false)
    private String sourceId;

    public SourceId(String sourceId) {
        this.sourceId = sourceId;
    }
}
