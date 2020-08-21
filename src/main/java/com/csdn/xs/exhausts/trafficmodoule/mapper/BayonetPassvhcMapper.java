package com.csdn.xs.exhausts.trafficmodoule.mapper;

import com.csdn.xs.exhausts.trafficmodoule.domain.BayonetPassvhcDomain;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author YJJ
 * @Date: Created in 14:33 2020-06-05
 */
public interface BayonetPassvhcMapper {


    String findChannelNameByID(String channelID);

    List<String> findDistinctChannelID();

    List<BayonetPassvhcDomain> findBayonetByChannelID(String channelNum, @Param("date1") String date1, @Param("date2") String date2);
}
