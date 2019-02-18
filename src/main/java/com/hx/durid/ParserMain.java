package com.hx.durid;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import com.alibaba.druid.stat.TableStat;

import java.util.Collection;

public class ParserMain {

  public static void main(String[] args) {
    System.out.println(".11".substring(1));
//    String sql = "select a from (select count(1) from  user)";
    String sql="1";

    // 新建 MySQL Parser
    SQLStatementParser parser = new MySqlStatementParser(sql);


    // 使用Parser解析生成AST，这里SQLStatement就是AST
    SQLStatement statement = parser.parseStatement();

    // 使用visitor来访问AST
    MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
    statement.accept(visitor);

    // 从visitor中拿出你所关注的信息
    Collection<TableStat.Column> columns = visitor.getColumns();
    for (TableStat.Column c:columns){
      System.out.println(c.getName());
      System.out.println(c.getDataType());
    }
  }
}
