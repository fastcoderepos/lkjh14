package com.fastcode.lkjh14.restcontrollers.extended;

import com.fastcode.lkjh14.application.extended.test4.ITest4AppServiceExtended;
import com.fastcode.lkjh14.commons.logging.LoggingHelper;
import com.fastcode.lkjh14.restcontrollers.core.Test4Controller;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test4/extended")
public class Test4ControllerExtended extends Test4Controller {

    public Test4ControllerExtended(
        ITest4AppServiceExtended test4AppServiceExtended,
        LoggingHelper helper,
        Environment env
    ) {
        super(test4AppServiceExtended, helper, env);
    }
    //Add your custom code here

}
