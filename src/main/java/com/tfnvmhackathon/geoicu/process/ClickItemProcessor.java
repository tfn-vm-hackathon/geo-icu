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

		List<Click> res = this.jdbcTemplate.query(sql.toString(), (rs, rowNum) -> this.mapResultToClick(rs, click));
		Preconditions.checkState(res.size() == 1);
		return res.get(0);
	}

	private Click mapResultToClick(ResultSet rs, Click click) throws SQLException
	{
		Click queriedClick = new Click(click);
		queriedClick.countryCode = rs.getString("country_code");
		queriedClick.country = rs.getString("country");
		queriedClick.region = rs.getString("region");
		queriedClick.city = rs.getString("city");
		queriedClick.latitude = rs.getDouble("latitude");
		queriedClick.longitude = rs.getDouble("longitude");
		queriedClick.postalCode = rs.getString("postal_code");
		queriedClick.timezone = rs.getString("timezone");
		return queriedClick;
	}

}
