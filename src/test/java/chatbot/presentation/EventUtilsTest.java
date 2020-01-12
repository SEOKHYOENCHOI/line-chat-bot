package chatbot.presentation;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.JoinEvent;
import com.linecorp.bot.model.event.source.Source;
import com.linecorp.bot.model.event.source.UserSource;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

class EventUtilsTest {
    @Test
    void getSenderId() {
        String userId = "UserId";
        String replyToken = "ReplyToken";

        Source source = new UserSource(userId);
        Event event = new JoinEvent(replyToken, source, Instant.now());

        assertThat(EventUtils.getSenderId(event)).isEqualTo(userId);
    }
}