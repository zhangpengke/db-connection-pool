package org.pk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Test {

  public static void main(String[] args) throws Exception {
    Class.forName("");//指定连接类型  
    Connection conn = DriverManager.getConnection("", "", "");
    Statement stat = conn.createStatement();
    conn.prepareStatement("");
    stat.executeQuery("");
  }

}
