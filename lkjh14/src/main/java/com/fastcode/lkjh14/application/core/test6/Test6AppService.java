package com.fastcode.lkjh14.application.core.test6;

import com.fastcode.lkjh14.application.core.test6.dto.*;
import com.fastcode.lkjh14.commons.logging.LoggingHelper;
import com.fastcode.lkjh14.commons.search.*;
import com.fastcode.lkjh14.domain.core.test6.ITest6Repository;
import com.fastcode.lkjh14.domain.core.test6.QTest6Entity;
import com.fastcode.lkjh14.domain.core.test6.Test6Entity;
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

@Service("test6AppService")
@RequiredArgsConstructor
public class Test6AppService implements ITest6AppService {

    @Qualifier("test6Repository")
    @NonNull
    protected final ITest6Repository _test6Repository;

    @Qualifier("ITest6MapperImpl")
    @NonNull
    protected final ITest6Mapper mapper;

    @NonNull
    protected final LoggingHelper logHelper;

    @Transactional(propagation = Propagation.REQUIRED)
    public CreateTest6Output create(CreateTest6Input input) {
        Test6Entity test6 = mapper.createTest6InputToTest6Entity(input);

        Test6Entity createdTest6 = _test6Repository.save(test6);
        return mapper.test6EntityToCreateTest6Output(createdTest6);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public UpdateTest6Output update(Integer test6Id, UpdateTest6Input input) {
        Test6Entity test6 = mapper.updateTest6InputToTest6Entity(input);

        Test6Entity updatedTest6 = _test6Repository.save(test6);
        return mapper.test6EntityToUpdateTest6Output(updatedTest6);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Integer test6Id) {
        Test6Entity existing = _test6Repository.findById(test6Id).orElse(null);
        _test6Repository.delete(existing);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public FindTest6ByIdOutput findById(Integer test6Id) {
        Test6Entity foundTest6 = _test6Repository.findById(test6Id).orElse(null);
        if (foundTest6 == null) return null;

        return mapper.test6EntityToFindTest6ByIdOutput(foundTest6);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<FindTest6ByIdOutput> find(SearchCriteria search, Pageable pageable) throws Exception {
        Page<Test6Entity> foundTest6 = _test6Repository.findAll(search(search), pageable);
        List<Test6Entity> test6List = foundTest6.getContent();
        Iterator<Test6Entity> test6Iterator = test6List.iterator();
        List<FindTest6ByIdOutput> output = new ArrayList<>();

        while (test6Iterator.hasNext()) {
            Test6Entity test6 = test6Iterator.next();
            output.add(mapper.test6EntityToFindTest6ByIdOutput(test6));
        }
        return output;
    }

    protected BooleanBuilder search(SearchCriteria search) throws Exception {
        QTest6Entity test6 = QTest6Entity.test6Entity;
        if (search != null) {
            Map<String, SearchFields> map = new HashMap<>();
            for (SearchFields fieldDetails : search.getFields()) {
                map.put(fieldDetails.getFieldName(), fieldDetails);
            }
            List<String> keysList = new ArrayList<String>(map.keySet());
            checkProperties(keysList);
            return searchKeyValuePair(test6, map, search.getJoinColumns());
        }
        return null;
    }

    protected void checkProperties(List<String> list) throws Exception {
        for (int i = 0; i < list.size(); i++) {
            if (
                !(
                    list.get(i).replace("%20", "").trim().equals("id") ||
                    list.get(i).replace("%20", "").trim().equals("version")
                )
            ) {
                throw new Exception("Wrong URL Format: Property " + list.get(i) + " not found!");
            }
        }
    }

    protected BooleanBuilder searchKeyValuePair(
        QTest6Entity test6,
        Map<String, SearchFields> map,
        Map<String, String> joinColumns
    ) {
        BooleanBuilder builder = new BooleanBuilder();

        for (Map.Entry<String, SearchFields> details : map.entrySet()) {
            if (details.getKey().replace("%20", "").trim().equals("id")) {
                if (
                    details.getValue().getOperator().equals("equals") &&
                    StringUtils.isNumeric(details.getValue().getSearchValue())
                ) builder.and(test6.id.eq(Integer.valueOf(details.getValue().getSearchValue()))); else if (
                    details.getValue().getOperator().equals("notEqual") &&
                    StringUtils.isNumeric(details.getValue().getSearchValue())
                ) builder.and(test6.id.ne(Integer.valueOf(details.getValue().getSearchValue()))); else if (
                    details.getValue().getOperator().equals("range")
                ) {
                    if (
                        StringUtils.isNumeric(details.getValue().getStartingValue()) &&
                        StringUtils.isNumeric(details.getValue().getEndingValue())
                    ) builder.and(
                        test6.id.between(
                            Integer.valueOf(details.getValue().getStartingValue()),
                            Integer.valueOf(details.getValue().getEndingValue())
                        )
                    ); else if (StringUtils.isNumeric(details.getValue().getStartingValue())) builder.and(
                        test6.id.goe(Integer.valueOf(details.getValue().getStartingValue()))
                    ); else if (StringUtils.isNumeric(details.getValue().getEndingValue())) builder.and(
                        test6.id.loe(Integer.valueOf(details.getValue().getEndingValue()))
                    );
                }
            }
            if (details.getKey().replace("%20", "").trim().equals("version")) {
                if (
                    details.getValue().getOperator().equals("equals") &&
                    StringUtils.isNumeric(details.getValue().getSearchValue())
                ) builder.and(test6.version.eq(Long.valueOf(details.getValue().getSearchValue()))); else if (
                    details.getValue().getOperator().equals("notEqual") &&
                    StringUtils.isNumeric(details.getValue().getSearchValue())
                ) builder.and(test6.version.ne(Long.valueOf(details.getValue().getSearchValue()))); else if (
                    details.getValue().getOperator().equals("range")
                ) {
                    if (
                        StringUtils.isNumeric(details.getValue().getStartingValue()) &&
                        StringUtils.isNumeric(details.getValue().getEndingValue())
                    ) builder.and(
                        test6.version.between(
                            Long.valueOf(details.getValue().getStartingValue()),
                            Long.valueOf(details.getValue().getEndingValue())
                        )
                    ); else if (StringUtils.isNumeric(details.getValue().getStartingValue())) builder.and(
                        test6.version.goe(Long.valueOf(details.getValue().getStartingValue()))
                    ); else if (StringUtils.isNumeric(details.getValue().getEndingValue())) builder.and(
                        test6.version.loe(Long.valueOf(details.getValue().getEndingValue()))
                    );
                }
            }
        }

        return builder;
    }
}
