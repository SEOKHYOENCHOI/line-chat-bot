package chatbot.presentation;

import chatbot.domain.Source;
import chatbot.domain.SourceId;
import chatbot.domain.SourceRepository;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.Broadcast;
import com.linecorp.bot.model.event.*;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@LineMessageHandler
@RequiredArgsConstructor
public class MessageHandler {
    public static final String START_OF_WORK_MESSAGE = "업무 시작 버튼을 눌러주세요.";
    public static final String END_OF_WORK_MESSAGE = "업무 끗 버튼 눌러주세요.";

    private final LineMessagingClient lineMessagingClient;
    private final SourceRepository sourceRepository;

    @EventMapping
    public void handleMemberJoined(MemberJoinedEvent event) {
        log.info("member joined {}", event);
    }

    @EventMapping
    public void handleMemberLeft(MemberLeftEvent event) {
        log.info("member left {}", event);
    }

    @EventMapping
    public void handleJoinEvent(JoinEvent event) {
        log.info("join {}", event);
        SourceId sourceId = new SourceId(EventUtils.getSenderId(event));

        if (!sourceRepository.existsBySourceId(sourceId)) {
            Source source = new Source(sourceId);
            sourceRepository.save(source);
        }
    }

    @EventMapping
    public void handleLeave(LeaveEvent event) {
        log.info("leave {}", event);

        SourceId sourceId = new SourceId(EventUtils.getSenderId(event));
        if (sourceRepository.existsBySourceId(sourceId)) {
            Source source = new Source(sourceId);
            sourceRepository.delete(source);
        }
    }

    @EventMapping
    public void handlerMessageEvent(MessageEvent<TextMessageContent> event) {
        log.info("textMessage {}", event);
    }

    @EventMapping
    public void handleOthers(Event event) {
        log.info("other event {}", event);
    }

    public void notify(String message) {
        SourceIds sourceIds = new SourceIds(sourceRepository.findAll());

        sourceIds.getPushMessages(message)
                .forEach(this.lineMessagingClient::pushMessage);
    }

    public void broadcast() {
        log.info(String.valueOf(LocalDateTime.now()));
        lineMessagingClient.broadcast(createBroadcast(START_OF_WORK_MESSAGE));
    }

    private Broadcast createBroadcast(String message) {
        return new Broadcast(new TextMessage(message));
    }
}
