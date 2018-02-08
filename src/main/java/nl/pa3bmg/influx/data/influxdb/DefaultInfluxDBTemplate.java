package nl.pa3bmg.influx.data.influxdb;

import org.influxdb.dto.Point;

import nl.pa3bmg.influx.data.influxdb.converter.PointConverter;


public class DefaultInfluxDBTemplate extends InfluxDBTemplate<Point> {
	
	public DefaultInfluxDBTemplate() {

	}

	public DefaultInfluxDBTemplate(final InfluxDBConnectionFactory connectionFactory) {
		    super(connectionFactory, new PointConverter());
	}
}
