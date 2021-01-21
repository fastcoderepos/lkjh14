package com.fastcode.lkjh14.restcontrollers.extended;

import com.fastcode.lkjh14.application.extended.test6.ITest6AppServiceExtended;
import com.fastcode.lkjh14.commons.logging.LoggingHelper;
import com.fastcode.lkjh14.restcontrollers.core.Test6Controller;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test6/extended")
public class Test6ControllerExtended extends Test6Controller {

    public Test6ControllerExtended(
        ITest6AppServiceExtended test6AppServiceExtended,
        LoggingHelper helper,
        Environment env
    ) {
        super(test6AppServiceExtended, helper, env);
    }
    //Add your custom code here

}
