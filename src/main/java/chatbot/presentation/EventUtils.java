package chatbot.presentation;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.source.Source;

public class EventUtils {
    public static String getSenderId(Event event) {
        Source source = event.getSource();
        return source.getSenderId();
    }
}
