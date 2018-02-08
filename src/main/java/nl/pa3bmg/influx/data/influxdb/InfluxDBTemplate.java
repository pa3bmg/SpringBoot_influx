package nl.pa3bmg.influx.data.influxdb;

import org.influxdb.InfluxDB;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Pong;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.util.Assert;
import nl.pa3bmg.influx.data.influxdb.converter.PointCollectionConverter;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class InfluxDBTemplate<T> extends InfluxDBAccessor implements InfluxDBOperations<T>
{
  private PointCollectionConverter<T> converter;

  public InfluxDBTemplate()
  {

  }

  public InfluxDBTemplate(final InfluxDBConnectionFactory connectionFactory, final PointCollectionConverter<T> converter)
  {
    setConnectionFactory(connectionFactory);
    setConverter(converter);
  }


  public void setConverter(final PointCollectionConverter<T> converter)
  {
    this.converter = converter;
  }

  @Override
  public void afterPropertiesSet()
  {
    super.afterPropertiesSet();
    Assert.notNull(converter, "PointCollectionConverter is required");
  }

  @Override
  public void createDatabase()
  {
    final String database = getDatabase();
    getConnection().createDatabase(database);
  }

  @Override
  @SuppressWarnings("unchecked")
  public void write(final T... payload)
  {
    write(Arrays.asList(payload));
  }

  @Override
  public void write(final List<T> payload)
  {
    final String database = getDatabase();
    final String retentionPolicy = getConnectionFactory().getProperties().getRetentionPolicy();
    final BatchPoints ops = BatchPoints.database(database)
      .retentionPolicy(retentionPolicy)
      .consistency(InfluxDB.ConsistencyLevel.ALL)
      .build();
    payload.forEach(t -> converter.convert(t).forEach(ops::point));
    getConnection().write(ops);
  }

  @Override
  public QueryResult query(final Query query)
  {
    return getConnection().query(query);
  }

  @Override
  public QueryResult query(final Query query, final TimeUnit timeUnit)
  {
    return getConnection().query(query, timeUnit);
  }

  @Override
  public void query(Query query, int chunkSize, Consumer<QueryResult> consumer)
  {
    getConnection().query(query, chunkSize, consumer);
  }

  @Override
  public Pong ping()
  {
    return getConnection().ping();
  }

  @Override
  public String version()
  {
    return getConnection().version();
  }
}
