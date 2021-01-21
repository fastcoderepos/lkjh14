package com.fastcode.lkjh14.application.core.test4;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fastcode.lkjh14.application.core.test4.dto.*;
import com.fastcode.lkjh14.commons.logging.LoggingHelper;
import com.fastcode.lkjh14.commons.search.*;
import com.fastcode.lkjh14.domain.core.test4.*;
import com.fastcode.lkjh14.domain.core.test4.QTest4Entity;
import com.fastcode.lkjh14.domain.core.test4.Test4Entity;
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
public class Test4AppServiceTest {

    @InjectMocks
    @Spy
    protected Test4AppService _appService;

    @Mock
    protected ITest4Repository _test4Repository;

    @Mock
    protected ITest4Mapper _mapper;

    @Mock
    protected Logger loggerMock;

    @Mock
    protected LoggingHelper logHelper;

    protected static Long ID = 15L;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(_appService);
        when(logHelper.getLogger()).thenReturn(loggerMock);
        doNothing().when(loggerMock).error(anyString());
    }

    @Test
    public void findTest4ById_IdIsNotNullAndIdDoesNotExist_ReturnNull() {
        Optional<Test4Entity> nullOptional = Optional.ofNullable(null);
        Mockito.when(_test4Repository.findById(anyLong())).thenReturn(nullOptional);
        Assertions.assertThat(_appService.findById(ID)).isEqualTo(null);
    }

    @Test
    public void findTest4ById_IdIsNotNullAndIdExists_ReturnTest4() {
        Test4Entity test4 = mock(Test4Entity.class);
        Optional<Test4Entity> test4Optional = Optional.of((Test4Entity) test4);
        Mockito.when(_test4Repository.findById(anyLong())).thenReturn(test4Optional);

        Assertions.assertThat(_appService.findById(ID)).isEqualTo(_mapper.test4EntityToFindTest4ByIdOutput(test4));
    }

    @Test
    public void createTest4_Test4IsNotNullAndTest4DoesNotExist_StoreTest4() {
        Test4Entity test4Entity = mock(Test4Entity.class);
        CreateTest4Input test4Input = new CreateTest4Input();

        Mockito.when(_mapper.createTest4InputToTest4Entity(any(CreateTest4Input.class))).thenReturn(test4Entity);
        Mockito.when(_test4Repository.save(any(Test4Entity.class))).thenReturn(test4Entity);

        Assertions
            .assertThat(_appService.create(test4Input))
            .isEqualTo(_mapper.test4EntityToCreateTest4Output(test4Entity));
    }

    @Test
    public void updateTest4_Test4IdIsNotNullAndIdExists_ReturnUpdatedTest4() {
        Test4Entity test4Entity = mock(Test4Entity.class);
        UpdateTest4Input test4 = mock(UpdateTest4Input.class);

        Mockito.when(_mapper.updateTest4InputToTest4Entity(any(UpdateTest4Input.class))).thenReturn(test4Entity);
        Mockito.when(_test4Repository.save(any(Test4Entity.class))).thenReturn(test4Entity);
        Assertions
            .assertThat(_appService.update(ID, test4))
            .isEqualTo(_mapper.test4EntityToUpdateTest4Output(test4Entity));
    }

    @Test
    public void deleteTest4_Test4IsNotNullAndTest4Exists_Test4Removed() {
        Test4Entity test4 = mock(Test4Entity.class);
        Optional<Test4Entity> test4Optional = Optional.of((Test4Entity) test4);
        Mockito.when(_test4Repository.findById(anyLong())).thenReturn(test4Optional);

        _appService.delete(ID);
        verify(_test4Repository).delete(test4);
    }

    @Test
    public void find_ListIsEmpty_ReturnList() throws Exception {
        List<Test4Entity> list = new ArrayList<>();
        Page<Test4Entity> foundPage = new PageImpl(list);
        Pageable pageable = mock(Pageable.class);
        List<FindTest4ByIdOutput> output = new ArrayList<>();
        SearchCriteria search = new SearchCriteria();
        //		search.setType(1);
        //		search.setValue("xyz");
        //		search.setOperator("equals");

        Mockito.when(_appService.search(any(SearchCriteria.class))).thenReturn(new BooleanBuilder());
        Mockito.when(_test4Repository.findAll(any(Predicate.class), any(Pageable.class))).thenReturn(foundPage);
        Assertions.assertThat(_appService.find(search, pageable)).isEqualTo(output);
    }

    @Test
    public void find_ListIsNotEmpty_ReturnList() throws Exception {
        List<Test4Entity> list = new ArrayList<>();
        Test4Entity test4 = mock(Test4Entity.class);
        list.add(test4);
        Page<Test4Entity> foundPage = new PageImpl(list);
        Pageable pageable = mock(Pageable.class);
        List<FindTest4ByIdOutput> output = new ArrayList<>();
        SearchCriteria search = new SearchCriteria();
        //		search.setType(1);
        //		search.setValue("xyz");
        //		search.setOperator("equals");
        output.add(_mapper.test4EntityToFindTest4ByIdOutput(test4));

        Mockito.when(_appService.search(any(SearchCriteria.class))).thenReturn(new BooleanBuilder());
        Mockito.when(_test4Repository.findAll(any(Predicate.class), any(Pageable.class))).thenReturn(foundPage);
        Assertions.assertThat(_appService.find(search, pageable)).isEqualTo(output);
    }

    @Test
    public void searchKeyValuePair_PropertyExists_ReturnBooleanBuilder() {
        QTest4Entity test4 = QTest4Entity.test4Entity;
        SearchFields searchFields = new SearchFields();
        searchFields.setOperator("equals");
        searchFields.setSearchValue("xyz");
        Map<String, SearchFields> map = new HashMap<>();
        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("xyz", String.valueOf(ID));
        BooleanBuilder builder = new BooleanBuilder();
        Assertions.assertThat(_appService.searchKeyValuePair(test4, map, searchMap)).isEqualTo(builder);
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
        QTest4Entity test4 = QTest4Entity.test4Entity;
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
            .searchKeyValuePair(any(QTest4Entity.class), any(HashMap.class), any(HashMap.class));

        Assertions.assertThat(_appService.search(search)).isEqualTo(builder);
    }

    @Test
    public void search_StringIsNull_ReturnNull() throws Exception {
        Assertions.assertThat(_appService.search(null)).isEqualTo(null);
    }
}
