package chatbot.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@EqualsAndHashCode
@Embeddable
public class SourceId {
    @Column(nullable = false)
    private String sourceId;

    private SourceId() {

    }

    public SourceId(String sourceId) {
        this.sourceId = sourceId;
    }
}
