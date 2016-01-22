package com.tfnvmhackathon.geoicu.rest.domain;

import com.google.common.base.Joiner;

public class HistoricalWeatherResponse {
	
	private int minTempC;
	private int maxTempC;
	private int uvIndex;
	private String forecastImage;
	private String weatherDescription;
	private float precipMM;
	private String sunrise;
	private String sunset;
	private int cloudCover;
	private int feelsLikeC;
	private String cityName;
	
	public int getMinTempC() {
		return minTempC;
	}
	public void setMinTempC(int minTempC) {
		this.minTempC = minTempC;
	}
	public int getMaxTempC() {
		return maxTempC;
	}
	public void setMaxTempC(int maxTempC) {
		this.maxTempC = maxTempC;
	}
	public int getUvIndex() {
		return uvIndex;
	}
	public void setUvIndex(int uvIndex) {
		this.uvIndex = uvIndex;
	}
	public String getForecastImage() {
		return forecastImage;
	}
	public void setForecastImage(String forecastImage) {
		this.forecastImage = forecastImage;
	}
	public String getWeatherDescription() {
		return weatherDescription;
	}
	public void setWeatherDescription(String weatherDescription) {
		this.weatherDescription = weatherDescription;
	}
	public float getPrecipMM() {
		return precipMM;
	}
	public void setPrecipMM(float precipMM) {
		this.precipMM = precipMM;
	}
	public String getSunrise() {
		return sunrise;
	}
	public void setSunrise(String sunrise) {
		this.sunrise = sunrise;
	}
	public String getSunset() {
		return sunset;
	}
	public void setSunset(String sunset) {
		this.sunset = sunset;
	}
	public int getCloudCover() {
		return cloudCover;
	}
	public void setCloudCover(int cloudCover) {
		this.cloudCover = cloudCover;
	}
	public int getFeelsLikeC() {
		return feelsLikeC;
	}
	public void setFeelsLikeC(int feelsLikeC) {
		this.feelsLikeC = feelsLikeC;
	}
	
	public String toCSVString() {
		return Joiner.on(',').useForNull("[NULL]")
				.join(this.getCityName(), this.getMinTempC(), this.getMaxTempC(), this.getUvIndex(), this.getForecastImage(), this.getWeatherDescription(),
					  this.getPrecipMM(), this.getSunrise(), this.getSunset(), this.getCloudCover(), this.getFeelsLikeC());
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
}
