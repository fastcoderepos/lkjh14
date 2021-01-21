package com.fastcode.lkjh14.domain.extended.test4;

import com.fastcode.lkjh14.domain.core.test4.ITest4Repository;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.stereotype.Repository;

@JaversSpringDataAuditable
@Repository("test4RepositoryExtended")
public interface ITest4RepositoryExtended extends ITest4Repository {
    //Add your custom code here
}
