package dizoo.std.designpatterns.construct.builder;

/**
 * Created by hx on 16-8-15.
 */
public class Product {
  private String part1;
  private String part2;

  public Product(String part1, String part2) {
    this.part1 = part1;
    this.part2 = part2;
  }

  @Override
  public String toString() {
    return "Product{" +
            "part1='" + part1 + '\'' +
            ", part2='" + part2 + '\'' +
            '}';
  }
}
