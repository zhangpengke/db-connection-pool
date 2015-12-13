/*
 * 文件名称: SyncMethodCaller.java Copyright 2011-2015 PengKe_Zhang All right reserved.
 */
package org.pk.caller;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.pk.pool.ConnectionPool;
import org.pk.util.MethodAnalyzer;
import org.pk.util.MethodType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author PengKe_Zhang
 */
public class GenerticMethodCaller extends MethodCaller {

  private static final Logger log = LoggerFactory.getLogger(GenerticMethodCaller.class);

  private int corePoolSize = 10;
  private int maximumPoolSize = 20;
  private long keepAliveTime = 2000;
  private int capacity = 10;
  private BlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<Runnable>(capacity);

  private ExecutorService executorService = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime,
      TimeUnit.MINUTES, taskQueue);

  public GenerticMethodCaller(ConnectionPool connectionPool) {
    super(connectionPool);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.pk.caller.MethodCaller#callMethod(java.lang.reflect.Method, java.lang.Object[])
   */
  @Override
  public Object callMethod(Method method, Object[] args) {
    MethodType methodType = MethodAnalyzer.analysis(method);
    Connection connection = null;
    Object result = null;
    try {
      connection = getConnection();
    } catch (Exception e) {
      log.error("get connection error", e);
    }
    if (null != connection) {
      Statement statement = null;
      PreparedStatement preparedStatement = null;
      switch (methodType) {
        case SyncStatement:
          try {
            String sql = (String) args[0];
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
          } catch (SQLException e) {
            log.error("create statement error", e);
          }
          break;
        case SyncPrepareStatement:
          try {
            String sql = (String) args[0];
            preparedStatement = connection.prepareStatement(sql);
            return statement.executeQuery(sql);
          } catch (SQLException e) {
            log.error("create statement error", e);
          }
          break;
        case AsyncStatement:
          String[] sqls = (String[]) args;

          Task task = new Task(statement, sqls);
          Future future = executorService.submit(task);
          break;
        case AsyncPreapareStatement:

          break;
        default:
          break;
      }
    }

    return result;
  }

  class Task implements Runnable {
    Statement statement = null;
    String[] sqls = null;

    public Task(Statement statement, String[] sqls) {
      super();
      this.statement = statement;
      this.sqls = sqls;
    }

    @Override
    public void run() {

      if (statement instanceof PreparedStatement) {
        try {
          ((PreparedStatement) statement).executeQuery();
        } catch (SQLException e) {
          log.error("PreparedStatement execute error", e);
        }
      } else {
        String sql = sqls[0];
        try {
          statement.execute(sql);
        } catch (SQLException e) {
          log.error("Statement execute error,sql is [{}]", sql, e);
        }
      }

    }

  }
}
