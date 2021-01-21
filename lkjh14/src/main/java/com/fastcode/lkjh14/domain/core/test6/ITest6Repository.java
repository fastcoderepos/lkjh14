package com.fastcode.lkjh14.domain.core.test6;

import java.time.*;
import java.util.*;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@JaversSpringDataAuditable
@Repository("test6Repository")
public interface ITest6Repository extends JpaRepository<Test6Entity, Integer>, QuerydslPredicateExecutor<Test6Entity> {}
