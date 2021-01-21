package com.fastcode.lkjh14.application.core.test4;

import com.fastcode.lkjh14.application.core.test4.dto.*;
import com.fastcode.lkjh14.commons.logging.LoggingHelper;
import com.fastcode.lkjh14.commons.search.*;
import com.fastcode.lkjh14.domain.core.test4.ITest4Repository;
import com.fastcode.lkjh14.domain.core.test4.QTest4Entity;
import com.fastcode.lkjh14.domain.core.test4.Test4Entity;
import com.querydsl.core.BooleanBuilder;
import java.time.*;
import java.util.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("test4AppService")
@RequiredArgsConstructor
public class Test4AppService implements ITest4AppService {

    @Qualifier("test4Repository")
    @NonNull
    protected final ITest4Repository _test4Repository;

    @Qualifier("ITest4MapperImpl")
    @NonNull
    protected final ITest4Mapper mapper;

    @NonNull
    protected final LoggingHelper logHelper;

    @Transactional(propagation = Propagation.REQUIRED)
    public CreateTest4Output create(CreateTest4Input input) {
        Test4Entity test4 = mapper.createTest4InputToTest4Entity(input);

        Test4Entity createdTest4 = _test4Repository.save(test4);
        return mapper.test4EntityToCreateTest4Output(createdTest4);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public UpdateTest4Output update(Long test4Id, UpdateTest4Input input) {
        Test4Entity test4 = mapper.updateTest4InputToTest4Entity(input);

        Test4Entity updatedTest4 = _test4Repository.save(test4);
        return mapper.test4EntityToUpdateTest4Output(updatedTest4);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Long test4Id) {
        Test4Entity existing = _test4Repository.findById(test4Id).orElse(null);
        _test4Repository.delete(existing);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public FindTest4ByIdOutput findById(Long test4Id) {
        Test4Entity foundTest4 = _test4Repository.findById(test4Id).orElse(null);
        if (foundTest4 == null) return null;

        return mapper.test4EntityToFindTest4ByIdOutput(foundTest4);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<FindTest4ByIdOutput> find(SearchCriteria search, Pageable pageable) throws Exception {
        Page<Test4Entity> foundTest4 = _test4Repository.findAll(search(search), pageable);
        List<Test4Entity> test4List = foundTest4.getContent();
        Iterator<Test4Entity> test4Iterator = test4List.iterator();
        List<FindTest4ByIdOutput> output = new ArrayList<>();

        while (test4Iterator.hasNext()) {
            Test4Entity test4 = test4Iterator.next();
            output.add(mapper.test4EntityToFindTest4ByIdOutput(test4));
        }
        return output;
    }

    protected BooleanBuilder search(SearchCriteria search) throws Exception {
        QTest4Entity test4 = QTest4Entity.test4Entity;
        if (search != null) {
            Map<String, SearchFields> map = new HashMap<>();
            for (SearchFields fieldDetails : search.getFields()) {
                map.put(fieldDetails.getFieldName(), fieldDetails);
            }
            List<String> keysList = new ArrayList<String>(map.keySet());
            checkProperties(keysList);
            return searchKeyValuePair(test4, map, search.getJoinColumns());
        }
        return null;
    }

    protected void checkProperties(List<String> list) throws Exception {
        for (int i = 0; i < list.size(); i++) {
            if (!(list.get(i).replace("%20", "").trim().equals("id"))) {
                throw new Exception("Wrong URL Format: Property " + list.get(i) + " not found!");
            }
        }
    }

    protected BooleanBuilder searchKeyValuePair(
        QTest4Entity test4,
        Map<String, SearchFields> map,
        Map<String, String> joinColumns
    ) {
        BooleanBuilder builder = new BooleanBuilder();

        for (Map.Entry<String, SearchFields> details : map.entrySet()) {
            if (details.getKey().replace("%20", "").trim().equals("id")) {
                if (
                    details.getValue().getOperator().equals("equals") &&
                    StringUtils.isNumeric(details.getValue().getSearchValue())
                ) builder.and(test4.id.eq(Long.valueOf(details.getValue().getSearchValue()))); else if (
                    details.getValue().getOperator().equals("notEqual") &&
                    StringUtils.isNumeric(details.getValue().getSearchValue())
                ) builder.and(test4.id.ne(Long.valueOf(details.getValue().getSearchValue()))); else if (
                    details.getValue().getOperator().equals("range")
                ) {
                    if (
                        StringUtils.isNumeric(details.getValue().getStartingValue()) &&
                        StringUtils.isNumeric(details.getValue().getEndingValue())
                    ) builder.and(
                        test4.id.between(
                            Long.valueOf(details.getValue().getStartingValue()),
                            Long.valueOf(details.getValue().getEndingValue())
                        )
                    ); else if (StringUtils.isNumeric(details.getValue().getStartingValue())) builder.and(
                        test4.id.goe(Long.valueOf(details.getValue().getStartingValue()))
                    ); else if (StringUtils.isNumeric(details.getValue().getEndingValue())) builder.and(
                        test4.id.loe(Long.valueOf(details.getValue().getEndingValue()))
                    );
                }
            }
        }

        return builder;
    }
}
