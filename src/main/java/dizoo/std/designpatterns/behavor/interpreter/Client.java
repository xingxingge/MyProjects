package dizoo.std.designpatterns.behavor.interpreter;

/**
 * Created by hx on 16-9-21.
 */
public class Client {
  private static Context ctx;
  private static Expression expression;
  public static void main(String[] args) {
    ctx=new Context();
    Variable x = new Variable("x");
    Variable y = new Variable("y");
    Constant  c = new Constant(true);
    ctx.assign(x,false);
    ctx.assign(y,true);
    expression=new And(new And(c,x),new And(c,y));
    System.out.println("x="+x.interpret(ctx));
    System.out.println("y="+y.interpret(ctx));
    System.out.println(expression.toString()+"="+expression.interpret(ctx));

  }
}
