package com.csdn.xs.exhausts.trafficmodoule.service;

import com.csdn.xs.exhausts.trafficmodoule.domain.ProcessDomain;
import com.csdn.xs.exhausts.trafficmodoule.util.CSVUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author YJJ
 * @Date: Created in 15:42 2020-06-06
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class VSPServiceTest {

    @Autowired
    private VSPService vspService;

    @Autowired
    private ProcessDataService processDataService;

    @Test
    public void test() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date oldDate1 = format.parse("2012-05-13 15:16:00");
            Date oldDate2 = format.parse("2012-05-13 15:16:20");
            ProcessDomain domain1 = new ProcessDomain();
            ProcessDomain domain2 = new ProcessDomain();
            domain1.setTestTime(oldDate1);
            domain2.setTestTime(oldDate2);

            domain1.setV(20d);
            domain2.setV(15d);
            log.info(vspService.getAcceleration(domain1, domain2).toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void batchTest() {
        //HashSet<String> set = CSVUtils.read();
        List<String> list = processDataService.findDistinctTestID();
        int i = 1;
        for (String s : list) {
            vspService.start(s);
            log.info("No." + i + " Completed");
            i++;
        }
    }


    @Test
    public void binTest() {
        HashSet<String> set = CSVUtils.read();
        int i = 1;
        int count = 0;
        List<ArrayList<Object>> data = new ArrayList<>();
        for (String s : set) {
            List<ProcessDomain> domainList = processDataService.findProcessByTestID(s);
            log.info("No." + i + " Completed");
            i++;
            if (domainList.size() == 195) {
                List<ArrayList<Object>> tempList = new ArrayList<>();
                int line = 1;
                while (line <= 15) {
                    ArrayList<Object> temp = new ArrayList<>();
                    temp.add(0d);
                    temp.add(0d);
                    temp.add(0d);
                    tempList.add(temp);
                    line++;
                }
                for (int j = 0 ; j < domainList.size(); j++) {
                    ProcessDomain domain = domainList.get(j);
                    ArrayList<Object> temp = tempList.get(getIndex(j+1));
                    temp.set(0, (Double)temp.get(0) + (domain.getNo()) / getDivider(j+1));
                    temp.set(1, (Double)temp.get(1) + (domain.getCo()) / getDivider(j+1));
                    temp.set(2, (Double)temp.get(2) + (domain.getHc()) / getDivider(j+1));
                    tempList.set(getIndex(j + 1), temp);
                }
                ArrayList<Object> tempData = new ArrayList<>();
                tempData.add(s);
                for (int j = 0; j < 15; j++) {
                    tempData.addAll(tempList.get(j));
                }
                data.add(tempData);
            }
        }
        log.info("total : " + count);
        try {
            CSVUtils.writes(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        HashSet<String> set = CSVUtils.read();
        int i = 1;
        for (String s : set) {
            if (i == 548) {
                log.info(s);
            }
            i++;
        }
        log.info("size: " + set.size());
    }

    private int getDivider(int index) {
        if (index <= 11)
            return 11;
        if (index <= 15)
            return 4;
        if (index <= 23)
            return 8;
        if (index <= 28)
            return 5;
        if (index <= 49)
            return 21;
        if (index <= 61)
            return 12;
        if (index <= 85)
            return 24;
        if (index <= 96)
            return 11;
        if (index <= 117)
            return 21;
        if (index <= 143)
            return 26;
        if (index <= 155)
            return 12;
        if (index <= 163)
            return 8;
        if (index <= 176)
            return 13;
        if (index <= 188)
            return 12;
        else
            return 7;
    }

    private int getIndex(int index) {
        if (index <= 11)
            return 0;
        if (index <= 15)
            return 1;
        if (index <= 23)
            return 2;
        if (index <= 28)
            return 3;
        if (index <= 49)
            return 4;
        if (index <= 61)
            return 5;
        if (index <= 85)
            return 6;
        if (index <= 96)
            return 7;
        if (index <= 117)
            return 8;
        if (index <= 143)
            return 9;
        if (index <= 155)
            return 10;
        if (index <= 163)
            return 11;
        if (index <= 176)
            return 12;
        if (index <= 188)
            return 13;
        else
            return 14;

    }

    @Test
    public void test4() {
        log.info(vspService.getVSPBin(0d) + "");
        log.info(vspService.getVSPBin(0.5d) + "");
        log.info(vspService.getVSPBin(-1.12d) + "");
        log.info(vspService.getVSPBin(20.0d) + "");
        log.info(vspService.getVSPBin(-20.0d) + "");
        log.info(vspService.getVSPBin(28.8d) + "");
        log.info(vspService.getVSPBin(10.0d) + "");
        log.info(vspService.getVSPBin(-10.0d) + "");


    }
}
