//package com.xyz.online.movie.config;
//
//import java.util.EnumSet;
//import java.util.concurrent.TimeUnit;
//
//import org.coursera.metrics.datadog.DatadogReporter;
//import org.coursera.metrics.datadog.DatadogReporter.Expansion;
//import org.coursera.metrics.datadog.transport.HttpTransport;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.codahale.metrics.MetricRegistry;
//
//@Configuration
//@ConfigurationProperties("management.metrics.export.datadog")
//public class DatadogReporterConfig {
//
//  private static final Logger LOGGER = LoggerFactory.getLogger(DatadogReporterConfig.class);
//
//  /** Datadog API key used to authenticate every request to Datadog API */
//  private String apiKey;
//
//  /** Logical name associated to all the events send by this application */
//  private String uri;
//
//  /** Time, in seconds, between every call to Datadog API. The lower this value the more information will be send to Datadog */
//  private long period;
//
//  /** This flag enables or disables the datadog reporter */
//  private boolean enabled = false;
//
//  @Bean
//  @Autowired
//  public DatadogReporter datadogReporter(MetricRegistry registry) {
//
//      DatadogReporter reporter = null;
//      if(enabled) {
//          reporter = enableDatadogMetrics(registry);
//      } else {
//          if(LOGGER.isWarnEnabled()) {
//              LOGGER.info("Datadog reporter is disabled. To turn on this feature just set 'rJavaServer.metrics.enabled:true' in your config file (property or YAML)");
//          }
//      }
//
//      return reporter;
//  }
//
//  private DatadogReporter enableDatadogMetrics(MetricRegistry registry) {
//
//      if(LOGGER.isInfoEnabled()) {
//          LOGGER.info("Initializing Datadog reporter using [ uri: {}, period(seconds):{}, api-key:{} ]", getUri(), getPeriod(), getApiKey());
//      }
//
//      EnumSet<Expansion> expansions = DatadogReporter.Expansion.ALL;
//      HttpTransport httpTransport = new HttpTransport
//                                .Builder()
//                                .withApiKey(getApiKey())
//                                .build();
//
//      DatadogReporter reporter = DatadogReporter.forRegistry(registry)
//    		  
//        .withHost(getUri())
//        .withTransport(httpTransport)
//        .withExpansions(expansions)
//        .build();
//
//      reporter.start(getPeriod(), TimeUnit.SECONDS);
//
//      if(LOGGER.isInfoEnabled()) {
//          LOGGER.info("Datadog reporter successfully initialized");
//      }
//
//      return reporter;
//  }
//
//  /**
//   * @return Datadog API key used to authenticate every request to Datadog API
//   */
//  public String getApiKey() {
//      return apiKey;
//  }
//
//  /**
//   * @param apiKey Datadog API key used to authenticate every request to Datadog API
//   */
//  public void setApiKey(String apiKey) {
//      this.apiKey = apiKey;
//  }
//
//  /**
//   * @return Logical name associated to all the events send by this application
//   */
//
//  
//  public String getUri() {
//	return uri;
//}
//
//  /**
//   * @param uri Logical name associated to all the events send by this application
//   */
//
//  
//  public void setUri(String uri) {
//	this.uri = uri;
//}
//
//  /**
//   * @return Time, in seconds, between every call to Datadog API. The lower this value the more information will be send to Datadog
//   */
//  public long getPeriod() {
//      return period;
//  }
//
//  /**
//   * @param period Time, in seconds, between every call to Datadog API. The lower this value the more information will be send to Datadog
//   */
//  public void setPeriod(long period) {
//      this.period = period;
//  }
//
//  /**
//   * @return true if DatadogReporter is enabled in this application
//   */
//  public boolean isEnabled() {
//      return enabled;
//  }
//
//  /**
//   * This flag enables or disables the datadog reporter.
//   * This flag is only read during initialization, subsequent changes on this value will no take effect 
//   * @param enabled
//   */
//  public void setEnabled(boolean enabled) {
//      this.enabled = enabled;
//  }
//}
