package com.fastcode.lkjh14.domain.extended.test6;

import com.fastcode.lkjh14.domain.core.test6.ITest6Repository;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.stereotype.Repository;

@JaversSpringDataAuditable
@Repository("test6RepositoryExtended")
public interface ITest6RepositoryExtended extends ITest6Repository {
    //Add your custom code here
}
