package com.fastcode.lkjh14.restcontrollers.extended;

import com.fastcode.lkjh14.application.extended.authorization.role.IRoleAppServiceExtended;
import com.fastcode.lkjh14.application.extended.authorization.user.IUserAppServiceExtended;
import com.fastcode.lkjh14.application.extended.authorization.userrole.IUserroleAppServiceExtended;
import com.fastcode.lkjh14.commons.logging.LoggingHelper;
import com.fastcode.lkjh14.restcontrollers.core.UserroleController;
import com.fastcode.lkjh14.security.JWTAppService;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userrole/extended")
public class UserroleControllerExtended extends UserroleController {

    public UserroleControllerExtended(
        IUserroleAppServiceExtended userroleAppServiceExtended,
        IRoleAppServiceExtended roleAppServiceExtended,
        IUserAppServiceExtended userAppServiceExtended,
        JWTAppService jwtAppService,
        LoggingHelper helper,
        Environment env
    ) {
        super(userroleAppServiceExtended, roleAppServiceExtended, userAppServiceExtended, jwtAppService, helper, env);
    }
    //Add your custom code here

}
