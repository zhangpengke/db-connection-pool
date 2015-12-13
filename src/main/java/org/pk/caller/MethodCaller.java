/*
 * 文件名称: MethodInvokeCallback.java Copyright 2011-2015 PengKe_Zhang All right reserved.
 */
package org.pk.caller;

import java.lang.reflect.Method;
import java.sql.Connection;

import org.pk.pool.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author PengKe_Zhang
 */
public abstract class MethodCaller {

  private final Logger log = LoggerFactory.getLogger(getClass());
  private ConnectionPool connectionPool;

  public MethodCaller(ConnectionPool connectionPool) {
    super();
    this.connectionPool = connectionPool;
  }

  public Connection getConnection() throws Exception {
    return connectionPool.borrowObject();
  }

  public abstract Object callMethod(Method method, Object[] args);

  public void returnConnection(Connection connection) {
    connectionPool.returnObject(connection);
  }

  public void returnBrokenConnection(Connection connection) {
    try {
      connectionPool.invalidateObject(connection);
    } catch (Exception e) {
      log.error("return broken connection error,connection is:{}", connection, e);
    }
  }
}
