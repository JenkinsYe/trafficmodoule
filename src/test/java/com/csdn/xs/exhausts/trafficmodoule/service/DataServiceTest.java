package com.csdn.xs.exhausts.trafficmodoule.service;

import com.csdn.xs.exhausts.trafficmodoule.domain.StatisticDomain;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author YJJ
 * @Date: Created in 17:13 2020-06-05
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DataServiceTest {

    @Autowired
    private TrafficDataService trafficDataService;

    @Test
    public void test() {
        log.info(trafficDataService.findDistinctChannel() + "");
    }


}
