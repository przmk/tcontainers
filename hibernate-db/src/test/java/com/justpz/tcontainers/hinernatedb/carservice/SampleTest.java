package com.justpz.tcontainers.hinernatedb.carservice;

import com.justpz.tcontainers.hinernatedb.BaseJpaTest;
import org.junit.jupiter.api.*;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

@DisplayName("Lecą testy 👍 👶")
public class SampleTest extends BaseJpaTest {

    @Test
    @DisplayName("Test drugi 🆒")
    @Order(2)
    public void name2() {
        doInTransaction(() -> {
            TypedQuery<Car> query = em.createQuery("from Car ", Car.class);
            List<Car> resultList = query.getResultList();
            assertThat(resultList).hasSize(1);
            Car car = resultList.get(0);
            assertThat(car.getId()).isNotNull();
            assertThat(car.getName()).isEqualTo("Mustang");
        });
    }

    @Test
    @DisplayName("Test pierwszy 🦁")
    @Order(1)
    public void name3() {
        doInTransaction(() -> {
            Car car = new Car();
            car.setName("Mustang");
            em.persist(car);
            assertThat(car.getId()).isNotNull();

        });
    }

    @TestFactory
    @DisplayName("Test factory 🏭")
    Stream<DynamicNode> dynamicTestsWithContainers() {
        return Stream.of("A", "B", "C", "🐭")
                .map(input -> dynamicContainer("Container " + input, Stream.of(
                        dynamicTest("not null", () -> assertNotNull(input)),
                        dynamicContainer("properties", Stream.of(
                                dynamicTest("length > 0", () -> assertTrue(input.length() > 0)),
                                dynamicTest("not empty", () -> assertFalse(input.isEmpty()))
                        ))
                )));
    }


}
