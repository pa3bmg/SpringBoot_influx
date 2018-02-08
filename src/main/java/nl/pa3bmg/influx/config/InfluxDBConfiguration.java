package nl.pa3bmg.influx.config;

import org.influxdb.dto.Point;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import nl.pa3bmg.influx.data.influxdb.DefaultInfluxDBTemplate;
import nl.pa3bmg.influx.data.influxdb.InfluxDBConnectionFactory;
import nl.pa3bmg.influx.data.influxdb.InfluxDBProperties;
import nl.pa3bmg.influx.data.influxdb.InfluxDBTemplate;
import nl.pa3bmg.influx.data.influxdb.converter.PointConverter;

@Configuration
@EnableConfigurationProperties(InfluxDBProperties.class)
public class InfluxDBConfiguration {
	@Bean
	  public InfluxDBConnectionFactory connectionFactory(final InfluxDBProperties properties)
	  {
	    return new InfluxDBConnectionFactory(properties);
	  }

	  @Bean
	  public InfluxDBTemplate<Point> influxDBTemplate(final InfluxDBConnectionFactory connectionFactory)
	  {
	    /*
	     * You can use your own 'PointCollectionConverter' implementation, e.g. in case
	     * you want to use your own custom measurement object.
	     */
	    return new InfluxDBTemplate<>(connectionFactory, new PointConverter());
	  }
	  
	  @Bean
	  public DefaultInfluxDBTemplate defaultTemplate(final InfluxDBConnectionFactory connectionFactory)
	  {
	    /*
	     * If you are just dealing with Point objects from 'influxdb-java' you could
	     * also use an instance of class DefaultInfluxDBTemplate.
	     */
	    return new DefaultInfluxDBTemplate(connectionFactory);
	  }
}
