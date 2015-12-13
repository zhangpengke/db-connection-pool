/*
 * 文件名称: ExecuterFactoryBean.java Copyright 2011-2015 PengKe_Zhang All right reserved.
 */
package org.pk;

import org.pk.config.ConnectionConfig;
import org.pk.config.ConnectionPoolConfig;
import org.pk.executer.SQLExecuter;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author PengKe_Zhang
 */
public class SQLExecuterFactoryBean implements FactoryBean<SQLExecuter>, InitializingBean, DisposableBean {
  private ConnectionConfig connectionConfig;
  private ConnectionPoolConfig connectionPoolConfig;

  private SQLExecuterProxyBuilder<SQLExecuter> sqlExecuterProxyBuilder;

  @Override
  public SQLExecuter getObject() throws Exception {
    return sqlExecuterProxyBuilder.build();
  }

  @Override
  public Class<?> getObjectType() {
    return null;
  }

  @Override
  public boolean isSingleton() {
    return true;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    sqlExecuterProxyBuilder = new SQLExecuterProxyBuilder<SQLExecuter>(connectionConfig, connectionPoolConfig);
  }

  @Override
  public void destroy() throws Exception {
    sqlExecuterProxyBuilder.releaseConnections();
  }
}
