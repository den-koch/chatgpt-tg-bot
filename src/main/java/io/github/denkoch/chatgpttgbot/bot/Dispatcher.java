package io.github.denkoch.chatgpttgbot.bot;

import io.github.denkoch.chatgpttgbot.bot.handler.RequestHandler;
import io.github.denkoch.chatgpttgbot.bot.model.UserRequest;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Dispatcher {

    private final List<RequestHandler> handlers;

    public Dispatcher(List<RequestHandler> handlers) {
        this.handlers = handlers
                .stream()
                .sorted(Comparator
                        .comparing(RequestHandler::isGlobal)
                        .reversed())
                .collect(Collectors.toList());
    }

    public boolean dispatch(UserRequest userRequest) {
        for (RequestHandler requestHandler : handlers) {
            if (requestHandler.isApplicable(userRequest)) {
                requestHandler.handle(userRequest);
                return true;
            }
        }
        return false;
    }
}