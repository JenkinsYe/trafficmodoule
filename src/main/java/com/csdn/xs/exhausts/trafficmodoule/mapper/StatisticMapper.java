package com.csdn.xs.exhausts.trafficmodoule.mapper;

import com.csdn.xs.exhausts.trafficmodoule.domain.StatisticDomain;

import java.util.List;

/**
 * @author YJJ
 * @Date: Created in 16:34 2020-06-05
 */
public interface StatisticMapper {

    void insertStatistic(List<StatisticDomain> domains);

}
