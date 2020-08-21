package com.csdn.xs.exhausts.trafficmodoule.service;

import com.csdn.xs.exhausts.trafficmodoule.domain.WeatherDomain;
import com.csdn.xs.exhausts.trafficmodoule.mapper.WeatherMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author YJJ
 * @Date: Created in 12:30 2020-06-08
 */
@Service
public class WeatherDateService {

    private SqlSessionFactory factory;

    @PostConstruct
    public void init() {
        InputStream in = null;
        try {
            in = Resources.getResourceAsStream("sqlmap/weathersqlmap.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        factory = new SqlSessionFactoryBuilder().build(in);
    }

    public void insertWeather(WeatherDomain domain) {
        SqlSession sqlSession = factory.openSession(true);
        WeatherMapper mapper = sqlSession.getMapper(WeatherMapper.class);

        try {
            mapper.insertWeather(domain);
        } finally {
            sqlSession.close();
        }
    }
}
