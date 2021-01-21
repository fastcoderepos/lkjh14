package com.fastcode.lkjh14.domain.extended.authorization.user;

import com.fastcode.lkjh14.domain.core.authorization.user.IUserRepository;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.stereotype.Repository;

@JaversSpringDataAuditable
@Repository("userRepositoryExtended")
public interface IUserRepositoryExtended extends IUserRepository {
    //Add your custom code here
}
