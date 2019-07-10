package com.justpz.tcontainers.hinernatedb.carservice;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@Suite.SuiteClasses({SampleTest.class, SampleTest2.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@RunWith(Suite.class)
public class Suite1IntegrationTest {
}
