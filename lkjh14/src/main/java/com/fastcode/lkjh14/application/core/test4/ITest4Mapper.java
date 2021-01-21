package com.fastcode.lkjh14.application.core.test4;

import com.fastcode.lkjh14.application.core.test4.dto.*;
import com.fastcode.lkjh14.domain.core.test4.Test4Entity;
import java.time.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ITest4Mapper {
    Test4Entity createTest4InputToTest4Entity(CreateTest4Input test4Dto);

    CreateTest4Output test4EntityToCreateTest4Output(Test4Entity entity);

    Test4Entity updateTest4InputToTest4Entity(UpdateTest4Input test4Dto);

    UpdateTest4Output test4EntityToUpdateTest4Output(Test4Entity entity);

    FindTest4ByIdOutput test4EntityToFindTest4ByIdOutput(Test4Entity entity);
}
