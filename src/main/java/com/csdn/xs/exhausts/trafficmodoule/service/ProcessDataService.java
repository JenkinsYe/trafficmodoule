package com.csdn.xs.exhausts.trafficmodoule.service;

import com.csdn.xs.exhausts.trafficmodoule.domain.ProcessDomain;
import com.csdn.xs.exhausts.trafficmodoule.mapper.ProcessMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author YJJ
 * @Date: Created in 14:59 2020-06-06
 */
@Service
public class ProcessDataService {
    private SqlSessionFactory factory;

    @PostConstruct
    public void init() {
        InputStream in = null;
        try {
            in = Resources.getResourceAsStream("sqlmap/processsqlmapconfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        factory = new SqlSessionFactoryBuilder().build(in);
    }

    List<ProcessDomain> findProcessByTestID(String testID) {
        SqlSession sqlSession = factory.openSession(true);
        ProcessMapper mapper = sqlSession.getMapper(ProcessMapper.class);
        List<ProcessDomain> result;
        try {
            result = mapper.findProcessByTestID(testID);
        } finally {
            sqlSession.close();
        }

        return result;
    }

    void updateProcess(List<ProcessDomain> domains) {
        SqlSession sqlSession = factory.openSession(true);
        ProcessMapper mapper = sqlSession.getMapper(ProcessMapper.class);

        try {
            mapper.updateProcess(domains);
        } finally {
            sqlSession.close();
        }
    }


    public List<String> findDistinctTestID() {
        SqlSession sqlSession = factory.openSession(true);
        ProcessMapper mapper = sqlSession.getMapper(ProcessMapper.class);

        try {
            List<String> results = mapper.findDistinctTestId();
            return results;
        } finally {
            sqlSession.close();
        }
    }
}
