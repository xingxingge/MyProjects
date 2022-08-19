package dizoo.std.designpatterns.structure.bridge;

import java.sql.Connection;

/**
 * Created by hx on 16-9-6.
 */
public class JdbcApi  extends DriverManager{
  public JdbcApi(Driver driver) {
    super(driver);
  }

  public Connection getCOnnection(){
    return super.getConnection();

  }
}
