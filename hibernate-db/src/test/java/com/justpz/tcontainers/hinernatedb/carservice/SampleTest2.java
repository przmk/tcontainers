package com.justpz.tcontainers.hinernatedb.carservice;


import com.justpz.tcontainers.hinernatedb.BaseJpaTest;
import org.junit.jupiter.api.Test;

import javax.persistence.TypedQuery;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SampleTest2 extends BaseJpaTest {

    @Test
    public void name2() {


        doInTransaction(() -> {
            TypedQuery<Car> query = em.createQuery("from Car", Car.class);
            List<Car> resultList = query.getResultList();
            assertThat(resultList).hasSize(1);
            Car car = resultList.get(0);
            assertThat(car.getId()).isNotNull();
            assertThat(car.getName()).isEqualTo("Mustang");
        });
    }
}
