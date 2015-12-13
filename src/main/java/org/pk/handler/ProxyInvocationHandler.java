/*
 * 文件名称: ProxyInvocationHandler.java Copyright 2011-2015 PengKe_Zhang All right reserved.
 */
package org.pk.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.pk.caller.MethodCaller;

/**
 * @author PengKe_Zhang
 */
public class ProxyInvocationHandler implements InvocationHandler {

  private MethodCaller methodCaller;

  public ProxyInvocationHandler(MethodCaller methodCaller) {
    super();
    this.methodCaller = methodCaller;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method,
   * java.lang.Object[])
   */
  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    return methodCaller.callMethod(method, args);
  }

}
