package com.csdn.xs.exhausts.trafficmodoule.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author YJJ
 * @Date: Created in 13:06 2020-06-08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class WeatherServiceTest {

    @Autowired
    private WeatherService weatherService;

    @Test
    public void test() {
        try {
            weatherService.getWeather();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
