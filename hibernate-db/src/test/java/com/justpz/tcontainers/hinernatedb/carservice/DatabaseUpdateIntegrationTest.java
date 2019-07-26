package com.justpz.tcontainers.hinernatedb.carservice;


import com.justpz.tcontainers.hinernatedb.BaseJpaTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import javax.persistence.TypedQuery;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Integration test 2 👍")
class DatabaseUpdateIntegrationTest extends BaseJpaTest {

    @Test
    @DisplayName("Should change 🚗 name")
    @Order(1)
    void shouldChangeCarName() {
        doInTransaction(() -> {
            TypedQuery<Car> query = em.createQuery("from Car", Car.class);
            List<Car> resultList = query.getResultList();
            assertThat(resultList).hasSize(1);
            Car car = resultList.get(0);
            assertThat(car.getId()).isNotNull();
            car.setName("Shelby");
        });
    }

    @Test
    @DisplayName("Should return 🚗 with changed name")
    @Order(2)
    void shouldReturnCarWithChangedCarName() {
        doInTransaction(() -> {
            TypedQuery<Car> query = em.createQuery("from Car", Car.class);
            List<Car> resultList = query.getResultList();
            assertThat(resultList).hasSize(1);
            Car car = resultList.get(0);
            assertThat(car.getId()).isNotNull();
            assertThat(car.getName()).isEqualTo("Shelby");
        });
    }
}
