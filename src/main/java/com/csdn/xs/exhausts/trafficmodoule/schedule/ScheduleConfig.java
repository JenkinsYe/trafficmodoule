package com.csdn.xs.exhausts.trafficmodoule.schedule;

import com.csdn.xs.exhausts.trafficmodoule.service.StatisticService;
import com.csdn.xs.exhausts.trafficmodoule.service.WeatherService;
import com.csdn.xs.exhausts.trafficmodoule.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.util.Date;

/**
 * @author YJJ
 * @Date: Created in 12:06 2020-06-08
 */
@Configuration
@EnableScheduling
@Slf4j
public class ScheduleConfig {

    @Autowired
    private StatisticService statisticService;

    @Autowired
    private WeatherService weatherService;

    @Scheduled(cron = "0 10 0/1 * * ?")
    public void trafficDataStatistic() {
        log.info("开始统计交通小脑数据");
        Date date = statisticService.start();
        log.info("生成模式数据");
        String arg = DateUtils.parseDateToSimpleString(date);
        String command = "python /home/jianglinhui/s_car/road_scripts/run_all.py " + arg;
        log.info("command:" + command);

        try {
            Process ps = Runtime.getRuntime().exec(command);
            log.info("waiting...");
            ps.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("模式数据生成结束");
    }

    //@Scheduled(cron = "0 30 0/1 * * ?")
    public void weather() {
        try {
            weatherService.getWeather();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
