package com.fastcode.lkjh14.addons.docmgmt;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = { "com.fastcode.lkjh14.addons.docmgmt", "org.springframework.versions" })
public class StoreConfig {}
