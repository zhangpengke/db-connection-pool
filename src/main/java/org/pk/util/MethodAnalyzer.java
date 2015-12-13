/*
 * 文件名称: MethodAnalyzer.java Copyright 2011-2015 PengKe_Zhang All right reserved.
 */
package org.pk.util;

import java.lang.reflect.Method;

import org.springframework.util.StringUtils;

/**
 * @author PengKe_Zhang
 */
public class MethodAnalyzer {

  public static final MethodType analysis(Method method) {
    MethodType methodType = null;
    String methodName = method.getName();
    if (StringUtils.startsWithIgnoreCase(methodName, "async")) {
      // 异步方法
      if (methodName.contains("PrepareStatement")) {
        methodType = MethodType.AsyncPreapareStatement;
      } else {
        methodType = MethodType.AsyncStatement;
      }
    } else {
      // 同步方法
      if (methodName.contains("PrepareStatement")) {
        methodType = MethodType.SyncPrepareStatement;
      } else {
        methodType = MethodType.SyncStatement;
      }
    }
    return methodType;
  }
}
