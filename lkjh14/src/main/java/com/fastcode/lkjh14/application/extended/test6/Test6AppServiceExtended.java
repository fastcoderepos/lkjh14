package com.fastcode.lkjh14.application.extended.test6;

import com.fastcode.lkjh14.application.core.test6.Test6AppService;
import com.fastcode.lkjh14.commons.logging.LoggingHelper;
import com.fastcode.lkjh14.domain.extended.test6.ITest6RepositoryExtended;
import org.springframework.stereotype.Service;

@Service("test6AppServiceExtended")
public class Test6AppServiceExtended extends Test6AppService implements ITest6AppServiceExtended {

    public Test6AppServiceExtended(
        ITest6RepositoryExtended test6RepositoryExtended,
        ITest6MapperExtended mapper,
        LoggingHelper logHelper
    ) {
        super(test6RepositoryExtended, mapper, logHelper);
    }
    //Add your custom code here

}
