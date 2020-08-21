package com.csdn.xs.exhausts.trafficmodoule.service;

import com.csdn.xs.exhausts.trafficmodoule.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author YJJ
 * @Date: Created in 16:56 2020-06-05
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class StatisticServiceTest {

    @Autowired
    private StatisticService statisticService;

    @Test
    public void test() {
        statisticService.start();
    }


    @Test
    public void test2() {
        log.info(DateUtils.parseDateToSimpleString(new Date()));
    }
}
