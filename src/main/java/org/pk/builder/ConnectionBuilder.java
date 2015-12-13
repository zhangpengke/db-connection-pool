/*
 * 文件名称: ConnectionBuilder.java Copyright 2011-2015 PengKe_Zhang All right reserved.
 */
package org.pk.builder;

import java.sql.Connection;

import org.pk.config.ConnectionConfig;

/**
 * 数据库连接builder
 * 
 * @author PengKe_Zhang
 */
public interface ConnectionBuilder {

  /**
   * 建造数据库连接
   * 
   * @param connectionConfig
   * @return
   */
  Connection build(ConnectionConfig connectionConfig);
}
