package com.justpz.tcontainers.hinernatedb.carservice;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;

@SuppressWarnings("JUnit5Platform")
@RunWith(JUnitPlatform.class)
@SuiteDisplayName("Integration tests")
@SelectClasses({DatabaseInsertIntegrationTest.class, DatabaseUpdateIntegrationTest.class})
public class SuiteTest {
}
