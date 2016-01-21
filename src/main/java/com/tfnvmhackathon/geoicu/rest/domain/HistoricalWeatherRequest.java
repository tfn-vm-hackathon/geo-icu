package com.tfnvmhackathon.geoicu.rest.domain;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HistoricalWeatherRequest {
	
	float latitude;
	float longitude;
	Date date;
	
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");	
		return sdf.format(date);
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getLatAndLon(){
		String ret = "";
		try {
			ret = URLEncoder.encode(String.valueOf(this.getLatitude()) + "," + String.valueOf(this.getLongitude()), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return ret;
	}

}
