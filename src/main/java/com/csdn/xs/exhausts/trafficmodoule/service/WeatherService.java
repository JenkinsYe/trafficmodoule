package com.csdn.xs.exhausts.trafficmodoule.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.csdn.xs.exhausts.trafficmodoule.domain.WeatherDomain;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author YJJ
 * @Date: Created in 12:08 2020-06-08
 */
@Service
@Slf4j
public class WeatherService {

    @Autowired
    private WeatherDateService weatherDateService;


    public void getWeather() throws Exception{
        log.info("开始获取天气");
        String s = getWeatherStringFromGaodeAPI();
        if (s == null) throw new Exception();
        JSONObject json = JSONObject.parseObject(s);
        JSONArray array = json.getJSONArray("lives");
        if (array.size() == 0) throw new Exception();
        JSONObject livesJson = array.getJSONObject(0);

        WeatherDomain weatherDomain = new WeatherDomain();
        weatherDomain.setCity(livesJson.getString("city"));
        weatherDomain.setWeather(livesJson.getString("weather"));
        weatherDomain.setWindDirection(livesJson.getString("winddirection"));
        weatherDomain.setWindPower(livesJson.getString("windpower"));
        weatherDomain.setTemperature(livesJson.getDouble("temperature"));
        weatherDomain.setHumidity(livesJson.getDouble("humidity"));
        weatherDomain.setReportTime(livesJson.getDate("reporttime"));

        weatherDateService.insertWeather(weatherDomain);
    }


    private String getWeatherStringFromGaodeAPI() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet httpGet = new HttpGet("https://restapi.amap.com/v3/weather/weatherInfo?key=33d364322cf95db87253d19a883a2bb4&city=330109");

        CloseableHttpResponse response = null;
        try {
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(5000)
                    .setConnectionRequestTimeout(5000)
                    .setSocketTimeout(5000)
                    .setRedirectsEnabled(true)
                    .build();

            httpGet.setConfig(requestConfig);
            response = httpClient.execute(httpGet);

            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

}
