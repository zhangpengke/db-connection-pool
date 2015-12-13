/*
 * 文件名称: ConnectionPoolConfig.java Copyright 2011-2015 PengKe_Zhang All right reserved.
 */
package org.pk.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * @author PengKe_Zhang
 */
public class ConnectionPoolConfig extends GenericObjectPoolConfig {
  // 初始池大小
  private int initialPoolSize;
  // 最小池大小
  private int minPoolSize;
  // 最大池大小
  private int maxPoolSize;
  // 最大空闲时间
  private int maxIdleTime;
  // 拿连接最大等待时间
  private int checkoutTimeOut;

  public ConnectionPoolConfig() {
    setTestWhileIdle(true);
    setTimeBetweenEvictionRunsMillis(1000 * 60 * 10);
    setNumTestsPerEvictionRun(-1);
    setMaxWaitMillis(10000);
  }

  
  public ConnectionPoolConfig(int initialPoolSize, int minPoolSize, int maxPoolSize, int maxIdleTime,
      int checkoutTimeOut) {
    super();
    this.initialPoolSize = initialPoolSize;
    this.minPoolSize = minPoolSize;
    this.maxPoolSize = maxPoolSize;
    this.maxIdleTime = maxIdleTime;
    this.checkoutTimeOut = checkoutTimeOut;
  }


  public int getInitialPoolSize() {
    return initialPoolSize;
  }

  public void setInitialPoolSize(int initialPoolSize) {
    this.initialPoolSize = initialPoolSize;
  }

  public int getMinPoolSize() {
    return minPoolSize;
  }

  public void setMinPoolSize(int minPoolSize) {
    this.minPoolSize = minPoolSize;
  }

  public int getMaxPoolSize() {
    return maxPoolSize;
  }

  public void setMaxPoolSize(int maxPoolSize) {
    this.maxPoolSize = maxPoolSize;
  }

  public int getMaxIdleTime() {
    return maxIdleTime;
  }

  public void setMaxIdleTime(int maxIdleTime) {
    this.maxIdleTime = maxIdleTime;
  }

  public int getCheckoutTimeOut() {
    return checkoutTimeOut;
  }

  public void setCheckoutTimeOut(int checkoutTimeOut) {
    this.checkoutTimeOut = checkoutTimeOut;
  }
}
