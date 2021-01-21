package com.fastcode.lkjh14.application.core.test6;

import com.fastcode.lkjh14.application.core.test6.dto.*;
import com.fastcode.lkjh14.domain.core.test6.Test6Entity;
import java.time.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ITest6Mapper {
    Test6Entity createTest6InputToTest6Entity(CreateTest6Input test6Dto);

    CreateTest6Output test6EntityToCreateTest6Output(Test6Entity entity);

    Test6Entity updateTest6InputToTest6Entity(UpdateTest6Input test6Dto);

    UpdateTest6Output test6EntityToUpdateTest6Output(Test6Entity entity);

    FindTest6ByIdOutput test6EntityToFindTest6ByIdOutput(Test6Entity entity);
}
