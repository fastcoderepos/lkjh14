package com.fastcode.lkjh14.application.extended.test4;

import com.fastcode.lkjh14.application.core.test4.Test4AppService;
import com.fastcode.lkjh14.commons.logging.LoggingHelper;
import com.fastcode.lkjh14.domain.extended.test4.ITest4RepositoryExtended;
import org.springframework.stereotype.Service;

@Service("test4AppServiceExtended")
public class Test4AppServiceExtended extends Test4AppService implements ITest4AppServiceExtended {

    public Test4AppServiceExtended(
        ITest4RepositoryExtended test4RepositoryExtended,
        ITest4MapperExtended mapper,
        LoggingHelper logHelper
    ) {
        super(test4RepositoryExtended, mapper, logHelper);
    }
    //Add your custom code here

}
