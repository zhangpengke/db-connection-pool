/*
 * 文件名称: JDBCConnectionBuilder.java Copyright 2011-2015 PengKe_Zhang All right reserved.
 */
package org.pk.builder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.pk.config.ConnectionConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

/**
 * @author PengKe_Zhang
 */
public class SimpleConnectionBuilder implements ConnectionBuilder {

  private static final Logger log = LoggerFactory.getLogger(SimpleConnectionBuilder.class);

  public static final SimpleConnectionBuilder builder = new SimpleConnectionBuilder();

  public static final SimpleConnectionBuilder getInstance() {
    return builder;
  }

  @Override
  public Connection build(ConnectionConfig connectionConfig) {
    Preconditions.checkNotNull(connectionConfig, "connectionConfig is null");

    Connection conn = null;
    String driverClass = connectionConfig.getDriverClass();
    String jdbcUrl = connectionConfig.getJdbcUrl();
    String user = connectionConfig.getUser();
    String password = connectionConfig.getPassword();

    Preconditions.checkNotNull(driverClass, "driverclass is null");
    Preconditions.checkNotNull(jdbcUrl, "jdbUrl is null");
    Preconditions.checkNotNull(user, "user is null");
    // 密码可能为空，对password不作检查
    try {
      Class.forName(connectionConfig.getDriverClass());
      conn = DriverManager.getConnection(jdbcUrl, user, password);
    } catch (ClassNotFoundException e) {
      log.error("driver class:{} is error", e);
    } catch (SQLException e) {
      log.error("can't connect to db,user is:{},password is:{}", user, password);
      log.error(e.getMessage(), e);
    }
    return conn;
  }
}
