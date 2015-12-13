/*
 * 文件名称: DBConnectionPool.java Copyright 2011-2015 PengKe_Zhang All right reserved.
 */
package org.pk.pool;

import java.sql.Connection;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * @author PengKe_Zhang
 */
public class ConnectionPool extends GenericObjectPool<Connection> {

  public ConnectionPool(PooledObjectFactory<Connection> factory) {
    super(factory);
  }

  public ConnectionPool(PooledObjectFactory<Connection> factory, GenericObjectPoolConfig config) {
    super(factory, config);
  }
  
}
