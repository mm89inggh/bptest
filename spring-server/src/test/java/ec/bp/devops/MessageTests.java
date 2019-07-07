package ec.bp.devops;

import com.fasterxml.jackson.databind.ObjectMapper;
import ec.bp.devops.api.MessageApi;
import ec.bp.devops.model.Message;
import ec.bp.devops.model.ModelApiResponse;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author marco
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Swagger2SpringBoot.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MessageTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @Autowired
    private MessageApi messageApi;

    private final String CONTEXTO = "/DevOps";

    private static Message entityTest;

    @Value("${app.security.api.key}")
    private String apiKeyHeader;

    @Before
    public void instalar() {
        initMocks(this);
        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @BeforeClass
    public static void iniciar() {
        entityTest = new Message().message("Message").to("To").from("from").timeToLifeSec(1);
    }

    private byte[] toJson(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsBytes(object);
    }

    private <T> T toObject(String json, Class<T> clase) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, clase);
    }

    @Test
    public void testAContextLoad() {
        assertThat(messageApi).isNotNull();
    }

    @Test
    public void testBSendMessage() throws Exception {
        ResultActions result = mvc.perform(post(CONTEXTO)
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-Parse-REST-API-Key", apiKeyHeader).content(toJson(entityTest)))
                .andDo(print()).andExpect(status().isOk());
        ResponseEntity<ModelApiResponse> entidad = (ResponseEntity) result.andReturn().getAsyncResult();
        assertThat(entidad).isNotNull();
        assertThat(entidad.getBody().getMessage()).isNotNull();
        String message = entidad.getBody().getMessage();
        assertThat(message).contains(entityTest.getTo());
    }

    @Test
    public void testCSendMessageErrorHeader() throws Exception {
        ResultActions result = mvc.perform(post(CONTEXTO)
                .contentType(MediaType.APPLICATION_JSON).content(toJson(entityTest)))
                .andDo(print()).andExpect(status().isBadRequest());
        String errorJson = result.andReturn().getResponse().getContentAsString();
        ModelApiResponse entidad = toObject(errorJson, ModelApiResponse.class);
        assertThat(entidad).isNotNull();
        assertThat(entidad.getMessage()).isNotNull();
        String error = entidad.getMessage();
        assertThat(error).contains("X-Parse-REST-API-Key");
    }

    @Test
    public void testDSendMessageErrorHeaderBad() throws Exception {
        ResultActions result = mvc.perform(post(CONTEXTO)
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-Parse-REST-API-Key", "abcdefghijk")
                .content(toJson(entityTest)))
                .andDo(print()).andExpect(status().isOk());
        //String error = result.andReturn().getResponse().getContentAsString();
        //ModelApiResponse entidad = toObject(error, ModelApiResponse.class);
        ResponseEntity<ModelApiResponse> entidad = (ResponseEntity) result.andReturn().getAsyncResult();
        assertThat(entidad).isNotNull();
        assertThat(entidad.getBody().getMessage()).isNotNull();
        String message = entidad.getBody().getMessage().toString();
        assertThat(message).contains("invalid");
    }

    @Test
    public void testESendMessageErrorNotMethod() throws Exception {
        ResultActions result = mvc.perform(get(CONTEXTO)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isMethodNotAllowed());
        String errorJson = result.andReturn().getResponse().getContentAsString();
        ModelApiResponse entidad = toObject(errorJson, ModelApiResponse.class);
        assertThat(entidad).isNotNull();
        assertThat(entidad.getMessage()).isNotNull();
        String error = entidad.getMessage();
        assertThat(error).contains("not supported");
    }

}

