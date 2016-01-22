package com.tfnvmhackathon.geoicu.process;

import java.util.Arrays;

import com.google.common.base.Joiner;

public class Click
{

	private static final Joiner CSV_JOINER = Joiner.on(',');
	public static final String HEADERS = CSV_JOINER.join(Arrays.asList("domainCode", "ip", "countryCode", "country", "region", "city", "latitude",
		"longitude", "postalCode", "timezone"));

	public final String domainCode;
	public final Long ip;
	public String countryCode;
	public String country;
	public String region;
	public String city;
	public Double latitude;
	public Double longitude;
	public String postalCode;
	public String timezone;

	public Click(String domainCode, Long ip)
	{
		this.domainCode = domainCode;
		this.ip = ip;
	}

	public Click(Click click)
	{
		this(click.domainCode, click.ip);
	}

	public String toCsvString()
	{
		return CSV_JOINER.useForNull("[NULL]").join(this.domainCode, this.ip, this.countryCode, this.country, this.region, this.city, this.latitude,
			this.longitude, this.postalCode, this.timezone);
	}

}
