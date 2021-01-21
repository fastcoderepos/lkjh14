package com.fastcode.lkjh14.domain.core.test4;

import java.time.*;
import java.util.*;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@JaversSpringDataAuditable
@Repository("test4Repository")
public interface ITest4Repository extends JpaRepository<Test4Entity, Long>, QuerydslPredicateExecutor<Test4Entity> {}
