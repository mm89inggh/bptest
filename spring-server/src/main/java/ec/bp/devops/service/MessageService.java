package ec.bp.devops.service;

import ec.bp.devops.model.Message;

import java.util.concurrent.CompletableFuture;

public interface MessageService {

    /**
     * Send message with request info and valid api key
     * @param apikey String api key
     * @param message Object with request info
     * @return Message with greeting or error
     */
    CompletableFuture<String> sendMessage(String apikey, Message message);
}
