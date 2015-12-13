/*
 * 文件名称: MethodType.java Copyright 2011-2015 PengKe_Zhang All right reserved.
 */
package org.pk.util;

/**
 * @author PengKe_Zhang
 */
public enum MethodType {

  SyncStatement(1), SyncPrepareStatement(2), AsyncStatement(3), AsyncPreapareStatement(4);

  int code;

  private MethodType(int code) {
    this.code = code;
  }

}
