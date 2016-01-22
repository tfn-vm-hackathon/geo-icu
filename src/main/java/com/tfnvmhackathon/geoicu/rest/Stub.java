package com.tfnvmhackathon.geoicu.rest;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.retry.backoff.Sleeper;

import com.tfnvmhackathon.geoicu.rest.domain.HistoricalWeatherRequest;
import com.tfnvmhackathon.geoicu.rest.domain.HistoricalWeatherResponse;

public class Stub {

	public static void main(String[] args) throws UnsupportedEncodingException, InterruptedException {

		String[] cities = new String[]{"Aachen", "Augsburg", "Berlin", "Bielefeld", "Bochum", "Bonn", "Braunschweig", "Bremen", "Chemnitz", "Dortmund", "Dresden", "Duisburg", "Dusseldorf", "Erfurt", "Essen", "Frankfurt am Main", "Freiburg", "Gelsenkirchen", "Hagen", "Halle", "Hamburg", "Hamm", "Hanover", "Herne", "Karlsruhe", "Kassel", "Kiel", "Krefeld", "Leipzig", "Leverkusen", "Magdeburg", "Mainz", "Mannheim", "Monchengladbach", "Munich", "Munster", "Nuremberg", "Oberhausen", "Oldenburg", "Osnabruck", "Rostock", "Saarbrucken", "Solingen", "Stuttgart", "Wiesbaden", "Wuppertal"};
		//String[] cities = new String[]{"Aachen", "Augsburg", "Berlin"};
		
		for (String city: cities){
			
			HistoricalWeatherRequest hwr = new HistoricalWeatherRequest();
			
			Calendar calendar = new GregorianCalendar(2015,11,6);
			hwr.setDate(calendar.getTime());
			hwr.setCityName(city);
			
			HistoricalWeatherResponse hwResponse = Client.getHistoricalWeather(hwr);
			System.out.println(hwResponse.toCSVString());
			
			Thread.sleep(1000);
			
		}
		
	}

}
