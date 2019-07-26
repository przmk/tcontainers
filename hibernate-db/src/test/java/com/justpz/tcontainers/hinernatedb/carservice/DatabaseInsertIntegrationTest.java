package com.justpz.tcontainers.hinernatedb.carservice;

import com.justpz.tcontainers.hinernatedb.BaseJpaTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import javax.persistence.TypedQuery;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Integration test 1 👍")
class DatabaseInsertIntegrationTest extends BaseJpaTest {

    @Test
    @DisplayName("Should save 🚗")
    @Order(1)
    void shouldSaveCar() {
        doInTransaction(() -> {
            Car car = new Car();
            car.setName("Mustang");
            em.persist(car);
            assertThat(car.getId()).isNotNull();
        });
    }

    @Test
    @DisplayName("Should return list with one 🚗")
    @Order(2)
    void shouldReturnListWithOneCar() {
        doInTransaction(() -> {
            TypedQuery<Car> query = em.createQuery("from Car ", Car.class);
            List<Car> resultList = query.getResultList();
            assertThat(resultList).hasSize(1);
            Car car = resultList.get(0);
            assertThat(car.getId()).isNotNull();
            assertThat(car.getName()).isEqualTo("Mustang");
        });
    }

}
