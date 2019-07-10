package com.justpz.tcontainers.hinernatedb.carservice;

import org.junit.Test;
import org.junit.jupiter.api.Order;

import javax.persistence.TypedQuery;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SampleTest extends BaseJpaTest {

    @Test
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
    @Order(1)
    public void name3() {
        doInTransaction(() -> {
            Car car = new Car();
            car.setName("Mustang");
            em.persist(car);
            assertThat(car.getId()).isNotNull();

        });


    }


}
