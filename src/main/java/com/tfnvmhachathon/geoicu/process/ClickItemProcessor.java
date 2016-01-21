package com.tfnvmhachathon.geoicu.process;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class ClickItemProcessor implements ItemProcessor<Click, Click>
{

	private static final Logger log = LoggerFactory.getLogger(ClickItemProcessor.class);

	@Override
	public Click process(Click click) throws Exception
	{
		log.info("Processing click for IP: {}", click.ip);
		// TODO query the DB
		click.latitude = new Random().nextDouble();
		click.longitude = new Random().nextDouble();
		return click;
	}

}
