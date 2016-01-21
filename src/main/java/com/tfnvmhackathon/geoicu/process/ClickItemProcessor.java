package com.tfnvmhackathon.geoicu.process;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.jdbc.core.JdbcTemplate;

import com.google.common.base.Preconditions;

public class ClickItemProcessor implements ItemProcessor<Click, Click>
{

	private final JdbcTemplate jdbcTemplate;

	public ClickItemProcessor(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Click process(Click click) throws Exception
	{
		StringBuilder sql = new StringBuilder();
		sql.append(" select * ");
		sql.append(" from ip_address_lookup ip ");
		sql.append(" where ip.min_ip <= ");
		sql.append(click.ip);
		sql.append(" order by ip.min_ip desc ");
		sql.append(" limit 1; ");

		List<Click> res = this.jdbcTemplate.query(sql.toString(), (rs, rowNum) -> this.mapResultToClick(rs, click.ip));
		Preconditions.checkState(res.size() == 1);
		return res.get(0);
	}

	private Click mapResultToClick(ResultSet rs, Long ip) throws SQLException
	{
		Click click = new Click(ip);
		click.countryCode = rs.getString("country_code");
		click.country = rs.getString("country");
		click.region = rs.getString("region");
		click.city = rs.getString("city");
		click.latitude = rs.getDouble("latitude");
		click.longitude = rs.getDouble("longitude");
		click.postalCode = rs.getString("postal_code");
		click.timezone = rs.getString("timezone");
		return click;
	}

}
