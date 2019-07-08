package ec.bp.devops.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import ec.bp.devops.model.Message;
import ec.bp.devops.model.ModelApiResponse;
import ec.bp.devops.service.MessageService;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-07-05T03:09:44.410Z")

@Controller
public class MessageApiController implements MessageApi {

    private static final Logger log = LoggerFactory.getLogger(MessageApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private MessageService service;

    @org.springframework.beans.factory.annotation.Autowired
    public MessageApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public CompletableFuture<ResponseEntity<ModelApiResponse>> sendMessage(@RequestHeader(value = "X-Parse-REST-API-Key", required = true) String apiKey, @ApiParam(value = "", required = true) @Valid @RequestBody Message messageRequest) {
        return service.sendMessage(apiKey, messageRequest).thenApplyAsync(m -> {
            HttpStatus status = HttpStatus.OK;
            if (m.contains("invalid")) {
                status = HttpStatus.UNAUTHORIZED;
                log.error("Bad api key");
            }
            return new ResponseEntity<>(new ModelApiResponse().message(m), status);
        });
    }


}
