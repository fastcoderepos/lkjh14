package com.fastcode.lkjh14.application.core.test4;

import com.fastcode.lkjh14.application.core.test4.dto.*;
import com.fastcode.lkjh14.commons.search.SearchCriteria;
import java.util.*;
import org.springframework.data.domain.Pageable;

public interface ITest4AppService {
    //CRUD Operations

    CreateTest4Output create(CreateTest4Input test4);

    void delete(Long id);

    UpdateTest4Output update(Long id, UpdateTest4Input input);

    FindTest4ByIdOutput findById(Long id);

    List<FindTest4ByIdOutput> find(SearchCriteria search, Pageable pageable) throws Exception;
    //Join Column Parsers
}
