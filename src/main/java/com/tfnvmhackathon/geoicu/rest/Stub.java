package com.tfnvmhackathon.geoicu.rest;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.tfnvmhackathon.geoicu.rest.domain.HistoricalWeatherRequest;

public class Stub {

	public static void main(String[] args) throws UnsupportedEncodingException {

		HistoricalWeatherRequest hwr = new HistoricalWeatherRequest();
		
		Calendar calendar = new GregorianCalendar(2016,0,1);
		
		hwr.setDate(calendar.getTime());
		hwr.setLatitude(34.650000f);
		hwr.setLongitude(133.917000f);
		
		Client.getHistoricalWeather(hwr);
		
	}

}
