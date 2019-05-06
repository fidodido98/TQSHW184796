package com.tqs1.tqs1;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class WeatherRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private WeatherRepository weatherRepo;

    @Test
    public void whenFindByLatitudeAndLongitude_thenReturnWeather(){

        //London Coordinates
        double latitude = 51.507351;
        double longitude = -0.127758;


        Weather london = new Weather(latitude, longitude);

        entityManager.persist(london);
        entityManager.flush();

        Weather found = weatherRepo.findByLatitudeAndLongitude(london.getLatitude(), london.getLongitude());

        assertThat(london.equals(found));

    }


}
