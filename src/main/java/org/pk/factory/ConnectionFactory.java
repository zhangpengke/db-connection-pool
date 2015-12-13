/*
 * 文件名称: DBConnectionFactory.java Copyright 2011-2015 PengKe_Zhang All right reserved.
 */
package org.pk.factory;

import java.sql.Connection;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.pk.builder.ConnectionBuilder;
import org.pk.builder.SimpleConnectionBuilder;
import org.pk.config.ConnectionConfig;

/**
 * @author PengKe_Zhang
 */
public class ConnectionFactory extends BasePooledObjectFactory<Connection> {

  private static final ConnectionFactory INSTANCE = new ConnectionFactory();

  private ConnectionConfig connectionConfig;
  private ConnectionBuilder connectionBuilder;

  private ConnectionFactory() {
    super();
  }

  public static final ConnectionFactory getInstance() {
    return INSTANCE;
  }

  public ConnectionFactory config(ConnectionConfig connectionConfig) {
    this.connectionConfig = connectionConfig;
    connectionBuilder = SimpleConnectionBuilder.getInstance();
    return INSTANCE;
  }

  @Override
  public Connection create() throws Exception {
    return connectionBuilder.build(connectionConfig);
  }

  @Override
  public PooledObject<Connection> wrap(Connection obj) {
    return new DefaultPooledObject<Connection>(obj);
  }

  public void setConnectionBuilder(ConnectionBuilder connectionBuilder) {
    this.connectionBuilder = connectionBuilder;
  }

}
