/*
 * 文件名称: ConnectionConfig.java Copyright 2011-2015 PengKe_Zhang All right reserved.
 */
package org.pk.config;

/**
 * @author PengKe_Zhang
 */
public class ConnectionConfig {
  // 数据库驱动
  private String driverClass;
  // 连接地址
  private String jdbcUrl;
  // 用户名
  private String user;
  // 密码
  private String password;

  public String getDriverClass() {
    return driverClass;
  }

  public void setDriverClass(String driverClass) {
    this.driverClass = driverClass;
  }

  public String getJdbcUrl() {
    return jdbcUrl;
  }

  public void setJdbcUrl(String jdbcUrl) {
    this.jdbcUrl = jdbcUrl;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
