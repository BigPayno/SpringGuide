package com.payno.jpa.config.multi;

/**
 * @author payno
 * @date 2019/12/5 11:29
 * @description
 */
public class MultiProperties {
    public static final String JPA_PREFIX1 = "spring.jpa.primary";
    public static final String JPA_PREFIX2 = "spring.jpa.second";
    public static final String BASE_PACKAGE = "com.payno.jpa.multi.entity.primary";
    public static final String BASE_REPO_PACKAGE = "com.payno.jpa.multi.repo.primary";
    public static final String DATA_SOURCE_PREFIX = "spring.datasource.primary";
    public static final String REPO1_POSTFIX = "impl1";
    public static final String BASE_PACKAGE2 = "com.payno.jpa.multi.entity.second";
    public static final String BASE_REPO_PACKAGE2 = "com.payno.jpa.multi.repo.second";
    public static final String DATA_SOURCE_PREFIX2 = "spring.datasource.second";
    public static final String REPO2_POSTFIX = "impl2";
}
