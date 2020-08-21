package com.csdn.xs.exhausts.trafficmodoule.service;

import com.csdn.xs.exhausts.trafficmodoule.domain.BayonetPassvhcDomain;
import com.csdn.xs.exhausts.trafficmodoule.domain.StatisticDomain;
import com.csdn.xs.exhausts.trafficmodoule.mapper.BayonetPassvhcMapper;
import com.csdn.xs.exhausts.trafficmodoule.mapper.StatisticMapper;
import com.csdn.xs.exhausts.trafficmodoule.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author YJJ
 * @Date: Created in 14:37 2020-06-05
 */
@Service
@Slf4j
public class TrafficDataService {

    private SqlSessionFactory factory;

    @PostConstruct
    public void init() {
        InputStream in = null;
        try {
            in = Resources.getResourceAsStream("sqlmap/sqlmapconfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        factory = new SqlSessionFactoryBuilder().build(in);
    }

    public List<String> findDistinctChannel() {
        SqlSession sqlSession = factory.openSession(true);
        BayonetPassvhcMapper mapper = sqlSession.getMapper(BayonetPassvhcMapper.class);
        List<String> result = mapper.findDistinctChannelID();

        return result;
    }

    public List<BayonetPassvhcDomain> findBayonetByChannelNumAnHourAgo(String num, Date date) {
        SqlSession sqlSession = factory.openSession(true);
        List<BayonetPassvhcDomain> result;
        try {

            BayonetPassvhcMapper mapper = sqlSession.getMapper(BayonetPassvhcMapper.class);
            String dateString1 = DateUtils.parseDateToString(date);
            String dateString2 = DateUtils.beforeOneHourToNowDateString(date);
            result = mapper.findBayonetByChannelID(num, dateString2, dateString1);
        } finally {
            sqlSession.close();
        }

        return result;
    }

    public void insertAllStatistic(List<StatisticDomain> domains) {
        SqlSession sqlSession = factory.openSession(true);
        StatisticMapper mapper = sqlSession.getMapper(StatisticMapper.class);
        try {
            mapper.insertStatistic(domains);
        } finally {
            sqlSession.close();
        }
    }

    public String findChannelNameByChannelID(String channelID) {
        SqlSession sqlSession =factory.openSession(true);
        BayonetPassvhcMapper mapper = sqlSession.getMapper(BayonetPassvhcMapper.class);
        try {
            return mapper.findChannelNameByID(channelID);
        } finally {
            sqlSession.close();
        }
    }
}
