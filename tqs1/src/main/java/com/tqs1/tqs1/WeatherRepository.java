package com.tqs1.tqs1;

import org.springframework.data.repository.CrudRepository;

public interface WeatherRepository extends CrudRepository<Weather, Long>{

    Weather findByLatitudeAndLongitude(double latitude,double longitude);

    boolean existsByLatitudeAndLongitude(double latitude, double longitude);

}
