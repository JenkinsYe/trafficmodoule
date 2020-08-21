package com.csdn.xs.exhausts.trafficmodoule.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author YJJ
 * @Date: Created in 16:48 2020-06-06
 */
public class CSVUtils {

    public static HashSet<String> read() {
        String filePath = "/Users/YJJ/Desktop/merge_remote.csv";
        HashSet<String> set = new HashSet<>();
        try {
            Reader reader = new FileReader(filePath);
            Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);
            for (CSVRecord csvRecord : records) {
                set.add(csvRecord.get("JYBH"));
            }
            reader.close();
            return set;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return set;
    }

    public static void writes(List<ArrayList<Object>> data) throws IOException {
        File file = new File("/Users/YJJ/Desktop/data.csv");
        Appendable printWriter = new PrintWriter(file, "utf8");
        CSVPrinter csvPrinter = CSVFormat.EXCEL.withHeader("检验编号", "no1", "co1", "hc1",
                "no2", "co2", "hc2",
                "no3", "co3", "hc3",
                "no4", "co4", "hc4",
                "no5", "co5", "hc5",
                "no6", "co6", "hc6",
                "no7", "co7", "hc7",
                "no8", "co8", "hc8",
                "no9", "co9", "hc9",
                "no10", "co10", "hc10",
                "no11", "co11", "hc11",
                "no12", "co12", "hc12",
                "no13", "co13", "hc13",
                "no14", "co14", "hc14",
                "no15", "co15", "hc15").print(printWriter);
        for (int i = 0; i < data.size(); i++) {
            ArrayList<Object> item = data.get(i);
            csvPrinter.printRecord(item);

        }
        csvPrinter.flush();
        csvPrinter.close();
    }
}
