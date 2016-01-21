package com.tfnvmhackathon.geoicu.rest.domain;

import java.util.HashMap;
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
@JsonPropertyOrder({ "moonrise", "moonset", "sunrise", "sunset" })
public class Astronomy {

	@JsonProperty("moonrise")
	private String moonrise;
	@JsonProperty("moonset")
	private String moonset;
	@JsonProperty("sunrise")
	private String sunrise;
	@JsonProperty("sunset")
	private String sunset;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The moonrise
	 */
	@JsonProperty("moonrise")
	public String getMoonrise() {
		return moonrise;
	}

	/**
	 * 
	 * @param moonrise
	 *            The moonrise
	 */
	@JsonProperty("moonrise")
	public void setMoonrise(String moonrise) {
		this.moonrise = moonrise;
	}

	/**
	 * 
	 * @return The moonset
	 */
	@JsonProperty("moonset")
	public String getMoonset() {
		return moonset;
	}

	/**
	 * 
	 * @param moonset
	 *            The moonset
	 */
	@JsonProperty("moonset")
	public void setMoonset(String moonset) {
		this.moonset = moonset;
	}

	/**
	 * 
	 * @return The sunrise
	 */
	@JsonProperty("sunrise")
	public String getSunrise() {
		return sunrise;
	}

	/**
	 * 
	 * @param sunrise
	 *            The sunrise
	 */
	@JsonProperty("sunrise")
	public void setSunrise(String sunrise) {
		this.sunrise = sunrise;
	}

	/**
	 * 
	 * @return The sunset
	 */
	@JsonProperty("sunset")
	public String getSunset() {
		return sunset;
	}

	/**
	 * 
	 * @param sunset
	 *            The sunset
	 */
	@JsonProperty("sunset")
	public void setSunset(String sunset) {
		this.sunset = sunset;
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