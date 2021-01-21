package com.fastcode.lkjh14.application.core.test4.dto;

import java.time.*;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTest4Input {

    @NotNull(message = "id Should not be null")
    private Long id;

    private Long versiono;
}
