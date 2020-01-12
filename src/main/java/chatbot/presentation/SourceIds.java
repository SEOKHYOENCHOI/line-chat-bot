package chatbot.presentation;

import chatbot.domain.Source;
import chatbot.domain.SourceId;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SourceIds {
    private final Set<SourceId> sourceIds;

    public SourceIds(List<Source> sources) {
        sourceIds = sources.stream()
                .map(Source::getSourceId)
                .collect(Collectors.toSet())
        ;
    }

    public Set<PushMessage> getPushMessages(String message) {
        Set<PushMessage> messages = sourceIds.stream()
                .map(SourceId::getSourceId)
                .map(sourceId -> new PushMessage(sourceId, new TextMessage(message)))
                .collect(Collectors.toSet());

        return Collections.unmodifiableSet(messages);
    }
}
