/*
 * 文件名称: ExecutorProxyBuilder.java Copyright 2011-2015 PengKe_Zhang All right reserved.
 */
package org.pk;

import java.lang.reflect.Proxy;

import org.pk.caller.GenerticMethodCaller;
import org.pk.caller.MethodCaller;
import org.pk.config.ConnectionConfig;
import org.pk.config.ConnectionPoolConfig;
import org.pk.executer.SQLExecuter;
import org.pk.factory.ConnectionFactory;
import org.pk.handler.ProxyInvocationHandler;
import org.pk.pool.ConnectionPool;

/**
 * @author PengKe_Zhang
 */
public class SQLExecuterProxyBuilder<SQLExecutor> {

  private MethodCaller methodCaller;
  private ConnectionPool connectionPool;

  public SQLExecuterProxyBuilder(ConnectionConfig connectionConfig, ConnectionPoolConfig connectionPoolConfig) {
    ConnectionFactory connectionFactory = ConnectionFactory.getInstance().config(connectionConfig);

    connectionPool = new ConnectionPool(connectionFactory, connectionPoolConfig);
    methodCaller = new GenerticMethodCaller(connectionPool);

  }

  public SQLExecuter build() {
    return (SQLExecuter) Proxy.newProxyInstance(SQLExecuter.class.getClassLoader(), new Class[] { SQLExecuter.class },
        new ProxyInvocationHandler(methodCaller));
  }

  public void releaseConnections() {
    if (connectionPool != null) {
      connectionPool.close();
    }
  }

}
