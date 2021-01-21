package com.fastcode.lkjh14.application.core.test6;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fastcode.lkjh14.application.core.test6.dto.*;
import com.fastcode.lkjh14.commons.logging.LoggingHelper;
import com.fastcode.lkjh14.commons.search.*;
import com.fastcode.lkjh14.domain.core.test6.*;
import com.fastcode.lkjh14.domain.core.test6.QTest6Entity;
import com.fastcode.lkjh14.domain.core.test6.Test6Entity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import java.time.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class Test6AppServiceTest {

    @InjectMocks
    @Spy
    protected Test6AppService _appService;

    @Mock
    protected ITest6Repository _test6Repository;

    @Mock
    protected ITest6Mapper _mapper;

    @Mock
    protected Logger loggerMock;

    @Mock
    protected LoggingHelper logHelper;

    protected static Integer ID = 15;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(_appService);
        when(logHelper.getLogger()).thenReturn(loggerMock);
        doNothing().when(loggerMock).error(anyString());
    }

    @Test
    public void findTest6ById_IdIsNotNullAndIdDoesNotExist_ReturnNull() {
        Optional<Test6Entity> nullOptional = Optional.ofNullable(null);
        Mockito.when(_test6Repository.findById(any(Integer.class))).thenReturn(nullOptional);
        Assertions.assertThat(_appService.findById(ID)).isEqualTo(null);
    }

    @Test
    public void findTest6ById_IdIsNotNullAndIdExists_ReturnTest6() {
        Test6Entity test6 = mock(Test6Entity.class);
        Optional<Test6Entity> test6Optional = Optional.of((Test6Entity) test6);
        Mockito.when(_test6Repository.findById(any(Integer.class))).thenReturn(test6Optional);

        Assertions.assertThat(_appService.findById(ID)).isEqualTo(_mapper.test6EntityToFindTest6ByIdOutput(test6));
    }

    @Test
    public void createTest6_Test6IsNotNullAndTest6DoesNotExist_StoreTest6() {
        Test6Entity test6Entity = mock(Test6Entity.class);
        CreateTest6Input test6Input = new CreateTest6Input();

        Mockito.when(_mapper.createTest6InputToTest6Entity(any(CreateTest6Input.class))).thenReturn(test6Entity);
        Mockito.when(_test6Repository.save(any(Test6Entity.class))).thenReturn(test6Entity);

        Assertions
            .assertThat(_appService.create(test6Input))
            .isEqualTo(_mapper.test6EntityToCreateTest6Output(test6Entity));
    }

    @Test
    public void updateTest6_Test6IdIsNotNullAndIdExists_ReturnUpdatedTest6() {
        Test6Entity test6Entity = mock(Test6Entity.class);
        UpdateTest6Input test6 = mock(UpdateTest6Input.class);

        Mockito.when(_mapper.updateTest6InputToTest6Entity(any(UpdateTest6Input.class))).thenReturn(test6Entity);
        Mockito.when(_test6Repository.save(any(Test6Entity.class))).thenReturn(test6Entity);
        Assertions
            .assertThat(_appService.update(ID, test6))
            .isEqualTo(_mapper.test6EntityToUpdateTest6Output(test6Entity));
    }

    @Test
    public void deleteTest6_Test6IsNotNullAndTest6Exists_Test6Removed() {
        Test6Entity test6 = mock(Test6Entity.class);
        Optional<Test6Entity> test6Optional = Optional.of((Test6Entity) test6);
        Mockito.when(_test6Repository.findById(any(Integer.class))).thenReturn(test6Optional);

        _appService.delete(ID);
        verify(_test6Repository).delete(test6);
    }

    @Test
    public void find_ListIsEmpty_ReturnList() throws Exception {
        List<Test6Entity> list = new ArrayList<>();
        Page<Test6Entity> foundPage = new PageImpl(list);
        Pageable pageable = mock(Pageable.class);
        List<FindTest6ByIdOutput> output = new ArrayList<>();
        SearchCriteria search = new SearchCriteria();
        //		search.setType(1);
        //		search.setValue("xyz");
        //		search.setOperator("equals");

        Mockito.when(_appService.search(any(SearchCriteria.class))).thenReturn(new BooleanBuilder());
        Mockito.when(_test6Repository.findAll(any(Predicate.class), any(Pageable.class))).thenReturn(foundPage);
        Assertions.assertThat(_appService.find(search, pageable)).isEqualTo(output);
    }

    @Test
    public void find_ListIsNotEmpty_ReturnList() throws Exception {
        List<Test6Entity> list = new ArrayList<>();
        Test6Entity test6 = mock(Test6Entity.class);
        list.add(test6);
        Page<Test6Entity> foundPage = new PageImpl(list);
        Pageable pageable = mock(Pageable.class);
        List<FindTest6ByIdOutput> output = new ArrayList<>();
        SearchCriteria search = new SearchCriteria();
        //		search.setType(1);
        //		search.setValue("xyz");
        //		search.setOperator("equals");
        output.add(_mapper.test6EntityToFindTest6ByIdOutput(test6));

        Mockito.when(_appService.search(any(SearchCriteria.class))).thenReturn(new BooleanBuilder());
        Mockito.when(_test6Repository.findAll(any(Predicate.class), any(Pageable.class))).thenReturn(foundPage);
        Assertions.assertThat(_appService.find(search, pageable)).isEqualTo(output);
    }

    @Test
    public void searchKeyValuePair_PropertyExists_ReturnBooleanBuilder() {
        QTest6Entity test6 = QTest6Entity.test6Entity;
        SearchFields searchFields = new SearchFields();
        searchFields.setOperator("equals");
        searchFields.setSearchValue("xyz");
        Map<String, SearchFields> map = new HashMap<>();
        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("xyz", String.valueOf(ID));
        BooleanBuilder builder = new BooleanBuilder();
        Assertions.assertThat(_appService.searchKeyValuePair(test6, map, searchMap)).isEqualTo(builder);
    }

    @Test(expected = Exception.class)
    public void checkProperties_PropertyDoesNotExist_ThrowException() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("xyz");
        _appService.checkProperties(list);
    }

    @Test
    public void checkProperties_PropertyExists_ReturnNothing() throws Exception {
        List<String> list = new ArrayList<>();
        _appService.checkProperties(list);
    }

    @Test
    public void search_SearchIsNotNullAndSearchContainsCaseThree_ReturnBooleanBuilder() throws Exception {
        Map<String, SearchFields> map = new HashMap<>();
        QTest6Entity test6 = QTest6Entity.test6Entity;
        List<SearchFields> fieldsList = new ArrayList<>();
        SearchFields fields = new SearchFields();
        SearchCriteria search = new SearchCriteria();
        search.setType(3);
        search.setValue("xyz");
        search.setOperator("equals");
        fields.setOperator("equals");
        fields.setSearchValue("xyz");
        fieldsList.add(fields);
        search.setFields(fieldsList);
        BooleanBuilder builder = new BooleanBuilder();
        Mockito.doNothing().when(_appService).checkProperties(any(List.class));
        Mockito
            .doReturn(builder)
            .when(_appService)
            .searchKeyValuePair(any(QTest6Entity.class), any(HashMap.class), any(HashMap.class));

        Assertions.assertThat(_appService.search(search)).isEqualTo(builder);
    }

    @Test
    public void search_StringIsNull_ReturnNull() throws Exception {
        Assertions.assertThat(_appService.search(null)).isEqualTo(null);
    }
}
