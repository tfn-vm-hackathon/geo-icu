package com.tfnvmhackathon.geoicu;

import org.springframework.boot.SpringApplication;

public class MainGeoICU
{

	public static void main(String[] args)
	{
		System.exit(SpringApplication.exit(SpringApplication.run(BatchConfiguration.class, args)));
	}

}
