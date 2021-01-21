package com.fastcode.lkjh14.application.core.test6.dto;

import java.time.*;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTest6Input {

    @NotNull(message = "version Should not be null")
    private Long version;
}
