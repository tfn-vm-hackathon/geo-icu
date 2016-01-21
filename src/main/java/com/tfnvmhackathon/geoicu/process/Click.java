package com.tfnvmhackathon.geoicu.process;

import com.google.common.base.Joiner;

public class Click
{

	public final Long ip;

	public String country;

	public String city;

	public Double latitude;

	public Double longitude;

	public Click(Long ip)
	{
		this.ip = ip;
	}

	public String toCsvString()
	{
		return Joiner.on(',').join(this.ip, this.country, this.city, this.latitude, this.longitude);
	}

}
