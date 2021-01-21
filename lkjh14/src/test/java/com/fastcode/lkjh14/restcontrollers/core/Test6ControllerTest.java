package com.fastcode.lkjh14.restcontrollers.core;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fastcode.lkjh14.application.core.test6.Test6AppService;
import com.fastcode.lkjh14.application.core.test6.dto.*;
import com.fastcode.lkjh14.commons.logging.LoggingHelper;
import com.fastcode.lkjh14.domain.core.test6.ITest6Repository;
import com.fastcode.lkjh14.domain.core.test6.Test6Entity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.*;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.core.env.Environment;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.profiles.active=test")
public class Test6ControllerTest {

    @Autowired
    protected SortHandlerMethodArgumentResolver sortArgumentResolver;

    @Autowired
    @Qualifier("test6Repository")
    protected ITest6Repository test6_repository;

    @SpyBean
    @Qualifier("test6AppService")
    protected Test6AppService test6AppService;

    @SpyBean
    protected LoggingHelper logHelper;

    @SpyBean
    protected Environment env;

    @Mock
    protected Logger loggerMock;

    protected Test6Entity test6;

    protected MockMvc mvc;

    @Autowired
    EntityManagerFactory emf;

    static EntityManagerFactory emfs;

    static int relationCount = 10;

    @PostConstruct
    public void init() {
        emfs = emf;
    }

    @AfterClass
    public static void cleanup() {
        EntityManager em = emfs.createEntityManager();
        em.getTransaction().begin();
        em.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();
        em.createNativeQuery("truncate table public.test6 RESTART IDENTITY").executeUpdate();
        em.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
        em.getTransaction().commit();
    }

    public Test6Entity createEntity() {
        Test6Entity test6Entity = new Test6Entity();
        test6Entity.setId(1);
        test6Entity.setVersion(1L);
        test6Entity.setVersiono(0L);

        return test6Entity;
    }

    public CreateTest6Input createTest6Input() {
        CreateTest6Input test6Input = new CreateTest6Input();
        test6Input.setVersion(5L);

        return test6Input;
    }

    public Test6Entity createNewEntity() {
        Test6Entity test6 = new Test6Entity();
        test6.setId(3);
        test6.setVersion(3L);

        return test6;
    }

    public Test6Entity createUpdateEntity() {
        Test6Entity test6 = new Test6Entity();
        test6.setId(4);
        test6.setVersion(4L);

        return test6;
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        final Test6Controller test6Controller = new Test6Controller(test6AppService, logHelper, env);
        when(logHelper.getLogger()).thenReturn(loggerMock);
        doNothing().when(loggerMock).error(anyString());

        this.mvc =
            MockMvcBuilders
                .standaloneSetup(test6Controller)
                .setCustomArgumentResolvers(sortArgumentResolver)
                .setControllerAdvice()
                .build();
    }

    @Before
    public void initTest() {
        test6 = createEntity();
        List<Test6Entity> list = test6_repository.findAll();
        if (!list.contains(test6)) {
            test6 = test6_repository.save(test6);
        }
    }

    @Test
    public void FindById_IdIsValid_ReturnStatusOk() throws Exception {
        mvc
            .perform(get("/test6/" + test6.getId() + "/").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void FindById_IdIsNotValid_ReturnStatusNotFound() {
        org.assertj.core.api.Assertions
            .assertThatThrownBy(
                () -> mvc.perform(get("/test6/999").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
            )
            .hasCause(new EntityNotFoundException("Not found"));
    }

    @Test
    public void CreateTest6_Test6DoesNotExist_ReturnStatusOk() throws Exception {
        CreateTest6Input test6Input = createTest6Input();

        ObjectWriter ow = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .writer()
            .withDefaultPrettyPrinter();

        String json = ow.writeValueAsString(test6Input);

        mvc.perform(post("/test6").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
    }

    @Test
    public void DeleteTest6_IdIsNotValid_ThrowEntityNotFoundException() {
        doReturn(null).when(test6AppService).findById(999);
        org.assertj.core.api.Assertions
            .assertThatThrownBy(
                () ->
                    mvc.perform(delete("/test6/999").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
            )
            .hasCause(new EntityNotFoundException("There does not exist a test6 with a id=999"));
    }

    @Test
    public void Delete_IdIsValid_ReturnStatusNoContent() throws Exception {
        Test6Entity entity = createNewEntity();
        entity.setVersiono(0L);
        entity = test6_repository.save(entity);

        FindTest6ByIdOutput output = new FindTest6ByIdOutput();
        output.setId(entity.getId());
        output.setVersion(entity.getVersion());

        Mockito.doReturn(output).when(test6AppService).findById(entity.getId());

        //    Mockito.when(test6AppService.findById(entity.getId())).thenReturn(output);

        mvc
            .perform(delete("/test6/" + entity.getId() + "/").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
    }

    @Test
    public void UpdateTest6_Test6DoesNotExist_ReturnStatusNotFound() throws Exception {
        doReturn(null).when(test6AppService).findById(999);

        UpdateTest6Input test6 = new UpdateTest6Input();
        test6.setId(999);
        test6.setVersion(999L);

        ObjectWriter ow = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .writer()
            .withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(test6);

        org.assertj.core.api.Assertions
            .assertThatThrownBy(
                () ->
                    mvc
                        .perform(put("/test6/999").contentType(MediaType.APPLICATION_JSON).content(json))
                        .andExpect(status().isOk())
            )
            .hasCause(new EntityNotFoundException("Unable to update. Test6 with id=999 not found."));
    }

    @Test
    public void UpdateTest6_Test6Exists_ReturnStatusOk() throws Exception {
        Test6Entity entity = createUpdateEntity();
        entity.setVersiono(0L);

        entity = test6_repository.save(entity);
        FindTest6ByIdOutput output = new FindTest6ByIdOutput();
        output.setId(entity.getId());
        output.setVersion(entity.getVersion());
        output.setVersiono(entity.getVersiono());

        Mockito.when(test6AppService.findById(entity.getId())).thenReturn(output);

        UpdateTest6Input test6Input = new UpdateTest6Input();
        test6Input.setId(entity.getId());
        test6Input.setVersion(entity.getVersion());

        ObjectWriter ow = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .writer()
            .withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(test6Input);

        mvc
            .perform(put("/test6/" + entity.getId() + "/").contentType(MediaType.APPLICATION_JSON).content(json))
            .andExpect(status().isOk());

        Test6Entity de = createUpdateEntity();
        de.setId(entity.getId());
        test6_repository.delete(de);
    }

    @Test
    public void FindAll_SearchIsNotNullAndPropertyIsValid_ReturnStatusOk() throws Exception {
        mvc
            .perform(get("/test6?search=id[equals]=1&limit=10&offset=1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void FindAll_SearchIsNotNullAndPropertyIsNotValid_ThrowException() throws Exception {
        org.assertj.core.api.Assertions
            .assertThatThrownBy(
                () ->
                    mvc
                        .perform(
                            get("/test6?search=test6id[equals]=1&limit=10&offset=1")
                                .contentType(MediaType.APPLICATION_JSON)
                        )
                        .andExpect(status().isOk())
            )
            .hasCause(new Exception("Wrong URL Format: Property test6id not found!"));
    }
}
