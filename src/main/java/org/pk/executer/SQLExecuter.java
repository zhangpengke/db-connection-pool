/*
 * 文件名称: DBExecutor.java Copyright 2011-2015 PengKe_Zhang All right reserved.
 */
package org.pk.executer;

import java.sql.ResultSet;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @author PengKe_Zhang
 */
public interface SQLExecuter {

  /**
   * 普通执行sql
   * 
   * @param sql
   * @return
   */
  ResultSet execute(String sql);

  /**
   * 异步执行sql
   * 
   * @param sql
   * @return
   */
  Future<ResultSet> asyncExecute(String sql);

  /**
   * 以preparestatement执行sql
   * 
   * @param sql
   * @param args
   * @return
   */
  ResultSet executeWithPrepareStatement(String sql, List<Object> args);

  /**
   * 异步以preparestatement执行sql
   * 
   * @param sql
   * @param args
   * @return
   */
  Future<ResultSet> asyncExecuteWithPrepareStatement(String sql, List<Object> args);

}
