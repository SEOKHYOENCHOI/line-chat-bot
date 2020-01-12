package chatbot.presentation;

import chatbot.domain.Source;
import chatbot.domain.SourceId;
import chatbot.domain.SourceRepository;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.Broadcast;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.*;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@LineMessageHandler
@RequiredArgsConstructor
public class MessageHandler {
    public static final String START_TO_WORK_MESSAGE = "업무시작을 눌러주세요.";

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

        Source source = new Source(new SourceId(EventUtils.getSenderId(event)));
        sourceRepository.save(source);
    }

    @EventMapping
    public void handleLeave(LeaveEvent event) {
        log.info("leave {}", event);

        Source source = new Source(new SourceId(EventUtils.getSenderId(event)));
        sourceRepository.delete(source);
    }

    @EventMapping
    public void handlerMessageEvent(MessageEvent<TextMessageContent> event) {
        log.info("textMessage {}", event);

        lineMessagingClient.replyMessage(new ReplyMessage(event.getReplyToken(), new TextMessage("hi")));
    }

    @EventMapping
    public void handleOthers(Event event) {
        log.info("other event {}", event);
    }

    private void notifyStartToWork() {
        List<Source> sources = sourceRepository.findAll();
        SourceIds sourceIds = new SourceIds(sources);

        sourceIds.getPushMessages(START_TO_WORK_MESSAGE)
                .forEach(this.lineMessagingClient::pushMessage);
    }

    private Broadcast createBroadcast(String message) {
        return new Broadcast(new TextMessage(message));
    }
}
