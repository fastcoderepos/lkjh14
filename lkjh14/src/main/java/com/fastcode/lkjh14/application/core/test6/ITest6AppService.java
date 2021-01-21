package com.fastcode.lkjh14.application.core.test6;

import com.fastcode.lkjh14.application.core.test6.dto.*;
import com.fastcode.lkjh14.commons.search.SearchCriteria;
import java.util.*;
import org.springframework.data.domain.Pageable;

public interface ITest6AppService {
    //CRUD Operations

    CreateTest6Output create(CreateTest6Input test6);

    void delete(Integer id);

    UpdateTest6Output update(Integer id, UpdateTest6Input input);

    FindTest6ByIdOutput findById(Integer id);

    List<FindTest6ByIdOutput> find(SearchCriteria search, Pageable pageable) throws Exception;
    //Join Column Parsers
}
