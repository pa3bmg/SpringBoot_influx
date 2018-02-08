package nl.pa3bmg.influx.data.influxdb;


import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties("spring.influxdb")
public class InfluxDBProperties
{
  @NotEmpty
  private String url;

  @NotEmpty
  private String username;

  private String password;

  @NotEmpty
  private String database;

  @NotEmpty
  private String retentionPolicy;

  private int connectTimeout = 10;

  private int readTimeout = 30;

  private int writeTimeout = 10;

  private boolean gzip = false;

  public String getUrl()
  {
    return url;
  }

  public void setUrl(final String url)
  {
    this.url = url;
  }

  public String getUsername()
  {
    return username;
  }

  public void setUsername(final String username)
  {
    this.username = username;
  }

  public String getPassword()
  {
    return password;
  }

  public void setPassword(final String password)
  {
    this.password = password;
  }

  public String getDatabase()
  {
    return database;
  }

  public void setDatabase(final String database)
  {
    this.database = database;
  }

  public String getRetentionPolicy()
  {
    return retentionPolicy;
  }

  public void setRetentionPolicy(final String retentionPolicy)
  {
    this.retentionPolicy = retentionPolicy;
  }

  public int getWriteTimeout()
  {
    return writeTimeout;
  }

  public void setWriteTimeout(int writeTimeout)
  {
    this.writeTimeout = writeTimeout;
  }

  public int getConnectTimeout()
  {
    return connectTimeout;
  }

  public void setConnectTimeout(int connectTimeout)
  {
    this.connectTimeout = connectTimeout;
  }

  public int getReadTimeout()
  {
    return readTimeout;
  }

  public void setReadTimeout(int readTimeout)
  {
    this.readTimeout = readTimeout;
  }

  public boolean isGzip()
  {
    return gzip;
  }

  public void setGzip(boolean gzip)
  {
    this.gzip = gzip;
  }
}
