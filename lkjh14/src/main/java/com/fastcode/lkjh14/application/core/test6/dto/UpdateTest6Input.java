package com.fastcode.lkjh14.application.core.test6.dto;

import java.time.*;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTest6Input {

    @NotNull(message = "id Should not be null")
    private Integer id;

    @NotNull(message = "version Should not be null")
    private Long version;

    private Long versiono;
}
