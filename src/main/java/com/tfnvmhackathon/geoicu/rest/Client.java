package com.tfnvmhackathon.geoicu.rest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.web.client.RestTemplate;

import com.tfnvmhackathon.geoicu.rest.domain.Data;
import com.tfnvmhackathon.geoicu.rest.domain.HistoricalWeatherRequest;
import com.tfnvmhackathon.geoicu.rest.domain.Response;

public class Client {
	
	static final String API_KEY = "1a4a4abc8326a6d94175ce50897c1";
	static final String URL = "http://api.worldweatheronline.com/free/v2/past-weather.ashx?q=%s&format=json&date=%s&enddate=%s&tp=24&key=" + API_KEY;
	
	public static Response getHistoricalWeather(HistoricalWeatherRequest hwr) throws UnsupportedEncodingException{
		
		RestTemplate restTemplate = new RestTemplate();
		
		String PreparedURL = URL;
		PreparedURL = String.format(URL, hwr.getLatAndLon(), hwr.getDate(), hwr.getDate());
		
		Response response = restTemplate.getForObject(PreparedURL, Response.class);
		
		return response;
	}

}
