package com.tfnvmhackathon.geoicu.rest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.web.client.RestTemplate;

import com.tfnvmhackathon.geoicu.rest.domain.Data;
import com.tfnvmhackathon.geoicu.rest.domain.HistoricalWeatherRequest;
import com.tfnvmhackathon.geoicu.rest.domain.HistoricalWeatherResponse;
import com.tfnvmhackathon.geoicu.rest.domain.Response;

public class Client {
	
	static final String API_KEY = "1a4a4abc8326a6d94175ce50897c1";
	static final String URL = "http://api.worldweatheronline.com/free/v2/past-weather.ashx?q=%s&format=json&date=%s&enddate=%s&tp=24&key=" + API_KEY;
	
	public static HistoricalWeatherResponse getHistoricalWeather(HistoricalWeatherRequest hwr) throws UnsupportedEncodingException{
		
		RestTemplate restTemplate = new RestTemplate();
		
		String PreparedURL = URL;
		
		if (hwr.getCityName() != null){
			PreparedURL = String.format(URL, hwr.getCityName(), hwr.getDate(), hwr.getDate());
		} else {
			PreparedURL = String.format(URL, hwr.getLatAndLon(), hwr.getDate(), hwr.getDate());
		}
		
		Response response = restTemplate.getForObject(PreparedURL, Response.class);
		
		HistoricalWeatherResponse queryResults = new HistoricalWeatherResponse();
		
		queryResults.setCityName(response.getData().getRequest().get(0).getQuery());
		queryResults.setCloudCover(Integer.valueOf(response.getData().getWeather().get(0).getHourly().get(0).getCloudcover()));
		queryResults.setPrecipMM(Float.valueOf(response.getData().getWeather().get(0).getHourly().get(0).getPrecipMM()));
		queryResults.setSunrise(response.getData().getWeather().get(0).getAstronomy().get(0).getSunrise());
		queryResults.setSunset(response.getData().getWeather().get(0).getAstronomy().get(0).getSunset());
		queryResults.setWeatherDescription(response.getData().getWeather().get(0).getHourly().get(0).getWeatherDesc().get(0).getValue());
		queryResults.setForecastImage(response.getData().getWeather().get(0).getHourly().get(0).getWeatherIconUrl().get(0).getValue());
		queryResults.setMaxTempC(Integer.valueOf(response.getData().getWeather().get(0).getMaxtempC()));
		queryResults.setMinTempC(Integer.valueOf(response.getData().getWeather().get(0).getMintempC()));
		queryResults.setUvIndex(Integer.valueOf(response.getData().getWeather().get(0).getUvIndex()));
		queryResults.setFeelsLikeC(Integer.valueOf(response.getData().getWeather().get(0).getHourly().get(0).getFeelsLikeC()));
		
		return queryResults;
	}

}
