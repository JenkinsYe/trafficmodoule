package com.csdn.xs.exhausts.trafficmodoule.service;

import com.alibaba.fastjson.JSON;
import com.csdn.xs.exhausts.trafficmodoule.domain.BayonetPassvhcDomain;
import com.csdn.xs.exhausts.trafficmodoule.domain.StatisticDomain;
import com.csdn.xs.exhausts.trafficmodoule.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author YJJ
 * @Date: Created in 14:54 2020-06-05
 */
@Service
@Slf4j
public class StatisticService {

    @Autowired
    private TrafficDataService trafficDataService;

    public Date start() {
        List<String> channelIDs = trafficDataService.findDistinctChannel();
        Date nowDate = new Date();
        log.info("总共 " + channelIDs.size() + " 速度不为0的路口");

        nowDate = DateUtils.getHourTime(nowDate);
        List<StatisticDomain> statisticDomains = new ArrayList<>();
        for (int i = 0; i < channelIDs.size(); i++) {
            Long total = 0l;
            log.info("正在处理第 " + (i+1) + "个路口");
            List<BayonetPassvhcDomain> domains = trafficDataService.findBayonetByChannelNumAnHourAgo(channelIDs.get(i), nowDate);
            log.info("size : " + domains.size());
            HashMap<String, Object> map = new HashMap<>();
            StatisticDomain statisticDomain = new StatisticDomain();
            statisticDomain.setAmount(domains.size());
            statisticDomain.setTime(DateUtils.beforeOneHourToNowDate(nowDate));

            statisticDomain.setChannelID(channelIDs.get(i));
            statisticDomain.setChannelName(trafficDataService.findChannelNameByChannelID(channelIDs.get(i)));

            for (BayonetPassvhcDomain domain : domains) {
                total += Long.parseLong(domain.getSpeed());
                HashMap<String, Integer> tempMap = (HashMap<String, Integer>) map.get(domain.getVhcplateType());
                if (tempMap == null) {
                    tempMap = new HashMap<>();
                    tempMap.put(domain.getSpeed(), 1);
                } else {
                    if (tempMap.get(domain.getSpeed()) == null) {
                        tempMap.put(domain.getSpeed(), 1);
                    } else {
                        tempMap.put(domain.getSpeed(), tempMap.get(domain.getSpeed()) + 1);
                    }
                }
                map.put(domain.getVhcplateType(), tempMap);
            }
            map.put("averageSpeed", domains.size() != 0 ? total / domains.size() : null);
            statisticDomain.setTypeAndNum(JSON.toJSONString(map));

            statisticDomains.add(statisticDomain);
        }

        trafficDataService.insertAllStatistic(statisticDomains);

        log.info("统计完成");
        return statisticDomains.size() == 0 ? null : statisticDomains.get(0).getTime();
    }
}
