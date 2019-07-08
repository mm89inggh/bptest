package ec.bp.devops.service;

import ec.bp.devops.model.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class MessageServiceImpl implements MessageService {

    @Value("${app.security.api.key}")
    private String valueKey;

    @Override
    public CompletableFuture<String> sendMessage(String apikey, Message message) {
        String messageResponse = "Unauthorized, invalid key";
        if (valueKey.equals(apikey)) {
            StringBuilder msm = new StringBuilder("Hello ")
                    .append(message.getTo()).append(" your message will be send");
            messageResponse = msm.toString();
        }
        return CompletableFuture.completedFuture(messageResponse);
    }
}
