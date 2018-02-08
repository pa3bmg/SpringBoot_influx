package nl.pa3bmg.influx.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.influxdb.dto.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import nl.pa3bmg.influx.data.influxdb.InfluxDBTemplate;

@Component
public class ScheduledTasks {
	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
	
	@Autowired
	private InfluxDBTemplate<Point> influxDBTemplate;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
    		log.info("reportCurrentTime Current Thread : {}", Thread.currentThread().getName());
        log.info("The time is now {}", dateFormat.format(new Date()));
        influxDBTemplate.createDatabase();
        final Point p = Point.measurement("disk")
        		  .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
        		  .tag("tenant", "default")
        		  .addField("used", 80L)
        		  .addField("free", 1L)
        		  .build();
        		influxDBTemplate.write(p);
    }
}
