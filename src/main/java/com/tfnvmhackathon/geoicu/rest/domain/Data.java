package com.tfnvmhackathon.geoicu.rest.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "request", "weather" })
public class Data {

	@JsonProperty("request")
	private List<Request> request = new ArrayList<Request>();
	@JsonProperty("weather")
	private List<Weather> weather = new ArrayList<Weather>();
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The request
	 */
	@JsonProperty("request")
	public List<Request> getRequest() {
		return request;
	}

	/**
	 * 
	 * @param request
	 *            The request
	 */
	@JsonProperty("request")
	public void setRequest(List<Request> request) {
		this.request = request;
	}

	/**
	 * 
	 * @return The weather
	 */
	@JsonProperty("weather")
	public List<Weather> getWeather() {
		return weather;
	}

	/**
	 * 
	 * @param weather
	 *            The weather
	 */
	@JsonProperty("weather")
	public void setWeather(List<Weather> weather) {
		this.weather = weather;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}