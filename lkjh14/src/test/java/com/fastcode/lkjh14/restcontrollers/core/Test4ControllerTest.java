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

import com.fastcode.lkjh14.application.core.test4.Test4AppService;
import com.fastcode.lkjh14.application.core.test4.dto.*;
import com.fastcode.lkjh14.commons.logging.LoggingHelper;
import com.fastcode.lkjh14.domain.core.test4.ITest4Repository;
import com.fastcode.lkjh14.domain.core.test4.Test4Entity;
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
public class Test4ControllerTest {

    @Autowired
    protected SortHandlerMethodArgumentResolver sortArgumentResolver;

    @Autowired
    @Qualifier("test4Repository")
    protected ITest4Repository test4_repository;

    @SpyBean
    @Qualifier("test4AppService")
    protected Test4AppService test4AppService;

    @SpyBean
    protected LoggingHelper logHelper;

    @SpyBean
    protected Environment env;

    @Mock
    protected Logger loggerMock;

    protected Test4Entity test4;

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
        em.createNativeQuery("truncate table public.test4 RESTART IDENTITY").executeUpdate();
        em.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
        em.getTransaction().commit();
    }

    public Test4Entity createEntity() {
        Test4Entity test4Entity = new Test4Entity();
        test4Entity.setId(1L);
        test4Entity.setVersiono(0L);

        return test4Entity;
    }

    public CreateTest4Input createTest4Input() {
        CreateTest4Input test4Input = new CreateTest4Input();

        return test4Input;
    }

    public Test4Entity createNewEntity() {
        Test4Entity test4 = new Test4Entity();
        test4.setId(3L);

        return test4;
    }

    public Test4Entity createUpdateEntity() {
        Test4Entity test4 = new Test4Entity();
        test4.setId(4L);

        return test4;
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        final Test4Controller test4Controller = new Test4Controller(test4AppService, logHelper, env);
        when(logHelper.getLogger()).thenReturn(loggerMock);
        doNothing().when(loggerMock).error(anyString());

        this.mvc =
            MockMvcBuilders
                .standaloneSetup(test4Controller)
                .setCustomArgumentResolvers(sortArgumentResolver)
                .setControllerAdvice()
                .build();
    }

    @Before
    public void initTest() {
        test4 = createEntity();
        List<Test4Entity> list = test4_repository.findAll();
        if (!list.contains(test4)) {
            test4 = test4_repository.save(test4);
        }
    }

    @Test
    public void FindById_IdIsValid_ReturnStatusOk() throws Exception {
        mvc
            .perform(get("/test4/" + test4.getId() + "/").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void FindById_IdIsNotValid_ReturnStatusNotFound() {
        org.assertj.core.api.Assertions
            .assertThatThrownBy(
                () -> mvc.perform(get("/test4/999").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
            )
            .hasCause(new EntityNotFoundException("Not found"));
    }

    @Test
    public void CreateTest4_Test4DoesNotExist_ReturnStatusOk() throws Exception {
        CreateTest4Input test4Input = createTest4Input();

        ObjectWriter ow = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .writer()
            .withDefaultPrettyPrinter();

        String json = ow.writeValueAsString(test4Input);

        mvc.perform(post("/test4").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
    }

    @Test
    public void DeleteTest4_IdIsNotValid_ThrowEntityNotFoundException() {
        doReturn(null).when(test4AppService).findById(999L);
        org.assertj.core.api.Assertions
            .assertThatThrownBy(
                () ->
                    mvc.perform(delete("/test4/999").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
            )
            .hasCause(new EntityNotFoundException("There does not exist a test4 with a id=999"));
    }

    @Test
    public void Delete_IdIsValid_ReturnStatusNoContent() throws Exception {
        Test4Entity entity = createNewEntity();
        entity.setVersiono(0L);
        entity = test4_repository.save(entity);

        FindTest4ByIdOutput output = new FindTest4ByIdOutput();
        output.setId(entity.getId());

        Mockito.doReturn(output).when(test4AppService).findById(entity.getId());

        //    Mockito.when(test4AppService.findById(entity.getId())).thenReturn(output);

        mvc
            .perform(delete("/test4/" + entity.getId() + "/").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
    }

    @Test
    public void UpdateTest4_Test4DoesNotExist_ReturnStatusNotFound() throws Exception {
        doReturn(null).when(test4AppService).findById(999L);

        UpdateTest4Input test4 = new UpdateTest4Input();
        test4.setId(999L);

        ObjectWriter ow = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .writer()
            .withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(test4);

        org.assertj.core.api.Assertions
            .assertThatThrownBy(
                () ->
                    mvc
                        .perform(put("/test4/999").contentType(MediaType.APPLICATION_JSON).content(json))
                        .andExpect(status().isOk())
            )
            .hasCause(new EntityNotFoundException("Unable to update. Test4 with id=999 not found."));
    }

    @Test
    public void UpdateTest4_Test4Exists_ReturnStatusOk() throws Exception {
        Test4Entity entity = createUpdateEntity();
        entity.setVersiono(0L);

        entity = test4_repository.save(entity);
        FindTest4ByIdOutput output = new FindTest4ByIdOutput();
        output.setId(entity.getId());
        output.setVersiono(entity.getVersiono());

        Mockito.when(test4AppService.findById(entity.getId())).thenReturn(output);

        UpdateTest4Input test4Input = new UpdateTest4Input();
        test4Input.setId(entity.getId());

        ObjectWriter ow = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .writer()
            .withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(test4Input);

        mvc
            .perform(put("/test4/" + entity.getId() + "/").contentType(MediaType.APPLICATION_JSON).content(json))
            .andExpect(status().isOk());

        Test4Entity de = createUpdateEntity();
        de.setId(entity.getId());
        test4_repository.delete(de);
    }

    @Test
    public void FindAll_SearchIsNotNullAndPropertyIsValid_ReturnStatusOk() throws Exception {
        mvc
            .perform(get("/test4?search=id[equals]=1&limit=10&offset=1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void FindAll_SearchIsNotNullAndPropertyIsNotValid_ThrowException() throws Exception {
        org.assertj.core.api.Assertions
            .assertThatThrownBy(
                () ->
                    mvc
                        .perform(
                            get("/test4?search=test4id[equals]=1&limit=10&offset=1")
                                .contentType(MediaType.APPLICATION_JSON)
                        )
                        .andExpect(status().isOk())
            )
            .hasCause(new Exception("Wrong URL Format: Property test4id not found!"));
    }
}
